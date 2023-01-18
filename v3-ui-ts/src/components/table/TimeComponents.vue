<template>
  <span>
    {{ timeStr }}
  </span>
</template>

<script lang="ts" setup>
import {ref, watchEffect} from "vue";

const props = defineProps({
  timestamp: {
    type: Number,
    default: 0,
  },
  ConversionType: {
    type: Number,
    default: 0
  }
})
const getYear = (date: Date) => {
  return date.getFullYear()
}
const getMonth = (date: Date) => {
  const num = date.getMonth() + 1;
  if (num < 10)
    return '0' + num
  else return num
}
const getDate = (date: Date) => {
  return date.getDate() < 10 ? '0' + date.getDate() : date.getDate()
}
const getHours = (date: Date) => {
  return date.getHours() < 10 ? '0' + date.getHours() : date.getHours()
}
const getMinutes = (date: Date) => {
  return date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()
}
const getSeconds = (date: Date) => {
  return date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds()
}
const timeStr = ref<string>('')
const getTimeStr = () => {
  //
  if (!props.timestamp) {
    return
  }
  const date = new Date(props.timestamp);
  const type = props.ConversionType
  switch (type) {
    case 0:
      timeStr.value = getYear(date) + '-' + getMonth(date) + '-' + getDate(date) + ' ' + getHours(date) + ':' + getMinutes(date) + ':' + getSeconds(date)
      break;
    case 1:
      timeStr.value = getYear(date) + '/' + getMonth(date) + '/' + getDate(date) + ' ' + getHours(date) + ':' + getMinutes(date) + ':' + getSeconds(date)
      break;
    case 2:
      timeStr.value = getYear(date) + '-' + getMonth(date) + '-' + getDate(date)
      break;
    case 3:
      timeStr.value = getYear(date) + '/' + getMonth(date) + '/' + getDate(date)
      break;
  }
}
watchEffect(() => {
  getTimeStr()
})
</script>

<style lang="scss" scoped>

</style>