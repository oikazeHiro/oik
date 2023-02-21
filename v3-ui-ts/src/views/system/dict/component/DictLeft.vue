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
                           @click="editRow({},0)"
          />
        </el-col>
      </el-row>
      <el-table
          v-loading="loading"
          :data="result.page.records"
          height="600"
          row-key="menuId"
          style="width: 100%"
          @row-click="clickRow"
      >
        <el-table-column label="业务名称" prop="valuee"></el-table-column>
        <el-table-column label="code" prop="tableName"></el-table-column>
        <el-table-column label="状态" prop="status"></el-table-column>
        <el-table-column label="排序" prop="sort"></el-table-column>
        <el-table-column fixed="right" label="Operations" width="150">
          <template #default="scope">
            <el-row justify="space-around">
              <oik-icon-button circle
                               content="设置"
                               effect="light"
                               icon="Edit"
                               placement="bottom"
                               type="success"
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
  <dict-lift-from ref="leftFrom" @save-ok="getList"/>
</template>

<script lang="ts" setup>
import zhCn from "element-plus/lib/locale/lang/zh-cn";
import {onMounted, reactive, ref} from "vue";
import {dict, query} from "@/entity/interface";
import OikIconButton from "@/components/button/OikIconButton.vue";
import {deleteDict, getDictList2} from '@/api/request/dict'
import DictLiftFrom from "@/views/system/dict/component/DictLiftFrom.vue";

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
    fatherId: '0',
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

const leftFrom = ref<any>()
const editRow = (data: dict, num?: number) => {
  leftFrom.value.show(data, num)
  console.log(data)
}
const SetUpRow = (data: dict) => {
  console.log()
}
const DeleteRow = (data: dict) => {
  const res = deleteDict(data.dictId)
  console.log()
}


const emits = defineEmits(['selectedRow']);
const clickRow = async (row) => {
  emits('selectedRow', row)
}


onMounted(() => {
  getList()
})
</script>

<style lang="scss" scoped>

</style>