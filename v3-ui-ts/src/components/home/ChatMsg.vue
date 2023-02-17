<template>
  <el-drawer v-model="drawer" direction="rtl" size="50%" title="">
    <el-container style="height: 100%">
      <el-aside style="height: 100%;" width="30%">
        <div
            v-for="item in userList"
            :key="item.userId"
            class="div-chat"
            style="width: 100%"
            v-on:click="userClick(item)"
        >
          <el-row align="middle">
            <el-col :span="6">
              <el-avatar
                  :src="item.avatar"
              />
            </el-col>
            <el-col :span="12">
              {{ item.username }}
              <el-icon :color="item.online?'#67C23A':''">
                <CircleCheckFilled v-if="item.online"/>
              </el-icon>
              <el-icon :color="!item.online?'#909399':''">
                <CircleCloseFilled v-if="!item.online"/>
              </el-icon>
              <!--              </el-button>-->
            </el-col>
          </el-row>
        </div>

      </el-aside>
      <el-main>
        <talk-msg :friend="friend"/>
      </el-main>
    </el-container>
  </el-drawer>
</template>

<script lang="ts" setup>
import {onMounted, reactive, ref, watchEffect} from "vue";
import chatMsgImpl from '@/entity/ChatMsg'
import {getUserList} from '@/api/request/user'
import {User} from '@/store/UserDto'
import {user} from "@/entity/interface";
import TalkMsg from "@/components/home/TalkMsg.vue";

const drawer = ref(false)
const friend = ref<user>()
const show = async () => {
  drawer.value = true
  await getList()
  await updateUserOnLine()
}
const userList = ref<Array<user>>([])
const getList = async () => {
  const userDto = User().userDto
  const res = await getUserList(userDto.userId)
  userList.value = res.data
  userList.value.forEach((e) => {
    userListMap.set(e.userId, 0)
  })

  friend.value = userList.value[0]
}
const userListMap = new Map()
const userOnline = reactive({
  userOnline: {
    userOnlineList: []
  }
})

const userClick = async (item: user) => {
  friend.value = item
  console.log(friend)
}
const updateUserOnLine = async () => {
  userOnline.userOnline.userOnlineList.forEach((e) => {
    userList.value.forEach((a) => {
      if (a.userId === e) {
        a.online = true
      }
    })
  })
}
const getSocketData = (res) => {
  const userDto = User().userDto
  const oneMsg = JSON.parse(res.detail.data) as chatMsgImpl
  if (oneMsg.code === 1 && oneMsg.sendId !== userDto.userId) {
    userListMap.set(oneMsg.sendId, userListMap.get(oneMsg.sendId) + 1)
  }
  if (oneMsg.code === 5 && oneMsg.msgType === 3) {
    userOnline.userOnline = JSON.parse(oneMsg.expandMsg)
    updateUserOnLine()
  }
}
onMounted(async () => {
  window.addEventListener('onmessageWS', getSocketData)
})
watchEffect(() => {
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

.demo-tabs .custom-tabs-label .el-icon {
  vertical-align: middle;

}

.demo-tabs .custom-tabs-label span {
  vertical-align: middle;
  margin-left: 4px;
}

.userList {
  display: flex;
  justify-content: center;
}

.div-chat:hover {
  background-color: antiquewhite;
}

</style>