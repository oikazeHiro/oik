import Socket from "@/util/socket";
import {ref} from "vue";
import chatMsgImpl from "@/entity/ChatMsg";


const socket = ref<Socket<chatMsgImpl>>()

export const initSocket = (userId: string) => {
    socket.value = new Socket({url: 'ws://127.0.0.1:7001' + '/ws?userId=' + userId});
}

export const getSocket = () => {
    return socket.value.getThis()
}