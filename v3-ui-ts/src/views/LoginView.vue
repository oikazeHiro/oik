<template>
  <div class="login-box">
    <el-form
        v-loading="loading"
        ref="ruleFormRef"
        :model="loginFrom"
        :rules="rules"
        class="loginFrom"
        label-width="70px"
        status-icon
    >
      <h1>标题</h1>
      <el-form-item class="form-item" label="账号" prop="username">
        <el-input v-model="loginFrom.username" autocomplete="off"/>
      </el-form-item>
      <el-form-item class="form-item" label="密码" prop="password">
        <el-input
            v-model="loginFrom.password"
            autocomplete="off"
            type="password"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm(ruleFormRef)">
          登录
        </el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts" setup>
import {reactive, ref} from 'vue'
import type {FormInstance} from 'element-plus'
import router from '@/router'
import {LoginFrom} from '@/entity/interface'
import {login} from '@/api/request/login'
import {User} from '@/store/UserDto'

const ruleFormRef = ref<FormInstance>()
const loginFrom = reactive<LoginFrom>({
  username: '',
  password: '',
})
const rules = reactive({
  username: [
    {
      required: true,
      trigger: 'blur',
      message: '请输入账号',
    },
    {
      min: 4,
      max: 24,
      message: '账号长度需要在4-24之间',
    },
  ],
  password: [
    {
      required: true,
      trigger: 'blur',
      message: '请输入密码',
    },
    {
      min: 6,
      max: 24,
      message: '密码长度需要在6-24之间',
    },
  ],
})
const loading = ref(false)
const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate(async (valid, fields) => {
    if (valid) {
      loading.value=true
      const res = await login(loginFrom)
      loading.value=false
      console.log(res)
      localStorage.setItem('token', res.data.token)
      const saveUser = User()
      saveUser.setUserDto(res.data)
      await router.push({path: '/system/home/index'})
    } else {
      loading.value=false
      console.log('error submit!', fields)
    }
  })
}
</script>

<style lang="scss" scoped>
@keyframes animate {
  0% {
    filter: hue-rotate(0deg);
  }

  100% {
    filter: hue-rotate(360deg);
  }
}

.login-box {
  width: 100%;
  height: 100%;
  background-image: url('~@/assets/miku.jpg');

  .loginFrom {
    width: 400px;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    border-radius: 25px;
    border: 1px solid black;
    background-color: rgba(255, 255, 255, 0.1) !important;
    backdrop-filter: blur(5px);
    box-shadow: -5px -5px 10px rgb(39, 65, 65), 5px 5px 20px aqua;
    /* 5秒 infinite循环播放无限次 linear匀速  */
    animation: animate 5s linear infinite;

    h1 {
      color: white;
      white-space: nowrap;
      text-align: center;
      margin: 20px 0;
    }
  }

  :deep(.el-form-item) {
    margin-top: 20px;
    width: 90%;
  }

  :deep(.el-input__wrapper) {
    background-color: rgba(255, 255, 255, 0.5);
  }
}
</style>
