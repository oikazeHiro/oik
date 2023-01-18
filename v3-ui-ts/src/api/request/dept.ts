import request from '@/api/index'
import {page, dept, query} from '@/entity/interface'
import {deptStore} from "@/store/deptStore";

const deptCache = async () => {
    return request.get<Array<dept>>('/api/all-dept')
}

const deptList = async (param:query<dept>) => {
    return request.get2<dept,Array<dept>>('/api/dept',param)
}

export const initDeptCache = async () =>{
    const deptSto = deptStore()
    const res = await deptCache()
    deptSto.setDeptList(res.data)
}