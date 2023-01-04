import {defineStore} from 'pinia'

export const User = defineStore('userDto', {

    state: () => ({
        userDto: {
            userId: '',
            username: '',
            deptId: '',
            email: '',
            mobile: '',
            description: '',
            token: '',
            ssex: '',
            avatar: '',
            ip: '',
        }
    }),

    getters: {
        getUser: (userDto) => userDto
    },

    actions: {
        setUserDto(userDto) {
            this.userDto = userDto
        },
    },
    persist: {
        enabled: true, // 开启数据缓存
    }

})
