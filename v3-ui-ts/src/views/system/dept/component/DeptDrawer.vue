<template>
  <el-drawer v-model="drawer" direction="rtl" title="设置二级" :with-header="with_header" size="40%" >
    <el-table
        :data="data.children"
        height="600"
        style="width: 100%"
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
        <el-button type="success" @click="editDept(null,0)">添加</el-button>
        <el-button @click="drawer = false">关闭</el-button>
      </div>
    </template>
  </el-drawer>
  <DeptForm ref="deptForm"  @save-ok="saveOk" />
</template>

<script lang="ts" setup>
import {ref} from "vue";
import {dept} from "@/entity/interface";
import StatusComponents from "@/components/table/StatusComponents.vue";
import TimeComponents from "@/components/table/TimeComponents.vue";
import OikIconButton from "@/components/button/OikIconButton.vue";
import DeptForm from "@/views/system/dept/component/DeptForm.vue";
import {delOne} from "@/api/request/dept";

const props = defineProps({
  with_header: {
    type: Boolean,
    default: false
  },
})
const drawer = ref(false)
const data = ref<dept>({})
const show = (row:dept) => {
  data.value = row
  drawer.value = true
  console.log(data.value)
}
const show2 = (row:dept) => {
  data.value = row
  drawer.value = true
  console.log(data.value)
}
const deptForm = ref<any>()

const editDept = (row: dept, num: number) => {
  if (!row){
    row = {
      parentId:data.value.deptId
    }
  }
  deptForm.value.show(row,num)
  console.log()
}
const DeleteDept = (data: dept) => {
  delOne(data);
  emits("save-ok")
  console.log()
}
const emits = defineEmits(['save-ok']);
const saveOk = () => {
  drawer.value=false
  emits("save-ok")
  console.log()
}
defineExpose({show})
</script>

<style lang="scss" scoped>

</style>