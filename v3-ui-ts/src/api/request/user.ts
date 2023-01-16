import request from '@/api/index'
import {page, user} from '@/entity/interface'

export const getUsers = async (param: any) => {
    return request.get2<page<user>>('/api/user/users', param)
}