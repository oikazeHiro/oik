<template>
<el-tag v-if="num === 0" :type="zeroType">{{zeroStr}}</el-tag>
<el-tag v-if="num !== 0" :type="oneType">{{oneStr}}</el-tag>
</template>

<script lang="ts" setup>
const props = defineProps({
  num:{
    type:Number,
    default:0
  },
  zeroStr:{
    type:String
  },
  zeroType:{
    type:String,
    default:'danger'
  },
  oneStr:{
    type:String
  },
  oneType:{
    type:String,
    default:'success'
  }
})

</script>

<style lang="scss" scoped>

</style>