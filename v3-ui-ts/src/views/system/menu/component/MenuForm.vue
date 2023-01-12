<template>
  <el-dialog v-model="dialogFormVisible" :title="title">
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="submit">Cancel</el-button>
        <el-button type="primary" @click="dialogFormVisible = false">
          Confirm
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>
import {ref} from "vue";
import {menus} from "@/entity/interface";
import {saveMenu} from '@/api/request/menu'

const dialogFormVisible = ref(false)
const title = ref<string>('')
const type = ref(0)
const formData = ref<menus>({})
const show = async (data: menus, type: number) => {
  await formDataInit()
  if (!type) type = 0;
  title.value = type === 0 ? '添加' : '编辑'
  if (type === 0) {
    if (data != null) {
      formData.value.parentId = data.parentId
    }
  } else {
    formData.value = data
  }
  dialogFormVisible.value = true
  console.log(data)
}
const formDataInit = async () => {
  formData.value = {
    menuId: null,
    parentId: 0,
    menuName: '',
    path: '',
    component: '',
    icon: '',
    type: '',
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
const submit = async () => {
  const res = await saveMenu(formData.value)
  console.log(formData)
  emits("save-ok")
  dialogFormVisible.value = false
}
//暴露方法
defineExpose({show})
</script>

<style lang="scss" scoped>

</style>