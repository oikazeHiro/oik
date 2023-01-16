<template>
  <el-avatar :size="size" :src="src"/>
</template>

<script lang="ts" setup>
const props = defineProps({
  size: {},
  src: {
    type: String,
    default: ''
  }
})
</script>

<style lang="scss" scoped>

</style>