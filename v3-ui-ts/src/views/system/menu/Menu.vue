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
    <el-table-column prop="menuName" label="menuName"/>
    <el-table-column prop="path" label="path"/>
    <el-table-column label="perms" prop="perms"/>
    <el-table-column prop="perms" label="perms"/>
    <el-table-column label="type">
      <template #default="scope">
        <DictComponents
            ref="dictComponents"
            :keyy="scope.row.type"
            field-name="type"
            table-name="sys_menu"
        ></DictComponents>
      </template>
    </el-table-column>

    <el-table-column label="createTime">
      <template #default="scope">
        <TimeComponents ref="timeComponents"
                        :conversion-type="0"
                        :timestamp=scope.row.createTime></TimeComponents>
      </template>
    </el-table-column>
  </el-table>
  </el-main>
</el-container>
</template>

<script lang="ts" setup>
import {onMounted, reactive} from "vue";
import {menus, query} from '@/entity/interface'
import {getMenus} from '@/api/request/menu'
import TimeComponents from "@/components/TimeComponents.vue";
import DictComponents from "@/components/DictComponents.vue";

const result = reactive<query<menus>>({
  page: {
    records: [],
    total: 0,
    size: 10,
    current: 1,
  },
  param: {
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