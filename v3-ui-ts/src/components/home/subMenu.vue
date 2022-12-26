<template>
  <div></div>
</template>

<script setup lang="ts">
import {reactive} from "vue";
import {menus} from "@/entity/interface"

const props = defineProps<{
 menu: menus
}>()
</script>
