<template>
  <el-container>
    <el-container>
      <el-aside :width="isCollapse ? '64px' : '200px'">
        <el-menu
            :collapse="isCollapse"
            active-text-color="#ffd04b"
            background-color="#545c64"
            class="el-menu-vertical-demo"
            default-active="/system/home/index"
            router
            text-color="#fff"
            @close="handleClose"
            @open="handleOpen"
        >
          <div class="logo"></div>
          <template v-for="menu in menuList" :key="menu.menuName">
            <el-sub-menu
                v-if="menu.children.length > 1"
                :index="menu.path"
                default-active="Index"
            >
              <template #title>
                <component :is="menu.icon" class="el-icon"></component>
                <span>{{ menu.menuName }}</span>
              </template>
              <el-menu-item
                  v-for="item in menu.children"
                  :key="item.menuName"
                  :index="item.path"
              >
                <template #title>
                  <component :is="item.icon" class="el-icon"></component>
                  <span>{{ item.menuName }}</span>
                </template>
              </el-menu-item>
            </el-sub-menu>
            <el-menu-item
                v-if="menu.children.length === 1"
                :index="menu.children[0].path"
            >
              <component
                  :is="menu.children[0].icon"
                  class="el-icon">
              </component>
              <template #title>
                <span>{{ menu.children[0].menuName }}</span>
              </template>
            </el-menu-item>
          </template>
        </el-menu>
      </el-aside>
      <el-container style="background-color: #f0f2f5">
        <el-header style="font-size: 14px;background-color: white">
          <el-row :gutter="20" class="home-row" style="height: 100%">
            <el-col :span="2" class="vertical-Center">
              <!-- <div @click="toggleCollapse">|||</div> -->
              <component
                  :is="toggle"
                  class="el-icon toggle"
                  @click="toggleCollapse">
              </component>
            </el-col>
            <el-col :span="19"></el-col>
            <el-col :span="1" class="vertical-Center">
              <div class="toolbar">
                <el-dropdown>
                  <el-avatar :size="60" :src="avatar" style="width: 35px;height: 35px">
                    <!--                  <el-avatar :size="60" src="@/assets/default.jpg" style="width: 35px;height: 35px">-->
                  </el-avatar>
                  <template #dropdown>
                    <el-dropdown-menu>
                      <el-dropdown-item>个人中心</el-dropdown-item>
                      <el-dropdown-item>修改密码</el-dropdown-item>
                      <el-dropdown-item @click="doLogout">退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                  </template>
                </el-dropdown>

              </div>
            </el-col>
            <el-col :span="2">
              <span>{{ username }}</span>
            </el-col>
          </el-row>
        </el-header>
        <el-main>
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </el-container>
</template>

<script lang="ts" setup>
import {onMounted, ref} from 'vue'
import {useMeanStore} from '@/store/menus'
import {User} from '@/store/UserDto'
import {RouterView} from 'vue-router'
import {logout} from '@/api/request/login'
import router from "@/router";

const isCollapse = ref(false)
const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
const toggle = ref('Fold')
const toggleCollapse = () => {
  isCollapse.value = !isCollapse.value
  if (isCollapse.value) {
    toggle.value = 'Expand'
  } else {
    toggle.value = 'Fold'
  }
}

const useMean = useMeanStore()
const menuList = useMean.menuList
const userDto = User()
const avatar = ref<string>("https://tse2-mm.cn.bing.net/th/id/OIP-C.vNn5RXHfCzUZGcdtzYG92AHaHa?w=203&h=203&c=7&r=0&o=5&pid=1.7")
const username = ref<string>(userDto.userDto.username)
const getAvatar = async () => {
  if (userDto.userDto.avatar != '' && userDto.userDto.avatar != null) {
    avatar.value = userDto.userDto.avatar
  }
}

const doLogout = async () => {
  const res = await logout()
  console.log(res)
  localStorage.clear();
  router.push({path: '/login'})
}
onMounted(() => {
  console.log(menuList)
  console.log(userDto.userDto)
  getAvatar()
  console.log(username)
})
</script>

<style lang="scss" scoped>
.el-header {
  margin: 0;
  padding: 0;
  height: 64px;
}

.el-aside {
  transition: width 0.25s;
  -webkit-transition: width 0.25s;
  -moz-transition: width 0.25s;
  -webkit-transition: width 0.25s;
  -o-transition: width 0.25s;
  background-color: #545c64;
  // height: 100%;
  .el-menu {
    // height: calc(100vh - 80px);
    height: 100vh;
  }

  .toggle-button {
    // 添加背景颜色
    background-color: #545c64;
    // 设置文本大小
    font-size: 10px;
    // 设置文本行高
    line-height: 24px;
    // 设置文本颜色
    color: #fff;
    // 设置文本居中
    text-align: center;
    // 设置文本间距
    // letter-spacing: 0.2em;
    // 设置鼠标悬浮变小手效果
    cursor: pointer;
  }

  .layout-container-demo .toolbar {
    display: inline-flex;
    align-items: center;
    justify-content: center;
    height: 100%;
    right: 20px;
  }
}

.logo {
  height: 64px;
  width: 100%;
  background-color: #545c64;
}

.toggle {
  font-size: 24px;
  color: #545c64;
}

.vertical-Center {
  display: flex;
  justify-content: center;
}

.home-row {
  display: flex;
  align-items: center;
}
</style>
