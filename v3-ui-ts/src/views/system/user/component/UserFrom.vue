<template>
  <el-dialog v-model="dialogFormVisible" :title="formType === 0 ? '添加':'修改'">
    <el-form
        v-if="dialogFormVisible"
        ref="ruleFormRef"
        :model="formData"
        status-icon
        :rules="rules"
        label-width="80px"
    >
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="用户名" prop="username">
            <el-input v-model="formData.username" autocomplete="off" clearable/>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="密码" prop="password">
            <el-input v-model="formData.password" :disabled="formType !== 0" type="password" autocomplete="off"/>
          </el-form-item>
        </el-col>
      </el-row>
      <!--      <el-form-item label="menuName" prop="avatar">-->
      <!--        <el-input v-model="formData.avatar" autocomplete="off"/>-->
      <!--      </el-form-item>-->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="部门" prop="deptId">
            <!--            <el-input v-model="formData.deptId" autocomplete="off"/>-->
            <el-cascader v-model="value" :options="options" :props="props1" clearable @change="valueChange"/>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="邮箱" prop="email">
            <el-input v-model="formData.email" clearable/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="联系电话" prop="mobile">
            <el-input v-model="formData.mobile" clearable/>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="状态">
            <el-select v-model="formData.status" clearable placeholder="Select">
              <el-option
                  v-for="item in options2"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
              />
            </el-select>
            <!--            <el-input v-model.number="formData.status"/>-->
          </el-form-item>
        </el-col>
      </el-row>

      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="描述" prop="description">
            <!--        <el-input v-model.number="formData.description"/>-->
            <el-input
                v-model="formData.description"
                autosize
                type="textarea"
                placeholder="Please input"
                style="width: 190px"
                clearable
            />
          </el-form-item>
        </el-col>
      </el-row>

    </el-form>
    <template #footer>
      <span class="dialog-footer">
       <el-button type="primary" @click="submitForm(ruleFormRef)">提交</el-button>
        <el-button type="primary" @click="dialogFormVisible = false">
          取消
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import {reactive, ref} from "vue";
import {user} from "@/entity/interface";
import {FormInstance} from "element-plus";
import {register, setUser} from '@/api/request/user'
import {deptCache} from '@/api/request/dept'

const options2 = [
  {
    value: 0,
    label: '无效',
  },
  {
    value: 1,
    label: '有效',
  },
]

const props1 = {
  checkStrictly: true,
  value: 'deptId',
  label: 'deptName',
  disabled:'verify'
}

const options = ref()
const value = ref()
const formData = ref<user>({
  userId: '',
  username: '',
  password: '',
  avatar: 'https://tse2-mm.cn.bing.net/th/id/OIP-C.vNn5RXHfCzUZGcdtzYG92AHaHa?w=203&h=203&c=7&r=0&o=5&pid=1.7',
  deptId: '',
  email: '',
  mobile: '',
  status: 1,
  description: ''
})
const dialogFormVisible = ref<boolean>(false)
const formType = ref<number>(0)
const reg = /^[a-zA-Z0-9_-]$/
const mobile = /0?(13|14|15|18|17)[0-9]{9}/
const email = /\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}/

const ruleFormRef = ref<FormInstance>()
const validateUsername = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('请输入用户名'))
  } else if (value.length < 5) {
    callback(new Error('用户名不能小于5'))
  } else if (value.length > 20) {
    callback(new Error('用户名不能超过20'))
  } else if (reg.test(value)) {
    callback(new Error('只能由26个大小写英文字母 数字0-9 - _组成'))
  } else {
    callback()
  }
}
const validateMobile = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('请输入联系电话'))
  } else if (!mobile.test(value)) {
    callback(new Error('请输入正确的手机号'))
  } else {
    callback()
  }
}
const validateEmail = (rule: any, value: any, callback: any) => {
  if (value === '' || !value) {
    callback(new Error('请输入邮箱'))
  } else if (!email.test(value)) {
    callback(new Error('请输入正确的邮箱'))
  } else {
    callback()
  }
}
const validateDescription = (rule: any, value: any, callback: any) => {
  if (value === '' || !value) {
    callback(new Error('请输入描述'))
  } else if (value.length > 100) {
    callback(new Error('请不要写小作文'))
  } else {
    callback()
  }
}


const rules = reactive({
  username: [{validator: validateUsername, trigger: 'blur'}],
  mobile: [{validator: validateMobile, trigger: 'blur'}],
  email: [{validator: validateEmail, trigger: 'blur'}],
  description: [{validator: validateDescription, trigger: 'blur'}],
})
const emits = defineEmits(['save-ok']);
const submit = async () => {
  try {
    if (formType.value === 0) {
      const res = await register(formData.value)
    } else {
      const res = await setUser(formData.value)
    }
  } catch (e) {
    console.log(e)
  } finally {
    emits("save-ok")
    dialogFormVisible.value = false
  }
}
const submitForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.validate((valid) => {
    if (valid) {
      submit()
    } else {
      return false
    }
  })
}
const resetForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return
  formEl.resetFields()
}
const show = async (data: any, type: number) => {
  try {
    const res = await deptCache(1);
    res.data.forEach((e) => {
      e.verify = e.status === 0
      if (e.children) {
        e.children.forEach((f) => {
          f.verify = f.status === 0
        })
      }
    })
    options.value = res.data
    console.log(res.data)
    console.log(options.value)
  } catch (e) {
    console.log(e)
  }
  if (type != 0) {
    formType.value = type
    formData.value = data
    value.value = formData.value.deptId
  } else {
    value.value = ''
    formType.value = 0
    formData.value = {
      userId: '',
      username: '',
      password: '',
      avatar: 'https://tse2-mm.cn.bing.net/th/id/OIP-C.vNn5RXHfCzUZGcdtzYG92AHaHa?w=203&h=203&c=7&r=0&o=5&pid=1.7',
      deptId: undefined,
      email: '',
      mobile: '',
      status: 1,
      description: ''
    }
    resetForm(ruleFormRef.value)
  }
  dialogFormVisible.value = true
}
const valueChange = () => {
  formData.value.deptId = value.value[value.value.length - 1]
}
//暴露方法
defineExpose({show})
</script>

<style lang="scss" scoped>

</style>