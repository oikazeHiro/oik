<!-- eslint-disable vue/multi-word-component-names -->
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
                           @click="showForm(null,0)"
          />
        </el-col>
      </el-row>
      <el-table
          v-loading="loading"
          :data="result.page.records"
          :row-class-name="tableRowClassName"
          height="640"
          row-key="menuId"
          style="width: 100%"
          border
      >
        <el-table-column :index="indexMethod" type="index"/>
        <el-table-column label="用户名" prop="username" width="120"/>
        <el-table-column label="部门" prop="deptId" width="120" >
          <template #default="scope">
            <DeptComponent :dept-id="scope.row.deptId" />
          </template>
        </el-table-column>
        <el-table-column label="邮箱" prop="email" show-overflow-tooltip width="120"/>
        <el-table-column label="联系电话" prop="mobile" width="120"/>
        <el-table-column label="状态" prop="status" width="120"/>
        <el-table-column label="最近访问时间" width="200">
          <template #default="scope">
            <TimeComponents :timestamp="scope.row.lastLoginTime"/>
          </template>
        </el-table-column>
        <el-table-column label="描述" prop="description" width="180"/>
        <el-table-column label="头像" width="120">
          <template #default="scope">
            <OikAvatar :src="scope.row.avatar" size="default"/>
          </template>
        </el-table-column>
        <el-table-column label="创建者" prop="createUsername" width="120"/>
        <el-table-column label="创建时间" width="200">
          <template #default="scope">
            <TimeComponents :timestamp="scope.row.createTime"/>
          </template>
        </el-table-column>
        <el-table-column label="修改者" prop="updateUsername" width="120"/>
        <el-table-column label="修改时间" width="200">
          <template #default="scope">
            <TimeComponents :timestamp="scope.row.updateTime"/>
          </template>
        </el-table-column>
        <el-table-column fixed="right" label="Operations" width="150">
          <template #default="scope">
            <el-row justify="space-around">
              <oik-icon-button circle
                               content="编辑"
                               effect="light"
                               icon="Edit"
                               placement="bottom"
                               type="success"
                               @click="showForm(scope.row,1)"/>
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
  <user-from ref="userForm" @save-ok="getList" />
</template>

<script lang="ts" setup>
import {onMounted, reactive, ref} from "vue";
import {query, user} from "@/entity/interface";
import {del, getUsers} from '@/api/request/user'
import OikIconButton from "@/components/button/OikIconButton.vue";
import zhCn from "element-plus/lib/locale/lang/zh-cn"
import OikAvatar from "@/components/table/OikAvatar.vue";
import TimeComponents from "@/components/table/TimeComponents.vue";
import UserFrom from "@/views/system/user/component/UserFrom.vue";
import DeptComponent from "@/components/table/DeptComponent.vue";

const locale = zhCn
const loading = ref(false)
const result = reactive<query<user>>({
  page: {
    records: [],
    total: 0,
    size: 10,
    current: 1,
  },
  param: {
    username: '',
  }
})
const getList = async () => {
  try {
    loading.value = true
    const res = await getUsers(result)
    result.page = res.data
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
const indexMethod = (index: number) => {
  return index + 1
}

const tableRowClassName = ({
                             // eslint-disable-next-line @typescript-eslint/no-unused-vars
                             row,
                             rowIndex,
                           }: {
  row: user
  rowIndex: number
}) => {
  if (rowIndex % 2 != 0) {
    return 'warning-row'
  } else if (rowIndex % 2 === 0) {
    return 'success-row'
  }
  return ''
}

const userForm = ref<any>()

const showForm = (data:user,type:number) =>{
    userForm.value.show(data,type)
}
const DeleteRow = async (data?: any) => {
  const res = await del(data.userId)
}
onMounted(async () => {
  await getList()
})
</script>

<style lang="scss" scoped>
.el-table .warning-row {
  --el-table-tr-bg-color: var(--el-color-warning-light-9);
}

.el-table .success-row {
  --el-table-tr-bg-color: var(--el-color-success-light-9);
}
</style>
