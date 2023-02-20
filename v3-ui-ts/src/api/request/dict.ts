import request from '@/api/index'
import {dict, page, query} from '@/entity/interface'
import {dictStore} from '@/store/dictStore'
import {reactive} from "vue";

export const getDictionary = () => {
    return request.get<Array<dict>>('/api/dicts');
}

export const setDictionary = (data: Array<dict>) => {
    const dict = dictStore();
    dict.setDictList(data);
}

export const getDictByTableName = (tableName: string): any => {
    const dictList = dictStore().dictList
    const dict = reactive({
        res: {}
    })
    dictList.forEach((e, index, array) => {
        if (e.tableName === tableName) {
            dict.res = e
            return e;
        }
    })
    return dict.res
}

export const getDictByFieldName = (dict: any, fieldName: string): any => {
    const d = reactive({
        res: {}
    })
    dict.children.forEach((e, index, array) => {
        if (e.fieldName === fieldName) {
            d.res = e
            return e;
        }
    })
    return d.res
}

export const getDictByKeyy = (dict: any, keyy: string): any => {
    const d = reactive({
        res: {}
    })
    dict.children.forEach((e, index, array) => {
        if (e.keyy == keyy) {
            d.res = e
            return e;
        }
    })
    return d.res
}


export const getDictList2 = async (data: query<dict>) => {
    return request.get2<dict, page<dict>>('/api/dict-list2', data)
}


export const save = async (data: dict) => {
    return request.post<boolean>('/api/dict', data)
}
export const update = async (data: dict) => {
    return request.put<boolean>('/api/dict', data)
}