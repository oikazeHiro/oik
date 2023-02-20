<template>
  <el-dialog v-model="dialogFormVisible" :title="operate === 0?'添加':'设置'">
    <el-form
        ref="ruleFormRef"
        v-loading="loading"
        :model="dictDto"
        :rules="rules"
        class="demo-ruleForm"
        label-width="120px"
        status-icon
    >
      <el-form-item label="业务名称" prop="valuee">
        <el-input v-model="dictDto.valuee" autocomplete="off"/>
      </el-form-item>
      <el-form-item label="code" prop="tableName">
        <el-input v-model="dictDto.tableName" autocomplete="off"/>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="dictDto.status" clearable placeholder="Select">
          <el-option
              v-for="item in options2"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="排序" prop="sort">
        <el-input-number v-model="dictDto.sort" :step="1" step-strictly/>
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
import {reactive, ref} from "vue";
import {dict} from "@/entity/interface";
import {FormInstance} from "element-plus";
import {save, update} from '@/api/request/dict'

const dialogFormVisible = ref(false)
const ruleFormRef = ref<FormInstance>()
const dictDto = ref<dict>({})

const options2 = [
  {
    value: 0,
    label: '无效',
  },
  {
    value: 1,
    label: '有效',
  },
]

const validateValuee = (rule: any, value: any, callback: any) => {
  if (value === '' || !value) {
    callback(new Error('不能为空'))
  } else if (value.length < 2) {
    callback(new Error('字数不能小于2'))
  } else if (value.length > 10) {
    callback(new Error('字数不能超过10'))
  } else {
    callback()
  }
}
//   const validateTableName = (rule: any, value: any, callback: any) => {
//   if (value === '' || !value){
//     callback(new Error('不能为空'))
//   } else if (value.length < 2) {
//     callback(new Error('字数不能小于2'))
//   } else if (value.length > 10) {
//     callback(new Error('字数不能超过10'))
//   } else {
//     callback()
//   }
// }
const rules = reactive({
  status: [
    {
      required: true,
      message: '请选择状态',
      trigger: 'change',
    },
  ],
  sort: [
    {
      required: true,
      message: '请填写排序号',
      trigger: 'change',
    },
  ],
  valuee: [{validator: validateValuee, trigger: 'blur'}],
  tableName: [{validator: validateValuee, trigger: 'blur'}],
})
const operate = ref(0)
const show = (data: dict, num?: number) => {
  dictDto.value = data
  dialogFormVisible.value = true
  operate.value = num
}
const emits = defineEmits(['save-ok']);
const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate(async (valid, fields) => {
    if (valid) {
      try {
        dictDto.value.fatherId = '0'
        if (operate.value === 0) {
          await save(dictDto.value)
        } else {
          await update(dictDto.value)
        }
        emits("save-ok")
      } finally {
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