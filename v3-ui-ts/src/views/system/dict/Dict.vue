<template>
  <el-container>
    <el-aside width="50%">
      <dict-left ref="dictLift" @selected-row="selRow">
      </dict-left>
    </el-aside>
    <el-main>
      <dict-right ref="dictRight">
      </dict-right>
    </el-main>
  </el-container>
</template>

<script lang="ts" setup>

import DictLeft from "@/views/system/dict/component/DictLeft.vue";
import DictRight from "@/views/system/dict/component/DictRight.vue";
import {dict} from "@/entity/interface";
import {ref} from "vue";

const dictLift = ref<any>()
const dictRight = ref<any>()
const selRow = async (row: dict) => {
  dictRight.value.show(row)
}
</script>

<style lang="scss" scoped>

</style>