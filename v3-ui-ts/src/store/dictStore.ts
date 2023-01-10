import {defineStore} from 'pinia'
import {dict} from '@/entity/interface'

export const dictStore = defineStore('dict', {
    state: () => ({
        dictList:[],
    }),

    getters: {
        getDictList: (dictList) => dictList
    },

    actions: {
        setDictList(dictList:Array<dict>) {
            this.dictList = dictList
        },
    },
    persist: {
        enabled: true, // 开启数据缓存
    }
})
