<template>
  <el-container>
    <el-main>
      <el-row :gutter="20">
        <el-col :span="4">
          <el-input
              v-model="result.param.username"
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
                           @click="showDrawer(null)"
          />
        </el-col>
      </el-row>
      <el-table
          v-loading="loading"
          :data="result.page.records"
          height="640"
          row-key="menuId"
          style="width: 100%"
      >
        <el-table-column :index="indexMethod" type="index"/>
        <el-table-column label="角色名" prop="roleName" width="120"/>
        <el-table-column label="备注" prop="remark" show-overflow-tooltip  width="200"/>
        <el-table-column label="状态" width="120">
          <template #default="scope">
            <status-components :num = "scope.row.status" zero-str="无效" one-str="有效"/>
          </template>
        </el-table-column>
        <el-table-column label="创建用户" prop="createUsername" width="120"/>
        <el-table-column label="创建时间" width="180">
          <template #default="scope">
            <time-components :timestamp="scope.row.createTime"/>
          </template>
        </el-table-column>
        <el-table-column label="修改用户" prop="updateUsername" width="120"/>
        <el-table-column label="修改时间" width="200">
            <template #default="scope">
              <time-components :timestamp="scope.row.updateTime"/>
            </template>
        </el-table-column>
        <el-table-column fixed="right" label="Operations" width="150">
          <template #default="scope">
            <el-row justify="space-around">
              <oik-icon-button circle
                               content="设置"
                               effect="light"
                               icon="Setting"
                               placement="bottom"
                               type="success"
                               @click="showDrawer(scope.row)"/>
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
  <RoleDrawer ref="roleDrawer" :with_header="true" title="编辑角色"/>
</template>

<script lang="ts" setup>
import zhCn from "element-plus/lib/locale/lang/zh-cn";
import {onMounted, reactive, ref} from "vue";
import {query, role, } from "@/entity/interface";
import {getRoleList} from "@/api/request/role";
import OikIconButton from "@/components/button/OikIconButton.vue";
import TimeComponents from "@/components/table/TimeComponents.vue";
import RoleDrawer from "@/views/system/role/comoonent/RoleDrawer.vue";
import StatusComponents from "@/components/table/StatusComponents.vue";

const locale = zhCn
const loading = ref(false)
const result = reactive<query<role>>({
  page: {
    records: [],
    total: 0,
    size: 10,
    current: 1,
  },
  param: {
    roleName: '',
  }
})

const getList = async () => {
  try {
    loading.value = true
    const res = await getRoleList(result)
    result.page = res.data
    console.log(result)
  } finally {
    loading.value = false
  }
}

const handleSizeChange = () => {
  getList()
}
const handleCurrentChange = () => {
  getList()
}
const indexMethod = (index: number) => {
  return index + 1
}
const roleDrawer = ref<any>()
const showDrawer = async (row:role) => {
  if (!row) row = result.param
  roleDrawer.value.show(row)
  console.log(row)
}

onMounted(async () => {
  await getList()
})
</script>

<style>
.el-table .warning-row {
  --el-table-tr-bg-color: var(--el-color-warning-light-9);
}

.el-table .success-row {
  --el-table-tr-bg-color: var(--el-color-success-light-9);
}
</style>
