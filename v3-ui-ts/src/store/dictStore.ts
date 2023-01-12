import {defineStore} from 'pinia'
import {dict, result} from '@/entity/interface'

export const dictStore = defineStore('dict', {
    state: () => ({
        dictList: []
    }),

    getters: {
        getDictList: (dictList) => dictList
    },

    actions: {
        setDictList(dictList: Array<dict>) {
            this.dictList = dictList
        },
        setDictList2(res: result<Array<dict>>) {
            this.dictList = res.data
        }

    },
    persist: {
        enabled: true, // 开启数据缓存
    }
})
