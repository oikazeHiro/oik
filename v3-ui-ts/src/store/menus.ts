// @src/store/menus.ts
import {defineStore} from 'pinia'
import {ref} from 'vue'

export const useMeanStore = defineStore('mean', () => {
    const menuList = ref([])
    const hasRoute = ref(false)

    function setMenuList(menus: any) {
        menuList.value = menus
    }

    function changeRouteStatus(state: any) {
        hasRoute.value = state
        sessionStorage.setItem('hasRoute', state)
    }

    return {
        menuList,
        hasRoute,
        setMenuList,
        changeRouteStatus
    }
})
