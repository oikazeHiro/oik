import request from '@/api/index'
import {menus, page} from '@/entity/interface'


export const getMenus = (param: any) => {
    console.log(param)

    return request.get<page<menus>>('/api/menu-get', param)
}
export const getMenus2 = (param: any) => {
    console.log(param)

    return request.get2<page<menus>>('/api/menu-get', param)
}