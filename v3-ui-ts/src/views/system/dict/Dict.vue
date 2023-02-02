<template>
  <el-container>
    <el-main>
      <el-row :gutter="20">
        <el-col :span="4">
          <el-input
              v-model="result.param.valuee"
              class="w-50 m-2"
              placeholder="Type something"
              prefix-icon="Search"
          />
        </el-col>
        <el-col :span="4">
          <oik-icon-button content="搜索"
                           effect="light"
                           icon="Search"
                           placement="bottom"
                           type="primary"
                           @click="getList()"/>
        </el-col>
      </el-row>
      <el-row justify="end">
        <el-col :gutter="20" :span="1">
          <oik-icon-button content="添加"
                           effect="light"
                           icon="Plus"
                           placement="bottom"
                           type="primary"
                           @click="editRow(null,'新增角色')"
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
        <el-table-column fixed="right" label="Operations" width="150">
          <template #default="scope">
            <el-row justify="space-around">
              <oik-icon-button v-if="scope.row.parentId === '0'"
                               circle
                               content="添加路由"
                               effect="light"
                               icon="Plus"
                               placement="bottom-start"
                               type="primary"
                               @click="editRow(scope.row,'编辑角色')"/>
              <oik-icon-button circle
                               content="设置"
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
</template>

<script lang="ts" setup>
import zhCn from "element-plus/lib/locale/lang/zh-cn";
import {onMounted, reactive, ref} from "vue";
import {dict, query} from "@/entity/interface";
import OikIconButton from "@/components/button/OikIconButton.vue";

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
    valuee: '',
    keyy: undefined,
  }
})
const getList = async () => {
  try {
    loading.value = true
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

const editRow = (data: dict, Str: string) => {
  console.log()
}
const SetUpRow = (data: dict) => {
  console.log()
}
const DeleteRow = (data: dict) => {
  console.log()
}

onMounted(() => {
  getList()
})
</script>

<style lang="scss" scoped>

</style>