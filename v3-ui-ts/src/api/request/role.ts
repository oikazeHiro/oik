import request from '@/api/index'
import {addRole, page, query, role} from "@/entity/interface";

export const viewAddRole = (param: string) => {
    return request.get<any>('/api/role-user/' + param);
}

export const AddRole = (data: addRole) => {
    return request.post<boolean>('/api/user-role2', data)
}


export const getRoleList = (data:query<role>) => {
    return request.get2<role,page<role>>("/api/roles",data);
}

export const getPermsBtRole = (param:string) => {
    return request.get<Array<string>>('/api/role-menu/'+param)
}

export const saveRoleAndPerms = (data:role) => {
    return request.post('/api/role',data)
}
export const setRoleAndPerms = (data:role) => {
    return request.put('/api/role',data)
}