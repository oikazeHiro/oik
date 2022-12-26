<template>
  <div>1111111222</div>
</template>

<script lang="ts" setup>

import {onMounted, reactive, ref} from 'vue'
import {User} from '@/store/UserDto'
import {Msg,Msg_DataType,ChatMsg,ChatMsgByGroup,ChatMsgAll,BindChannel} from "@/protobufs/Message"

const user = User()
const bindChannel = reactive<BindChannel>({
  userId: user.userId,
  userName: user.username,
})
const msg = reactive<Msg>({
  dataType: Msg_DataType.bindChannelType,
  bindChannel: bindChannel
})
const test = async() => {
  console.log(1)
  console.log(msg)
  console.log(Msg.encode(msg))
}

onMounted(() => {
  console.log()
  test()
})
</script>

<style scoped></style>
