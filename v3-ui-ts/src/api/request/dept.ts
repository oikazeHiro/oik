import request from '@/api/index'
import {page, dept, query} from '@/entity/interface'
import {deptStore} from "@/store/deptStore";

export const deptCache = async (option:number) => {
    return request.get<Array<dept>>('/api/all-dept/'+option)
}

export const deptList = async (param:query<dept>) => {
    return request.get2<dept,page<dept>>('/api/dept',param)
}

export const saveOrSetDept = async  (data:dept) =>{
    if (data.children){
        data.children = undefined
    }
    return request.post<boolean>('/api/dept',data)
}

export const delOne = async (data: dept) => {
    return request.delete('/api/dept/'+data.deptId)
}

export const initDeptCache = async () =>{
    const deptSto = deptStore()
    const res = await deptCache(0)
    deptSto.setDeptList(res.data)
}