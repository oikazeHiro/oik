<template>
  <el-drawer v-model="drawer_" :direction="direction" :title="title" :with-header="with_header" size="40%">
      <el-form
          ref="ruleFormRef"
          :model="data"
          status-icon
          :rules="rules"
          label-width="80px"
          class="demo-ruleForm"
      >
        <el-form-item label="角色名" prop="username">
          <el-input v-model="data.roleName" clearable/>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="data.remark" clearable/>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-input v-model="data.status" clearable/>
<!--          <el-switch v-model="data.status" />-->
        </el-form-item>
        <el-form-item label="业务数据权限" prop="dataScope">
          <el-input v-model="data.dataScope" clearable/>
        </el-form-item>
        <el-form-item label="权限树">
          <el-tree
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
import {getPermsBtRole} from "@/api/request/role"
import {menus, query, role} from "@/entity/interface";
import type { FormInstance } from 'element-plus'
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
    default: 'rtl'
  }
})
const drawer_ = ref(false)
const data = ref<role>({})
const treeProps = {
  label:'menuName',
  children:'children'
}
const show = async (param: role) => {
  data.value = param
  drawer_.value = true
  if (data.value.roleId){
    const res = await getPermsBtRole(data.value.roleId)
    data.value.perms = res.data
  }
  await initOptions()
}
const menuResult = reactive<query<menus>>({
  page: {
    records: [],
    total: 0,
    size: 200,
    current: 1,
  },
  param: {
  }
})
const initOptions = async () => {
  const res = await getMenus2(menuResult)
  menuResult.page = res.data
  if (data.value.perms){
    treeRef.value!.setCheckedKeys(data.value.perms,false)
  }
  console.log()
}
const getCheckedKeys = () => {
  console.log(treeRef.value!.getCheckedKeys(false))
}
const ruleFormRef = ref<FormInstance>()
const rules = reactive({})
const confirmClick = async () => {
  console.log()
  drawer_.value = false
}

defineExpose({show})
</script>

<style lang="scss" scoped>

</style>