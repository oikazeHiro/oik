import {defineStore} from 'pinia'

export const deptStore = defineStore('dept', {
    state: () => ({
        deptList:[]
    }),
    getters:{

    },
    actions:{
        setDeptList(data:Array<any>){
            this.deptList = data
        }
    },
    persist: {
        enabled: true, // 开启数据缓存
    }
})