<template>
  <el-dialog v-model="dialogFormVisible" :title="operate === 0 ? '添加':'修改'">
    <el-form
        ref="ruleFormRef"
        :model="data"
        status-icon
        :rules="rules"
        label-width="80px"
    >
      <el-form-item label="名称" prop="deptName">
        <el-input v-model="data.deptName" clearable/>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-radio-group v-model="data.status" class="ml-4">
          <el-radio :label="0" size="large">无效</el-radio>
          <el-radio :label="1" size="large">有效</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="排序" prop="orderNum">
        <el-input-number v-model="data.orderNum" :step="1" step-strictly/>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
       <el-button type="primary" @click="submitForm(ruleFormRef)">提交</el-button>
        <el-button type="primary" @click="dialogFormVisible = false">
          取消
        </el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script lang="ts" setup>

import {dept} from "@/entity/interface";
import {reactive, ref} from "vue";
import {FormInstance} from "element-plus";
import {saveOrSetDept} from "@/api/request/dept"

const operate = ref(0)
const data = ref<dept>({})
const dialogFormVisible = ref(false)
const show = (row: dept, num: number) => {
  data.value = row
  operate.value = num
  dialogFormVisible.value = true
  console.log(data.value)
}

const validateDeptName = (rule: any, value: any, callback: any) => {
  if (value === '' || !value) {
    callback(new Error('请输入部门名'))
  } else if (value.length < 2) {
    callback(new Error('名称不能小于2'))
  } else if (value.length > 6) {
    callback(new Error('名称不能超过6'))
  } else {
    callback()
  }
}


const rules = reactive({
  status: [
    {
      required: true,
      message: '请选择状态',
      trigger: 'change',
    },
  ],
  orderNum: [
    {
      required: true,
      message: '请填写排序号',
      trigger: 'change',
    },
  ],
  deptName:[{validator: validateDeptName, trigger: 'blur'}]
})
const ruleFormRef = ref<FormInstance>()
const emits = defineEmits(['save-ok']);
const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate(async (valid, fields) => {
    if (valid) {
      console.log('submit')
      try {
        await saveOrSetDept(data.value)
        emits("save-ok")
      }finally {
        dialogFormVisible.value = false
      }
    } else {
      console.log('error submit!', fields)
    }
  })
}
//暴露方法
defineExpose({show})
</script>

<style lang="scss" scoped>

</style>