<template>
{{str}}
</template>

<script lang="ts" setup>
import {deptStore} from "@/store/deptStore";
import {initDeptCache} from '@/api/request/dept'
import {ref, watchEffect} from "vue";

const deptSto = deptStore()
const props = defineProps({
  deptId:{
    type:String
  }
})
const str = ref('')
const getDeptStr = async () => {
  if (!deptSto.deptList || deptSto.deptList.length === 0){
    await initDeptCache()
  }
  console.log(deptSto.deptList)
  deptSto.deptList.forEach((item)=>{
    if (item.deptId === props.deptId){
      str.value = item.deptName
      console.log(item)
      return
    }
  })
}
watchEffect(() => {
  getDeptStr()
})
</script>

<style lang="sass" scoped>
</style>