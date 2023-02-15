export interface chatMsg {
    id?: string,
    code?: number,
    sendId?: string,
    acceptId?: string,
    msg?: string,
    signFlag?: string,
    createTime?: string,
    msgType?: string,
    acceptGroup?: string,
    expandMsg?: any
}

export default class chatMsgImpl implements chatMsg{
    id?: string
    code?: number
    sendId?: string
    acceptId?: string
    msg?: string
    signFlag?: string
    createTime?: string
    msgType?: string
    acceptGroup?: string
    expandMsg?: any


    constructor(id?: string, code?: number, sendId?: string,
                acceptId?: string, msg?: string, signFlag?: string,
                createTime?: string, msgType?: string, acceptGroup?: string, expandMsg?: any) {
        this.id = id;
        this.code = code;
        this.sendId = sendId;
        this.acceptId = acceptId;
        this.msg = msg;
        this.signFlag = signFlag;
        this.createTime = createTime;
        this.msgType = msgType;
        this.acceptGroup = acceptGroup;
        this.expandMsg = expandMsg;
    }
}