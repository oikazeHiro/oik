<template>
  <div>1111111222</div>
</template>

<script lang="ts" setup>
import message from '@/protobufs/Message_pb'
import { onMounted, ref } from 'vue'

const testDataType = async () => {
  console.log(message)
  console.log(message.Msg.DataType)
  console.log(message.Msg.prototype)
  console.log(message.Msg.DataType.CHATMSGALLTYPE)

  const a = message.Msg.prototype.setDataType(
    message.Msg.DataType.CHATMSGALLTYPE,
  )
  console.log(a)
  const b = message.Msg.prototype.getDataType()
  console.log(b)
}

onMounted(() => {
  testDataType()
})
</script>

<style scoped></style>
