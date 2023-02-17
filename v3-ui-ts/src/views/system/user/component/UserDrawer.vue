<template>
  <el-drawer v-model="drawer_" :direction="direction" :title="title" :with-header="with_header">
    <div>
      <el-select
          v-model="value"
          multiple
          placeholder="Select"
          style="width: 240px"
      >
        <el-option
            v-for="item in options"
            :key="item.roleId"
            :label="item.roleName"
            :value="item.roleId"
            :disabled = "item.status === 0"
        />
      </el-select>
    </div>
    <template #footer>
      <div style="flex: auto">
        <el-button @click="drawer_.value = false">取消</el-button>
        <el-button type="primary" @click="confirmClick">确定</el-button>
      </div>
    </template>
  </el-drawer>
</template>

<script lang="ts" setup>
import {reactive, ref} from 'vue'
import {AddRole, viewAddRole} from "@/api/request/role"
import {addRole, role, user} from "@/entity/interface";

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
const data = ref<user>({})
const value = ref<Array<string>>()
const options = ref<Array<role>>()
const show = async (param: user) => {
  data.value = param
  drawer_.value = true
  await initOptions()
}

const initOptions = async () => {
  const res = await viewAddRole(data.value.userId)
  options.value = res.data.allRole
  value.value = res.data.youRole
}

const addroleMode = reactive<addRole>({
  youRole: [],
  userId: ''
})
const confirmClick = async () => {
  addroleMode.youRole = value.value
  addroleMode.userId = data.value.userId
  const res = await AddRole(addroleMode)
  drawer_.value = false
}

defineExpose({show})
</script>

<style lang="scss" scoped>

</style>