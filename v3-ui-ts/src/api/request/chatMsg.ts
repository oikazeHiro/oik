import request from '@/api/index'
import {page, query} from "@/entity/interface";
import chatMsgImpl from "@/entity/ChatMsg";

export const chatMsgList = async (param: query<chatMsgImpl>) => {
    return request.get2<chatMsgImpl, page<chatMsgImpl>>('/api/chatMsg', param)
}

export const sendPrivateMsg = async (data: chatMsgImpl) => {
    return request.post('/api/send-private-msg', data)
}