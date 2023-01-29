<template>
  <el-drawer v-model="drawer_" :title="title" :with-header="false" :direction="direction">
    <span>Hi there!</span>
  </el-drawer>
</template>

<script lang="ts" setup>
import {onMounted, ref, watchEffect} from 'vue'
const props = defineProps({
  title:{
    type:String,
    default:"title"
  },
  with_header:{
    type:Boolean,
    default:false
  },
  direction:{
    default:'rtl'
  }
})
const drawer_ = ref(false)
const data = ref({})
const show = async(param:any) => {
  data.value = param
  drawer_.value = true
  console.log(data.value)
  console.log(drawer_.value)
}
defineExpose({show})
// onMounted(async () => {
//   drawer_.value = props.drawer
//   console.log(props.drawer)
//   console.log(drawer_.value)
// })
// watchEffect(() => {
//   drawer_.value = props.drawer
//   console.log(props.drawer)
//   console.log(drawer_.value)
// })
</script>

<style lang="scss" scoped>

</style>