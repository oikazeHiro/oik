<template>
  <el-drawer v-model="drawer" direction="rtl" title="" size="50%" >
    <el-tabs tab-position="left" style="height: 80%" class="demo-tabs">
      <el-tab-pane v-for="item in userList" :key="item.userId" :label="item.username">
        <talk-msg :user="item"/>
      </el-tab-pane>
    </el-tabs>
  </el-drawer>
</template>

<script lang="ts" setup>
import Socket from '@/util/socket'
import {onMounted, ref, watchEffect} from "vue";
import chatMsgImpl, {chatMsg} from '@/entity/ChatMsg'
import {getUserList} from '@/api/request/user'
import {User} from '@/store/UserDto'
import {user} from "@/entity/interface";
import TalkMsg from "@/components/home/TalkMsg.vue";
import {initSocket,getSocket} from '@/util/InitSocket'
import {ElMessage} from "element-plus";
const userDto = User().userDto
const drawer = ref(false)



const show = () => {
  drawer.value = true
}
const userList = ref<Array<user>>([])
const getList = async () =>{
  const res = await getUserList(userDto.userId)
  userList.value = res.data
  console.log()
  userList.value.forEach((e)=>{
    userListMap.set(e.userId,0)
  })
}
const userListMap = new Map()
const init = async () => {
  await getList()
}
const getSocketData = (res) => {
  console.log(JSON.parse(res.detail.data))
  const oneMsg = JSON.parse(res.detail.data) as chatMsgImpl
  if (oneMsg.code === 1 && oneMsg.sendId !== userDto.userId){
    userListMap.set(oneMsg.sendId,userListMap.get(oneMsg.sendId)+1)
  }
  if (oneMsg.code === 5){

  }

}
onMounted(async ()=>{
  initSocket()
  await init()
  window.addEventListener('onmessageWS', getSocketData)
})
watchEffect(() =>{
  console.log()
})
defineExpose({show})
</script>

<style lang="scss" scoped>
.demo-tabs > .el-tabs__content {
  padding: 32px;
  color: #6b778c;
  font-size: 32px;
  font-weight: 600;
}

.el-tabs--right .el-tabs__content,
.el-tabs--left .el-tabs__content {
  height: 100%;
}
</style>