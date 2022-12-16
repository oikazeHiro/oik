<template>
  <el-container>
    <el-header>
      <!-- <el-radio-group v-model="isCollapse" style="">
        <el-radio-button :label="false">expand</el-radio-button>
        <el-radio-button :label="true">collapse</el-radio-button>
      </el-radio-group> -->
    </el-header>
    <el-container>
      <el-aside :width="isCollapse ? '64px' : '200px'">
        <el-menu
            :collapse="isCollapse"
            active-text-color="#ffd04b"
            background-color="#545c64"
            class="el-menu-vertical-demo"
            default-active="2"
            router
            text-color="#fff"
            @close="handleClose"
            @open="handleOpen"
        >
          <div class="toggle-button" @click="toggleCollapse">|||</div>
          <el-menu-item index="default">
            <template #title>
              <el-icon>
                <House/>
              </el-icon>
              <span>首页</span>
            </template>
          </el-menu-item>
          <el-sub-menu
              v-for="menu in menuList"
              :key="menu.menuName"
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
        </el-menu>
      </el-aside>
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</template>

<script lang="ts" setup>
import {onMounted, ref} from 'vue'
import {useMeanStore} from '@/store/menus'
import {RouterView} from 'vue-router'

const isCollapse = ref(true)
const handleOpen = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
const handleClose = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}

const toggleCollapse = () => {
  isCollapse.value = !isCollapse.value
}

const useMean = useMeanStore()
const menuList = useMean.menuList

onMounted(() => {
  console.log()
})
</script>

<style lang="scss" scoped>
.el-header {
  height: 80px;
  background-color: #545c64;
}

.el-aside {
  transition: width 0.25s;
  -webkit-transition: width 0.25s;
  -moz-transition: width 0.25s;
  -webkit-transition: width 0.25s;
  -o-transition: width 0.25s;

  .el-menu {
    height: calc(100vh - 80px);
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
}
</style>
