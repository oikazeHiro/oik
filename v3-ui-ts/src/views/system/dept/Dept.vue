<template>
  <el-container>
    <el-main>
      <el-row :gutter="20">
        <el-col :span="4">
          <el-input
              v-model="result.param.deptName"
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
                           @click="editDept({},0)"
          />
        </el-col>
      </el-row>
      <el-table
          v-loading="loading"
          :data="result.page.records"
          height="600"
          style="width: 70%;margin: auto"
      >
        <el-table-column label="部门名称" prop="deptName" width="120"/>
        <el-table-column label="状态" prop="status" width="120">
          <template #default="scope">
            <status-components :num = "scope.row.status" zero-str="无效" one-str="有效"/>
          </template>
        </el-table-column>
        <el-table-column label="排序" prop="orderNum" width="120"/>
        <el-table-column label="创建人" prop="createUsername" width="120"/>
        <el-table-column label="创建时间" width="180">
          <template #default="scope">
            <TimeComponents :timestamp="scope.row.createTime"/>
          </template>
        </el-table-column>
        <el-table-column label="更新人" prop="updateUsername" width="120"/>
        <el-table-column label="更新时间" width="180">
          <template #default="scope">
            <TimeComponents :timestamp="scope.row.updateTime"/>
          </template>
        </el-table-column>
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
                               content="设置"
                               effect="light"
                               icon="Setting"
                               placement="bottom"
                               type="success"
                               @click="SetUpDept(scope.row)"/>
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
      <el-row justify="center" style="padding: 20px">
        <el-config-provider :locale="locale">
          <el-pagination
              v-model:current-page="result.page.current"
              v-model:page-size="result.page.size"
              :background="false"
              :disabled="false"
              :page-sizes="[10, 30, 50, 100]"
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
  <dept-form ref="deptForm" @save-ok="getList"/>
  <dept-drawer ref="deptDrawer" @save-ok="getList" :with_header="true"/>
</template>

<script lang="ts" setup>
import zhCn from "element-plus/lib/locale/lang/zh-cn";
import {onMounted, reactive, ref} from "vue";
import {dept, query} from "@/entity/interface";
import {deptList,delOne} from "@/api/request/dept"
import OikIconButton from "@/components/button/OikIconButton.vue";
import StatusComponents from "@/components/table/StatusComponents.vue";
import DeptForm from "@/views/system/dept/component/DeptForm.vue";
import DeptDrawer from "@/views/system/dept/component/DeptDrawer.vue";
import TimeComponents from "@/components/table/TimeComponents.vue";

const locale = zhCn
const loading = ref(false)
const result = reactive<query<dept>>({
  page: {
    records: [],
    total: 0,
    size: 30,
    current: 1,
  },
  param: {
    deptName: '',
  }
})
const getList = async () => {
  try {
    loading.value = true
    const res = await deptList(result);
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

const deptForm = ref<any>()
const deptDrawer = ref<any>()

const editDept = (data: dept, num: number) => {
  deptForm.value.show(data,num)
  console.log()
}
const SetUpDept = (data: dept) => {
  deptDrawer.value.show(data)
  console.log()
}
const DeleteDept = (data: dept) => {
  delOne(data);
  getList()
  console.log()
}

onMounted(() => {
  getList()
})
</script>

<style lang="scss" scoped>

</style>