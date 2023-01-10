import request from '@/api/index'
import {query,page,menus} from '@/entity/interface'


export const getMenus = (param:query<menus>) => {
    return request.get<page<menus>>('/api/menu-get',param)
}