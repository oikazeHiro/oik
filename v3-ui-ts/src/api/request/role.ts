import request from '@/api/index'
import {addRole} from "@/entity/interface";

export const viewAddRole = (param: string) => {
    return request.get<any>('/api/role-user/' + param);
}

export const AddRole = (data: addRole) => {
    return request.post<boolean>('/api/user-role2', data)
}