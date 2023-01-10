<template>
<el-container>
  <el-main>
  <el-table
      :data="result.page.records"
      style="width: 100%"
      row-key="menuId"
      border
      :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
  >
    <el-table-column prop="menuName" label="menuName" />
    <el-table-column prop="path" label="path" />
    <el-table-column prop="perms" label="perms" />
    <el-table-column prop="createTime" label="createTime" />
  </el-table>
  </el-main>
</el-container>
</template>

<script lang="ts" setup>
import {onMounted, reactive} from "vue";
import {query,menus} from '@/entity/interface'
import {getMenus} from '@/api/request/menu'

const result = reactive<query<menus>>({
  page:{
    records:[],
    total:0,
    size:10,
    current:1,
  },
  param:{
    menuName: '',
  }
})

const getList = async () => {
  const res = await getMenus(result)
  result.page = res.data
  console.log(res)
}

onMounted(() => {
  getList()
})

</script>

<style lang="scss" scoped>

</style>