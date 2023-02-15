import request from '@/api/index'
import {page, query, user} from '@/entity/interface'

export const getUsers = async (param: query<user>) => {
    return request.get2<user,page<user>>('/api/user/users', param)
}

export const setUser = async (data : user) => {
    return request.put<user>('/api/user/update',data)
}
export const register = async (data : user) => {
    return request.post<user>('/api/user/register',data)
}

export const del = async (param : string) => {
    return request.delete<any>('/api/user/delete'+'/'+param)
}

export const getUserList = async (param: string) => {
    return request.get<Array<user>>('/api/user/get-user-list/'+param)
}