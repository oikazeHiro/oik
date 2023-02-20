<template>
  <el-container>
    <el-main>
      <div>
        <el-card v-for="item in testList" :key="item.index" class="box-card">
          <el-row v-for="test in item" :key="test.index" :gutter="20">
            <el-col :span="10">{{ test.key }} : {{ test.value }}</el-col>
          </el-row>
        </el-card>
      </div>
    </el-main>
  </el-container>
</template>

<script lang="ts" setup>

import chatMsgImpl from "@/entity/ChatMsg";
import {ref} from "vue";

interface test {
  key?: string
  value?: string
}

const testList = ref<Array<Array<test>>>([])
const getSocketData = (res) => {
  // const userDto = User().userDto
  const oneMsg = JSON.parse(res.detail.data) as chatMsgImpl
  console.log(oneMsg)
  if (oneMsg.code === 6) {
    // msgData.list.push(JSON.parse(oneMsg.expandMsg))
    console.log(JSON.parse(oneMsg.expandMsg))
    testList.value.push(JSON.parse(oneMsg.expandMsg))
    console.log(testList.value)
  }
}

window.addEventListener('onmessageWS', getSocketData)
</script>

<style lang="scss" scoped>

</style>