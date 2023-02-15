import Socket from "@/util/socket";
import {User} from "@/store/UserDto";
import {ref} from "vue";
import chatMsgImpl from "@/entity/ChatMsg";
const userDto = User().userDto


const socket = ref<Socket<chatMsgImpl>>()

export const initSocket = () => {
    socket.value = new Socket({ url:'ws://127.0.0.1:7001'+'/ws?userId='+userDto.userId});
}

export const getSocket = () => {
    return socket.value.getThis()
}