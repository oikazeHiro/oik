<template>
  <el-container style="background-color: aqua;height: 650px">
    <el-main style="height: 95%;background-color: #ececec">
      <div v-for="item in msg" :key="item.id">
        <el-row v-if="item.sendId === userDto.userId" :gutter="20" align="middle" justify="end">
          <!--          <el-col :span="11"></el-col>-->
          <el-col :span="10" align="right">{{ item.msg }}</el-col>
          <el-col :span="3">
            <el-avatar
                :src="userDto.avatar"
            />
          </el-col>
        </el-row>
        <el-row v-if="item.sendId === friend.userId" :gutter="20" align="middle" justify="start">
          <el-col :span="3">
            <el-avatar
                :src="friend.avatar"
            />
          </el-col>
          <el-col :span="10">{{ item.msg }}</el-col>
        </el-row>
      </div>
    </el-main>
    <el-footer style="height: 10%;background-color: #f3f1df">
      <el-row>
        <el-col :span="18">
          <el-input
              v-model="chatMsgDome.ChatMdg.msg"
              :rows="2"
              placeholder="Please input"
              type="textarea"
          />
        </el-col>
        <el-col :span="6">
          <el-button :disabled="!chatMsgDome.ChatMdg.msg" @click="sendMsg">socketSend</el-button>
          <el-button :disabled="!chatMsgDome.ChatMdg.msg" @click="httpSend">httpSend</el-button>
        </el-col>
      </el-row>
    </el-footer>
  </el-container>
</template>

<script lang="ts" setup>
import {getSocket} from '@/util/InitSocket'

import chatMsgImpl from '@/entity/ChatMsg'
import {onMounted, reactive, ref, watchEffect} from "vue";
import {User} from '@/store/UserDto'
import {query, user} from "@/entity/interface";
import {chatMsgList, sendPrivateMsg} from '@/api/request/chatMsg'

const props = defineProps({
  friend: {
    type: Object
  }
})
const result = reactive<query<chatMsgImpl>>({
  page: {
    records: [],
    total: 0,
    size: 30,
    current: 1,
  },
  param: new chatMsgImpl()
})
const webSocket = getSocket();

const chatMsgDome = reactive({
  ChatMdg: new chatMsgImpl('', 1)
})
const sendMsg = async () => {
  // const msgImpl = new chatMsgImpl('', 1, '35', '1', 'test', "", '');
  webSocket.send(chatMsgDome.ChatMdg)
  chatMsgDome.ChatMdg.msg = ''
}

const httpSend = async () => {
  console.log(chatMsgDome.ChatMdg)
  const res = sendPrivateMsg(chatMsgDome.ChatMdg)
  console.log(res)
  chatMsgDome.ChatMdg.msg = ''
}

const userDto = ref<user>()
const msg = ref<Array<chatMsgImpl>>([])
const getMsgList = async () => {
  const res = await chatMsgList(result)
  const data = res.data.records as Array<chatMsgImpl>
  data.forEach((e) => {
    msg.value.unshift(e)
  })

  console.log(msg)
}

const initMsgList = async () => {
  if (!userDto.value || !props.friend) {
    return
  }
  result.param.sendId = userDto.value.userId;
  result.param.acceptId = props.friend.userId
  await getMsgList()
  chatMsgDome.ChatMdg.sendId = userDto.value.userId
  chatMsgDome.ChatMdg.acceptId = props.friend.userId
}
const getSocketData = (res) => {
  console.log(props.friend)
  if (!userDto.value) {
    userDto.value = User().userDto
  }
  const oneMsg = JSON.parse(res.detail.data) as chatMsgImpl
  if (oneMsg.code === 1 && (oneMsg.acceptId == props.friend.userId || oneMsg.acceptId == userDto.value.userId)) {
    msg.value.push(oneMsg)
  }
}

watchEffect(async () => {
  msg.value = []
  await initMsgList()
})
onMounted(async () => {
  userDto.value = User().userDto
  window.addEventListener('onmessageWS', getSocketData)
  await initMsgList()
})
</script>

<style lang="scss" scoped>

</style>