<template>
  <el-drawer v-model="drawer" direction="rtl">
    <el-row justify="end">
      <el-col :gutter="20" :span="1">
        <oik-icon-button content="添加"
                         effect="light"
                         icon="Plus"
                         placement="bottom"
                         type="primary"
                         @click="editRow({fatherId:result.param.fatherId},0)"
        />
      </el-col>
    </el-row>
    <el-table
        v-loading="loading"
        :data="result.page.records"
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
                @click="editRow(scope.row,1)"/>
            <oik-icon-button circle
                             content="删除"
                             effect="light"
                             icon="Minus"
                             placement="bottom"
                             type="danger"
                             @click="DeleteRow(scope.row)"/>
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
import {deleteDict, getDictList2} from "@/api/request/dict";
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

const DeleteRow = (data: dict) => {
  const res = deleteDict(data.dictId)
  console.log()
}
//暴露方法
defineExpose({show})
</script>

<style lang="scss" scoped>

</style>