<template>
  <el-drawer v-model="drawer" direction="rtl">
    <el-table
        v-loading="loading"
        :data="result.page.records.children"
        height="600"
        style="width: 100%"
    >
      <el-table-column fixed="right" label="Operations" width="150">
        <template #default="scope">
          <el-row justify="space-around">
            <oik-icon-button
                circle
                content="编辑"
                effect="light"
                icon="EditPen"
                placement="bottom-start"
                type="primary"
                @click="editDept(scope.row,1)"/>
            <oik-icon-button circle
                             content="删除"
                             effect="light"
                             icon="Minus"
                             placement="bottom"
                             type="danger"
                             @click="DeleteDept(scope.row)"/>
          </el-row>
        </template>
      </el-table-column>
    </el-table>
    <template #footer>
      <div style="flex: auto">
        <el-button @click="drawer = false">关闭</el-button>
      </div>
    </template>
  </el-drawer>
</template>

<script lang="ts" setup>
import {reactive, ref} from "vue";
import {dict, query} from "@/entity/interface";
import {getDictList2} from "@/api/request/dict";
import OikIconButton from "@/components/button/OikIconButton.vue";

const result = reactive<query<dict>>({
  page: {
    records: [],
    total: 0,
    size: 100,
    current: 1,
  },
  param: {
    fatherId: '',
  }
})
const loading = ref(false)
const drawer = ref(false)
const getList = async () => {
  try {
    loading.value = true
    const res = await getDictList2(result)
    result.page = res.data
    console.log(res)
  } finally {
    loading.value = false
  }
}

const editRow = (data: dict, num?: number) => {
  console.log(data)
}

const show = async (row: dict) => {
  drawer.value = true
  result.param.fatherId = row.dictId
  await getList()
}
</script>

<style lang="scss" scoped>

</style>