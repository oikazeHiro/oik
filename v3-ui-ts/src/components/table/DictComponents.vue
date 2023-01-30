<template>
  <span>
    {{ dictOne.dict.valuee }}
  </span>
</template>

<script lang="ts" setup>
import {getDictByFieldName, getDictByKeyy, getDictByTableName} from '@/api/request/dict'
import {reactive, watchEffect} from "vue";

const props = defineProps({
  tableName: {
    type: String
  },
  fieldName: {
    type: String
  },
  keyy: {
    type: String
  }
})
const dictOne = reactive({
  dict: {
    value: ""
  }
})
const getDictValue = async () => {
  if (!props.tableName) return;
  if (!props.fieldName) return;
  if (!props.keyy) return;
  const e = getDictByTableName(props.tableName)
  if (!e) return;
  if (!e.children) return
  const t = getDictByFieldName(e, props.fieldName)
  if (!t) return;
  if (!t.children) return;
  dictOne.dict = getDictByKeyy(t, props.keyy)
}

watchEffect(() => {
  getDictValue()
})

</script>

<style lang="scss" scoped>

</style>