<template>
  <el-table
      :data="tableData"
      :row-class-name="tableRowClassName"
      style="width: 100%"
  >
    <el-table-column label="Date" prop="date" width="180"/>
    <el-table-column label="Name" prop="name" width="180"/>
    <el-table-column label="Address" prop="address"/>
  </el-table>
</template>

<script lang="ts" setup>
interface User {
  date: string
  name: string
  address: string
}

const tableRowClassName = ({
                             row,
                             rowIndex,
                           }: {
  row: User
  rowIndex: number
}) => {
  if (rowIndex === 1) {
    return 'warning-row'
  } else if (rowIndex === 3) {
    return 'success-row'
  }
  return ''
}

const tableData: User[] = [
  {
    date: '2016-05-03',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-02',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-04',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-01',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
]
</script>

<style>
.el-table .warning-row {
  --el-table-tr-bg-color: var(--el-color-warning-light-9);
}

.el-table .success-row {
  --el-table-tr-bg-color: var(--el-color-success-light-9);
}
</style>
