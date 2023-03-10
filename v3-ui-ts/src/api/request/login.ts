import request from '@/api/index'
import {LoginFrom, menus, userDto} from '@/entity/interface'

export const login = (params: LoginFrom) => {
  return request.post<userDto>('/api/user/login', params)
}

export const getMenus = () => {
  return request.get<menus>('/api/getMenus')
}

export const logout = () => {
  return request.get<any>("/api/user/logout")
}

export const getUserDto = () => {
  return request.get<any>("/api/user/get-user")
}

