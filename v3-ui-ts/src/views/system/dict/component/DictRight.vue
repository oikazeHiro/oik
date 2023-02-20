<template>
  <el-container>
    <el-main>
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
          row-key="menuId"
          style="width: 100%"
      >
        <el-table-column label="类别名称" prop="valuee"></el-table-column>
        <el-table-column label="code" prop="fieldName"></el-table-column>
        <el-table-column label="状态" prop="status"></el-table-column>
        <el-table-column label="排序" prop="sort"></el-table-column>
        <el-table-column fixed="right" label="Operations" width="150">
          <template #default="scope">
            <el-row justify="space-around">
              <oik-icon-button circle
                               content="设置"
                               effect="light"
                               icon="Setting"
                               placement="bottom"
                               type="success"
                               @click="editRow(scope.row,1)"/>
              <oik-icon-button circle
                               content="设置键值"
                               effect="light"
                               icon="Setting"
                               placement="bottom"
                               type="success"
                               @click="SetUpRow(scope.row)"/>
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
      <el-row justify="center" style="padding: 20px">
        <el-config-provider :locale="locale">
          <el-pagination
              v-model:current-page="result.page.current"
              v-model:page-size="result.page.size"
              :background="false"
              :disabled="false"
              :page-sizes="[10, 20, 50, 100]"
              :small="false"
              :total="result.page.total"
              layout="total, sizes, prev, pager, next, jumper"
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
          />
        </el-config-provider>
      </el-row>
    </el-main>
  </el-container>
  <dict-right-from ref="rightFrom"/>
</template>

<script lang="ts" setup>

import OikIconButton from "@/components/button/OikIconButton.vue";
import zhCn from "element-plus/lib/locale/lang/zh-cn";
import {reactive, ref} from "vue";
import {dict, query} from "@/entity/interface";
import {getDictList2} from "@/api/request/dict";
import DictRightFrom from "@/views/system/dict/component/DictRightFrom.vue";

const locale = zhCn
const loading = ref(false)
const result = reactive<query<dict>>({
  page: {
    records: [],
    total: 0,
    size: 10,
    current: 1,
  },
  param: {
    fatherId: '',
  }
})
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
const handleSizeChange = (val: number) => {
  getList()
}
const handleCurrentChange = (val: number) => {
  getList()
}
const rightFrom = ref<any>()
const editRow = (data: dict, num?: number) => {
  rightFrom.value.show(data, num)
  console.log(data)
}
const SetUpRow = (data: dict) => {
  rightFrom.value.show(data)
  console.log()
}
const DeleteRow = (data: dict) => {
  console.log()
}
const show = async (row: dict) => {
  result.param.fatherId = row.dictId
  await getList()
}
//暴露方法
defineExpose({show})
</script>

<style lang="scss" scoped>

</style>