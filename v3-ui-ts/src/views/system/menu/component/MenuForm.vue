<template>
  <el-dialog v-model="dialogFormVisible" :title="title">
    <el-form
        v-loading="loading"
        ref="ruleFormRef"
        :model="formData"
        status-icon
        :rules="rules"
        label-width="120px"
        class="demo-ruleForm"
    >
      <el-form-item label="名称" prop="menuName">
        <el-input v-model="formData.menuName" autocomplete="off"/>
      </el-form-item>
      <el-form-item v-if="flag" label="路径" prop="path">
        <el-input v-model="formData.path" autocomplete="off"/>
      </el-form-item>
      <el-form-item v-if="flag" label="组件" prop="component">
        <el-input v-model.number="formData.component"/>
      </el-form-item>
      <el-form-item v-if="!flag" label="权限" prop="perms">
        <el-input v-model.number="formData.perms"/>
      </el-form-item>
      <el-form-item label="图标" prop="icon">
        <el-input v-model.number="formData.icon"/>
      </el-form-item>
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
import {menus} from "@/entity/interface";
import {saveMenu} from '@/api/request/menu'
import type {FormInstance} from 'element-plus'

const dialogFormVisible = ref(false)
const title = ref<string>('')
const type = ref(0)
const flag = ref(true)
const formData = ref<menus>({})
const menuTy = ref(0)
const show = async (data: menus, type: number, menuType?: number) => {
  await formDataInit()
  if (menuType) menuTy.value = menuType
  else menuTy.value = 0
  if (!type) type = 0;
  title.value = type === 0 ? '添加' : '编辑'
  if (data != null) {
    formData.value.parentId = data.menuId
    if (data.parentId != '0') flag.value = false
    if (type !== 0 && data.type === '0') flag.value = true
  } else {
    formData.value.parentId = '0'
  }
  if (type != 0) {
    // data.children = null
    formData.value = data
  }
  dialogFormVisible.value = true

}
const formDataInit = async () => {
  flag.value = true
  formData.value = {
    menuId: null,
    parentId: '0',
    menuName: '',
    path: '',
    component: '',
    perms: '',
    icon: '',
    type: '0',
    orderNum: null,
    createTime: '',
    updateTime: '',
    status: '',
    createUsername: '',
    createUserId: '',
    updateUsername: '',
    updateUserId: ''
  }
}
const emits = defineEmits(['save-ok']);
const loading = ref(false)
const submit = async () => {
  try {
    loading.value = true
    if (title.value == "添加" && menuTy.value === 1) formData.value.type = '1';
    const res = await saveMenu(formData.value)
  }finally {
    loading.value = false
    emits("save-ok")
    dialogFormVisible.value = false
  }

}
const ruleFormRef = ref<FormInstance>()
const validateMenuName = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('Please input the menuName again'))
  } else if (value.length > 10) {
    callback(new Error('The menuName length can not exceed 10'))
  } else {
    callback()
  }
}
const validatePath = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('Please input the path again'))
  } else if (value.length > 200) {
    callback(new Error('The path length can not exceed 200'))
  } else {
    callback()
  }
}
const validateComponent = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('Please input the component again'))
  } else if (value.length > 200) {
    callback(new Error('The component length can not exceed 200'))
  } else {
    callback()
  }
}
const validatePerms = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('Please input the perms again'))
  } else if (value.length > 50) {
    callback(new Error('The perms length can not exceed 50'))
  } else {
    callback()
  }
}
const validateIcon = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('Please input the icon again'))
  } else if (value.length > 50) {
    callback(new Error('The icon length can not exceed 50'))
  } else {
    callback()
  }
}

const rules = reactive({
  menuName: [{validator: validateMenuName, trigger: 'blur'}],
  path: [{validator: validatePath, trigger: 'blur'}],
  component: [{validator: validateComponent, trigger: 'blur'}],
  perms: [{validator: validatePerms, trigger: 'blur'}],
  icon: [{validator: validateIcon, trigger: 'blur'}],
})

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
//暴露方法
defineExpose({show})
</script>

<style lang="scss" scoped>

</style>