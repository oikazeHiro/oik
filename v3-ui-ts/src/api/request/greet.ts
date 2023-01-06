import request from '@/api/index'

export const getWelcomeMsg = async () => {
    return request.get<any>('/api/greets')
}