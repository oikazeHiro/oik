// @src/store/menus.ts
import {defineStore} from 'pinia'
import {ref} from 'vue'

export const useMeanStore = defineStore('mean',  {
    // const menuList = ref([])
    // const hasRoute = ref(false)
    state: () => ({
        menuList:[],
        hasRoute:false
    }),

    getters: {
        getMenuList: (menuList) => menuList,
        getHasRoute: (hasRoute) => hasRoute
    },

    actions: {
        setMenuList (menuList) {
        this.menuList = menuList
        },
        setHasRoute(hasRoute) {
            this.hasRoute = hasRoute
        }
    },
    // persist: {
    //     enabled: true, // 开启数据缓存
    // }
})
