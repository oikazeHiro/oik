<template>
<el-container>
  <el-header>

  </el-header>
  <el-main>

  </el-main>
  <el-footer>
    <el-button @click="sendMsg">test</el-button>
  </el-footer>
</el-container>
</template>

<script lang="ts" setup>
import {getSocket} from '@/util/InitSocket'

import chatMsgImpl, {chatMsg} from '@/entity/ChatMsg'
import {onMounted, ref, watchEffect} from "vue";
import {User} from '@/store/UserDto'
const props = defineProps({
  user:{
    type: Object
  }
})
const webSocket = getSocket();
const sendMsg = async () => {
  console.log(3333)
    const msgImpl = new chatMsgImpl('',1,'1','35','test');
  webSocket.send(msgImpl)
}
const userDto = User().userDto
const msg = ref<Array<chatMsg>>([])
const getSocketData = (res) => {
  console.log(JSON.parse(res.detail.data))
  const oneMsg = JSON.parse(res.detail.data) as chatMsgImpl
  if (oneMsg.code === 1 && (oneMsg.acceptId == props.user.userId || oneMsg.acceptId == userDto.userId)){
    msg.value.push(oneMsg)
    console.log("==========================================================================")
    console.log(props.user.userId)
    console.log(msg.value)
  }
}

watchEffect(() => {
  console.log()
})
onMounted(async ()=>{
  window.addEventListener('onmessageWS', getSocketData)
  console.log()
})
</script>

<style lang="scss" scoped>

</style>