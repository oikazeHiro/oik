<template>
  <el-container style="height: 100%;background-color: white">
    <el-main>
      <el-row :gutter="8" class="head-info">
        <el-card class="head-info-card" shadow="always">
          <el-col :span="12">
            <div class="head-info-avatar">
              <img :src="avatar" alt="头像">
            </div>
            <div class="head-info-count">
              <div class="head-info-welcome">
                {{ welcomeMsg }}
              </div>
              <div class="head-info-desc">
                <p>{{ user.description ? user.description : '你甚至不愿意介绍一下自己' }}</p>
              </div>
              <div class="head-info-time">
                上次登录时间：
              </div>
            </div>
          </el-col>
        </el-card>
      </el-row>
    </el-main>
  </el-container>
</template>

<script lang="ts" setup>

import {onMounted, ref} from 'vue'
import {User} from '@/store/UserDto';
import {getWelcomeMsg} from '@/api/request/greet'

const user = User().userDto
const avatar = user.avatar ? user.avatar : 'https://tse2-mm.cn.bing.net/th/id/OIP-C.vNn5RXHfCzUZGcdtzYG92AHaHa?w=203&h=203&c=7&r=0&o=5&pid=1.7'
const welcomeMsg = ref<string>()
const todayIp = ref(1)
const todayVisitCount = ref(1)
const totalVisitCount = ref(1)


const welcome = async () => {
  const date = new Date()
  const hour = date.getHours()
  const time = hour < 6 ? '早上好' : (hour <= 11 ? '上午好' : (hour <= 13 ? '中午好' : (hour <= 18 ? '下午好' : '晚上好')))
  const res = await getWelcomeMsg()
  const data = res.data.records
  welcomeMsg.value = time + ',' + user.username + ',' + data[0 + Math.round(Math.random() * (data.length - 1))].greet
}
onMounted(() => {
  welcome()
})
</script>

<style lang="scss" scoped>
.head-info {
  margin-bottom: .5rem;
  height: 25%;

  .head-info-card {
    padding: .5rem;
    border-color: #f1f1f1;
    width: 100%;

    .head-info-avatar {
      display: inline-block;
      float: left;
      margin-right: 1rem;

      img {
        width: 5rem;
        border-radius: 2px;
      }
    }

    .head-info-count {
      display: inline-block;
      float: left;

      .head-info-welcome {
        font-size: 1.05rem;
        margin-bottom: .1rem;
      }

      .head-info-desc {
        color: rgba(0, 0, 0, 0.45);
        font-size: .8rem;
        padding: .5rem 0;

        p {
          margin-bottom: 0;
        }
      }

      .head-info-time {
        color: rgba(0, 0, 0, 0.45);
        font-size: .8rem;
        padding: .4rem 0;
      }
    }
  }
}
</style>
