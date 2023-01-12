<template>
  <el-tooltip
      :content="content"
      :effect="effect"
      :placement="placement"
  >
    <el-button :circle="circle" :icon="icon" :type="type" @click="_click"/>
  </el-tooltip>
</template>

<script lang="ts" setup>
const props = defineProps({
  effect: {
    type: String,
    default: 'light'
  },
  content: {
    type: String,
    default: '这是一个按钮'
  },
  placement: {
    type: String,
    default: 'bottom'
  },
  type: {
    type: String,
    default: 'primary'
  },
  icon: {
    type: String,
    default: 'Plus'
  },
  circle: {
    type: Boolean,
    default: false
  }
})
const emits = defineEmits(['click']);
const _click = async () => {
  emits('click')
}
</script>

<style lang="scss" scoped>

</style>