import request from '@/api/index'
import {dict} from '@/entity/interface'
import {dictStore} from '@/store/dictStore'

export const getDictionary = () => {
    return request.get<Array<dict>>('/api/dicts');
}

export const setDictionary = (data:Array<dict>) => {
    const dict = dictStore();
    dict.setDictList(data);
}