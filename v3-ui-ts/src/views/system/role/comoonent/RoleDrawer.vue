<template>
  <el-drawer v-model="drawer_" :direction="direction" :title="title" :with-header="with_header" size="40%" >
    <el-form
        v-if="open"
        ref="ruleFormRef"
        :model="data"
        status-icon
        :rules="rules"
        label-width="80px"
        class="demo-ruleForm"
    >
      <el-form-item label="角色名" prop="roleName">
        <el-input v-model="data.roleName" clearable/>
      </el-form-item>
      <el-form-item label="备注" prop="remark">
        <el-input v-model="data.remark" clearable/>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-radio-group v-model="data.status" class="ml-4">
          <el-radio :label="0" size="large">无效</el-radio>
          <el-radio :label="1" size="large">有效</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="数据权限" prop="dataScope">
        <el-radio-group v-model="data.dataScope" class="ml-4">
          <el-radio :label="0" size="large">全部数据</el-radio>
          <el-radio :label="1" size="large">个人数据</el-radio>
          <el-radio :label="2" size="large">部门数据</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="权限树">
        <el-tree
            v-loading="loading"
            ref="treeRef"
            :data="menuResult.page.records"
            show-checkbox
            default-expand-all
            node-key="menuId"
            highlight-current
            :check-strictly="true"
            :props="treeProps"
        />
      </el-form-item>
    </el-form>

    <template #footer>
      <div style="flex: auto">
        <!--        <el-button @click="getCheckedKeys">get by key</el-button>-->
        <el-button @click="drawer_ = false">取消</el-button>
        <el-button type="primary" @click="confirmClick">确定</el-button>
      </div>
    </template>
  </el-drawer>
</template>

<script lang="ts" setup>
import {reactive, ref} from 'vue'
import {getPermsBtRole, saveRoleAndPerms, setRoleAndPerms} from "@/api/request/role"
import {menus, query, role} from "@/entity/interface";
import type {FormInstance} from 'element-plus'
import {ElTree} from "element-plus";
import {getMenus2} from "@/api/request/menu";


const treeRef = ref<InstanceType<typeof ElTree>>()
const props = defineProps({
  title: {
    type: String,
    default: "title"
  },
  with_header: {
    type: Boolean,
    default: false
  },
  direction: {
    type: String,
    default: 'rtl'
  },
})
const open = ref(false)
const drawer_ = ref(false)
const data = ref<role>({})
const treeProps = {
  label: 'menuName',
  children: 'children'
}
const loading = ref(false)
const show = async (param: role) => {
  try {
    open.value = true
    data.value = param
    drawer_.value = true
    if (data.value.roleId) {
      loading.value = true
      const res = await getPermsBtRole(data.value.roleId)
      data.value.perms = res.data
    }
    await initOptions()
  } finally {
    loading.value = false
  }
}
const menuResult = reactive<query<menus>>({
  page: {
    records: [],
    total: 0,
    size: 200,
    current: 1,
  },
  param: {}
})
const initOptions = async () => {
  const res = await getMenus2(menuResult)
  menuResult.page = res.data
  if (data.value.perms) {
    treeRef.value!.setCheckedKeys(data.value.perms, false)
  }else {
    treeRef.value!.setCheckedKeys([], false)
  }
}
// const getCheckedKeys = () => {
//   console.log(treeRef.value!.getCheckedKeys(false))
// }
const ruleFormRef = ref<FormInstance>()

const validateRolename = (rule: any, value: any, callback: any) => {
  if (value === '') {
    callback(new Error('请输入角色名'))
  } else if (value.length < 2) {
    callback(new Error('用户名不能小于2'))
  } else if (value.length > 20) {
    callback(new Error('用户名不能超过20'))
  } else {
    callback()
  }
}

const validateDescription = (rule: any, value: any, callback: any) => {
  if (value === '' || !value) {
    callback(new Error('请输入备注'))
  } else if (value.length > 100) {
    callback(new Error('请不要写小作文'))
  } else {
    callback()
  }
}

const rules = reactive({
  roleName: [{validator: validateRolename, trigger: 'blur'}],
  remark: [{validator: validateDescription, trigger: 'blur'}],
  status: [
    {
      required: true,
      message: '请选择状态',
      trigger: 'change',
    },
  ],
  dataScope: [
    {
      required: true,
      message: '请选择业务数据权限',
      trigger: 'change',
    },
  ],
})
const emits = defineEmits(['save-ok']);
const submit = async () => {
  loading.value = true
  data.value.perms = treeRef.value!.getCheckedKeys(false)
  try {
    if (!data.value.roleId||data.value.roleId === ''){
      await saveRoleAndPerms(data.value)
    }else {
      await setRoleAndPerms(data.value)
    }
  }finally {
    loading.value = false
    open.value = false
    drawer_.value = false
    emits("save-ok")
  }
}

const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      submit()
    } else {
      console.log('error submit!', fields)
    }
  })
}
const confirmClick = async () => {
  await submitForm(ruleFormRef.value)
}

defineExpose({show})
</script>

<style lang="scss" scoped>

</style>