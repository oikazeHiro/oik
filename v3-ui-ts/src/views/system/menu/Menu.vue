<template>
  <el-container>
    <el-main>
      <div style="padding: 20px">
        <el-row :gutter="20">
          <el-col :span="4">
            <el-input
                v-model="result.param.menuName"
                class="w-50 m-2"
                placeholder="Type something"
                prefix-icon="Search"
            />
          </el-col>
          <el-col :span="4">
            <oik-icon-button content="搜索"
                             effect="light"
                             icon="Search"
                             placement="bottom"
                             type="primary"
                             @click="getList()"/>
          </el-col>
        </el-row>
        <el-row :gutter="20" justify="end">
          <el-col :span="2">
            <oik-icon-button content="添加"
                             effect="light"
                             icon="Plus"
                             placement="bottom"
                             type="primary"
                             @click="addSubMenu(null,0)"/>
          </el-col>
        </el-row>
      </div>
      <el-table
          v-loading="loading"
          :data="result.page.records"
          :tree-props="{ children: 'children', hasChildren: 'hasChildren' }"
          height="600"
          row-key="menuId"
          style="width: 100%"
      >
        <el-table-column label="名称" prop="menuName" width="180"/>
        <el-table-column label="图标" width="120">
          <template #default="scope">
            <component :is="scope.row.icon" class="el-icon"/>
          </template>
        </el-table-column>
        <el-table-column label="path" prop="path" width="180"/>
        <el-table-column label="组件" prop="component" width="200"/>
        <el-table-column label="权限标识" prop="perms" width="140"/>
        <el-table-column label="类型" width="80">
          <template #default="scope">
            <el-tag :type="scope.row.type === '0'?'success':'danger'">
              <DictComponents
                  ref="dictComponents"
                  :keyy="scope.row.type"
                  field-name="type"
                  table-name="sys_menu">
              </DictComponents>
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="创建时间" width="200">
          <template #default="scope">
            <TimeComponents ref="timeComponents"
                            :timestamp=scope.row.createTime
                            conversion-type="0"></TimeComponents>
          </template>
        </el-table-column>
        <el-table-column label="修改时间" width="200">
          <template #default="scope">
            <TimeComponents ref="timeComponents"
                            :timestamp=scope.row.updateTime
                            conversion-type="0"></TimeComponents>
          </template>
        </el-table-column>
        <el-table-column label="创建用户名" prop="createUsername" width="120"/>
        <el-table-column label="修改用户名" prop="updateUsername" width="120"/>
        <el-table-column fixed="right" label="Operations" width="150">
          <template #default="scope">
            <el-row justify="space-around">
              <oik-icon-button v-if="scope.row.parentId === 0"
                               circle
                               content="添加路由"
                               effect="light"
                               icon="Plus"
                               placement="bottom-start"
                               type="primary"
                               @click="addSubMenu(scope.row)"/>
              <oik-icon-button v-if="scope.row.parentId !=0 && scope.row.path"
                               circle
                               content="添加权限"
                               effect="light"
                               icon="Plus"
                               placement="bottom-start"
                               type="warning"
                               @click="addSubMenu(scope.row,0)"/>
              <oik-icon-button circle
                               content="编辑"
                               effect="light"
                               icon="Edit"
                               placement="bottom"
                               type="success"
                               @click="addSubMenu(scope.row,1)"/>
              <oik-icon-button circle
                               content="删除"
                               effect="light"
                               icon="Minus"
                               placement="bottom-end"
                               type="danger"
                               @click="DeleteRow(scope.row)"/>
            </el-row>
          </template>
        </el-table-column>
      </el-table>
      <el-row justify="center" style="padding: 20px">
        <el-config-provider :locale="locale">
          <el-pagination
              v-model:current-page="result.page.current"
              v-model:page-size="result.page.size"
              :background="false"
              :disabled="false"
              :page-sizes="[10, 20, 50, 100]"
              :small="false"
              :total="result.page.total"
              layout="total, sizes, prev, pager, next, jumper"
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
          />
        </el-config-provider>
      </el-row>
    </el-main>
  </el-container>
  <MenuForm ref="menuForm" @saveOk="saveOk"/>
</template>

<script lang="ts" setup>
import {onMounted, reactive, ref} from "vue";
import {menus, query} from '@/entity/interface'
import {deleteMenu, getMenus2} from '@/api/request/menu'
//引入vue方法
import {ElConfigProvider} from 'element-plus'
//中文包
import zhCn from "element-plus/lib/locale/lang/zh-cn"
import OikIconButton from "@/components/button/OikIconButton.vue";
import TimeComponents from "@/components/table/TimeComponents.vue";
import DictComponents from "@/components/table/DictComponents.vue";
import MenuForm from "@/views/system/menu/component/MenuForm.vue";


const locale = zhCn
const loading = ref(false)
const result = reactive<query<menus>>({
  page: {
    records: [],
    total: 0,
    size: 10,
    current: 1,
  },
  param: {
    menuName: '',
  }
})

const getList = async () => {
  loading.value = true
  const res = await getMenus2(result)
  result.page = res.data
  loading.value = false
}
const handleSizeChange = (val: number) => {
  getList()
}
const handleCurrentChange = (val: number) => {
  getList()
}
const menuForm = ref<any>();
const addSubMenu = async (data: menus, type?: number) => {
  if (data != null) data.children = null
  menuForm.value.show(data, type)
}

const DeleteRow = async (data: menus) => {
  console.log(data)
  const res = deleteMenu(data.menuId)
  console.log(res)
}
const saveOk = async () => {
  await getList()
  console.log('ok')
}
onMounted(() => {
  getList()
})

</script>

<style lang="scss" scoped>

</style>