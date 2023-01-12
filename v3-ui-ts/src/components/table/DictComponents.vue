<template>
  <span>
    {{ dictOne.dict.valuee }}
  </span>
</template>

<script lang="ts" setup>
import {getDictByFieldName, getDictByKeyy, getDictByTableName, getDictionary, setDictionary} from '@/api/request/dict'
import {reactive, watchEffect} from "vue";
import {dictStore} from '@/store/dictStore'

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
  await initDict()
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

const initDict = async () => {
  const dictSto = dictStore()
  if (dictSto.dictList == null || !dictSto.dictList || dictSto.dictList.length == 0) {
    const res = await getDictionary()
    setDictionary(res.data)
  }
}

watchEffect(() => {
  getDictValue()
})

</script>

<style lang="scss" scoped>

</style>