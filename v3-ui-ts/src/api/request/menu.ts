import request from '@/api/index'
import {menus, page} from '@/entity/interface'


// export const getMenus = (param: any) => {
//     return request.get<page<menus>>('/api/menu-get', param)
// }
export const getMenus = (param: any) => {
    return request.get2<Array<menus>,page<menus>>('/api/menu-get', param)
}

export const getMenus2 = (param: any) => {
    return request.get2<Array<menus>,page<menus>>('/api/menu-get2', param)
}
export const saveMenu = (data: menus) => {
    data.children = []
    return request.post<any>('/api/menu', data)
}

export const deleteMenu = (param: number | string) => {
    return request.delete("/api/menu/" + param)
}