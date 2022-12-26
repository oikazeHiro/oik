import {defineStore} from 'pinia'
import {ref} from 'vue'
import {userDto} from "@/entity/interface"

export const User = defineStore('userDto',() => {
    const userId  = ref<string>()
    const username = ref<string>()
    const deptId = ref<string>()
    const email = ref<string>()
    const mobile = ref<string>()
    const description = ref<string>()
    const token = ref<string>()
    const ssex = ref<string>()
    const avatar = ref<string>()
    const ip = ref<string>()

    function setUserDto(userDto: userDto) {
        userId.value = userDto.userId
        username.value = userDto.username
        deptId.value = userDto.deptId
        email.value = userDto.email
        mobile.value = userDto.mobile
        description.value = userDto.description
        token.value = userDto.token
        ssex.value = userDto.ssex
        avatar.value = userDto.avatar
        ip.value = userDto.ip
    }

    function setUserId(userId2: string) {
        userId.value = userId2
    }

    return {
        userId,
        username,
        deptId,
        email,
        mobile,
        description,
        token,
        ssex,
        avatar,
        ip,
        setUserDto,
        setUserId,
    }
})
