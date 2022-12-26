/* eslint-disable */
import * as _m0 from "protobufjs/minimal";

export const protobufPackage = "Message";

export interface Msg {
  dataType: Msg_DataType;
  chatMsg?: ChatMsg | undefined;
  chatMsgAll?: ChatMsgAll | undefined;
  chatMsgByGroup?: ChatMsgByGroup | undefined;
  bindChannel?: BindChannel | undefined;
}

export enum Msg_DataType {
  chatMsgType = 0,
  chatMsgAllType = 1,
  chatMsgByGroupType = 2,
  bindChannelType = 3,
  UNRECOGNIZED = -1,
}

export function msg_DataTypeFromJSON(object: any): Msg_DataType {
  switch (object) {
    case 0:
    case "chatMsgType":
      return Msg_DataType.chatMsgType;
    case 1:
    case "chatMsgAllType":
      return Msg_DataType.chatMsgAllType;
    case 2:
    case "chatMsgByGroupType":
      return Msg_DataType.chatMsgByGroupType;
    case 3:
    case "bindChannelType":
      return Msg_DataType.bindChannelType;
    case -1:
    case "UNRECOGNIZED":
    default:
      return Msg_DataType.UNRECOGNIZED;
  }
}

export function msg_DataTypeToJSON(object: Msg_DataType): string {
  switch (object) {
    case Msg_DataType.chatMsgType:
      return "chatMsgType";
    case Msg_DataType.chatMsgAllType:
      return "chatMsgAllType";
    case Msg_DataType.chatMsgByGroupType:
      return "chatMsgByGroupType";
    case Msg_DataType.bindChannelType:
      return "bindChannelType";
    case Msg_DataType.UNRECOGNIZED:
    default:
      return "UNRECOGNIZED";
  }
}

export interface ChatMsg {
  userId: string;
  userName: string;
  acceptId: string;
  acceptName: string;
  message: string;
}

export interface ChatMsgByGroup {
  userId: string;
  group: string;
  message: string;
}

export interface ChatMsgAll {
  userId: string;
  message: string;
}

export interface BindChannel {
  userId: string;
  userName: string;
}

function createBaseMsg(): Msg {
  return { dataType: 0, chatMsg: undefined, chatMsgAll: undefined, chatMsgByGroup: undefined, bindChannel: undefined };
}

export const Msg = {
  encode(message: Msg, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.dataType !== 0) {
      writer.uint32(8).int32(message.dataType);
    }
    if (message.chatMsg !== undefined) {
      ChatMsg.encode(message.chatMsg, writer.uint32(18).fork()).ldelim();
    }
    if (message.chatMsgAll !== undefined) {
      ChatMsgAll.encode(message.chatMsgAll, writer.uint32(26).fork()).ldelim();
    }
    if (message.chatMsgByGroup !== undefined) {
      ChatMsgByGroup.encode(message.chatMsgByGroup, writer.uint32(34).fork()).ldelim();
    }
    if (message.bindChannel !== undefined) {
      BindChannel.encode(message.bindChannel, writer.uint32(42).fork()).ldelim();
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): Msg {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseMsg();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.dataType = reader.int32() as any;
          break;
        case 2:
          message.chatMsg = ChatMsg.decode(reader, reader.uint32());
          break;
        case 3:
          message.chatMsgAll = ChatMsgAll.decode(reader, reader.uint32());
          break;
        case 4:
          message.chatMsgByGroup = ChatMsgByGroup.decode(reader, reader.uint32());
          break;
        case 5:
          message.bindChannel = BindChannel.decode(reader, reader.uint32());
          break;
        default:
          reader.skipType(tag & 7);
          break;
      }
    }
    return message;
  },

  fromJSON(object: any): Msg {
    return {
      dataType: isSet(object.dataType) ? msg_DataTypeFromJSON(object.dataType) : 0,
      chatMsg: isSet(object.chatMsg) ? ChatMsg.fromJSON(object.chatMsg) : undefined,
      chatMsgAll: isSet(object.chatMsgAll) ? ChatMsgAll.fromJSON(object.chatMsgAll) : undefined,
      chatMsgByGroup: isSet(object.chatMsgByGroup) ? ChatMsgByGroup.fromJSON(object.chatMsgByGroup) : undefined,
      bindChannel: isSet(object.bindChannel) ? BindChannel.fromJSON(object.bindChannel) : undefined,
    };
  },

  toJSON(message: Msg): unknown {
    const obj: any = {};
    message.dataType !== undefined && (obj.dataType = msg_DataTypeToJSON(message.dataType));
    message.chatMsg !== undefined && (obj.chatMsg = message.chatMsg ? ChatMsg.toJSON(message.chatMsg) : undefined);
    message.chatMsgAll !== undefined &&
      (obj.chatMsgAll = message.chatMsgAll ? ChatMsgAll.toJSON(message.chatMsgAll) : undefined);
    message.chatMsgByGroup !== undefined &&
      (obj.chatMsgByGroup = message.chatMsgByGroup ? ChatMsgByGroup.toJSON(message.chatMsgByGroup) : undefined);
    message.bindChannel !== undefined &&
      (obj.bindChannel = message.bindChannel ? BindChannel.toJSON(message.bindChannel) : undefined);
    return obj;
  },

  fromPartial<I extends Exact<DeepPartial<Msg>, I>>(object: I): Msg {
    const message = createBaseMsg();
    message.dataType = object.dataType ?? 0;
    message.chatMsg = (object.chatMsg !== undefined && object.chatMsg !== null)
      ? ChatMsg.fromPartial(object.chatMsg)
      : undefined;
    message.chatMsgAll = (object.chatMsgAll !== undefined && object.chatMsgAll !== null)
      ? ChatMsgAll.fromPartial(object.chatMsgAll)
      : undefined;
    message.chatMsgByGroup = (object.chatMsgByGroup !== undefined && object.chatMsgByGroup !== null)
      ? ChatMsgByGroup.fromPartial(object.chatMsgByGroup)
      : undefined;
    message.bindChannel = (object.bindChannel !== undefined && object.bindChannel !== null)
      ? BindChannel.fromPartial(object.bindChannel)
      : undefined;
    return message;
  },
};

function createBaseChatMsg(): ChatMsg {
  return { userId: "", userName: "", acceptId: "", acceptName: "", message: "" };
}

export const ChatMsg = {
  encode(message: ChatMsg, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.userId !== "") {
      writer.uint32(10).string(message.userId);
    }
    if (message.userName !== "") {
      writer.uint32(18).string(message.userName);
    }
    if (message.acceptId !== "") {
      writer.uint32(26).string(message.acceptId);
    }
    if (message.acceptName !== "") {
      writer.uint32(34).string(message.acceptName);
    }
    if (message.message !== "") {
      writer.uint32(42).string(message.message);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): ChatMsg {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseChatMsg();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.userId = reader.string();
          break;
        case 2:
          message.userName = reader.string();
          break;
        case 3:
          message.acceptId = reader.string();
          break;
        case 4:
          message.acceptName = reader.string();
          break;
        case 5:
          message.message = reader.string();
          break;
        default:
          reader.skipType(tag & 7);
          break;
      }
    }
    return message;
  },

  fromJSON(object: any): ChatMsg {
    return {
      userId: isSet(object.userId) ? String(object.userId) : "",
      userName: isSet(object.userName) ? String(object.userName) : "",
      acceptId: isSet(object.acceptId) ? String(object.acceptId) : "",
      acceptName: isSet(object.acceptName) ? String(object.acceptName) : "",
      message: isSet(object.message) ? String(object.message) : "",
    };
  },

  toJSON(message: ChatMsg): unknown {
    const obj: any = {};
    message.userId !== undefined && (obj.userId = message.userId);
    message.userName !== undefined && (obj.userName = message.userName);
    message.acceptId !== undefined && (obj.acceptId = message.acceptId);
    message.acceptName !== undefined && (obj.acceptName = message.acceptName);
    message.message !== undefined && (obj.message = message.message);
    return obj;
  },

  fromPartial<I extends Exact<DeepPartial<ChatMsg>, I>>(object: I): ChatMsg {
    const message = createBaseChatMsg();
    message.userId = object.userId ?? "";
    message.userName = object.userName ?? "";
    message.acceptId = object.acceptId ?? "";
    message.acceptName = object.acceptName ?? "";
    message.message = object.message ?? "";
    return message;
  },
};

function createBaseChatMsgByGroup(): ChatMsgByGroup {
  return { userId: "", group: "", message: "" };
}

export const ChatMsgByGroup = {
  encode(message: ChatMsgByGroup, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.userId !== "") {
      writer.uint32(10).string(message.userId);
    }
    if (message.group !== "") {
      writer.uint32(18).string(message.group);
    }
    if (message.message !== "") {
      writer.uint32(26).string(message.message);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): ChatMsgByGroup {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseChatMsgByGroup();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.userId = reader.string();
          break;
        case 2:
          message.group = reader.string();
          break;
        case 3:
          message.message = reader.string();
          break;
        default:
          reader.skipType(tag & 7);
          break;
      }
    }
    return message;
  },

  fromJSON(object: any): ChatMsgByGroup {
    return {
      userId: isSet(object.userId) ? String(object.userId) : "",
      group: isSet(object.group) ? String(object.group) : "",
      message: isSet(object.message) ? String(object.message) : "",
    };
  },

  toJSON(message: ChatMsgByGroup): unknown {
    const obj: any = {};
    message.userId !== undefined && (obj.userId = message.userId);
    message.group !== undefined && (obj.group = message.group);
    message.message !== undefined && (obj.message = message.message);
    return obj;
  },

  fromPartial<I extends Exact<DeepPartial<ChatMsgByGroup>, I>>(object: I): ChatMsgByGroup {
    const message = createBaseChatMsgByGroup();
    message.userId = object.userId ?? "";
    message.group = object.group ?? "";
    message.message = object.message ?? "";
    return message;
  },
};

function createBaseChatMsgAll(): ChatMsgAll {
  return { userId: "", message: "" };
}

export const ChatMsgAll = {
  encode(message: ChatMsgAll, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.userId !== "") {
      writer.uint32(10).string(message.userId);
    }
    if (message.message !== "") {
      writer.uint32(18).string(message.message);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): ChatMsgAll {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseChatMsgAll();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.userId = reader.string();
          break;
        case 2:
          message.message = reader.string();
          break;
        default:
          reader.skipType(tag & 7);
          break;
      }
    }
    return message;
  },

  fromJSON(object: any): ChatMsgAll {
    return {
      userId: isSet(object.userId) ? String(object.userId) : "",
      message: isSet(object.message) ? String(object.message) : "",
    };
  },

  toJSON(message: ChatMsgAll): unknown {
    const obj: any = {};
    message.userId !== undefined && (obj.userId = message.userId);
    message.message !== undefined && (obj.message = message.message);
    return obj;
  },

  fromPartial<I extends Exact<DeepPartial<ChatMsgAll>, I>>(object: I): ChatMsgAll {
    const message = createBaseChatMsgAll();
    message.userId = object.userId ?? "";
    message.message = object.message ?? "";
    return message;
  },
};

function createBaseBindChannel(): BindChannel {
  return { userId: "", userName: "" };
}

export const BindChannel = {
  encode(message: BindChannel, writer: _m0.Writer = _m0.Writer.create()): _m0.Writer {
    if (message.userId !== "") {
      writer.uint32(10).string(message.userId);
    }
    if (message.userName !== "") {
      writer.uint32(18).string(message.userName);
    }
    return writer;
  },

  decode(input: _m0.Reader | Uint8Array, length?: number): BindChannel {
    const reader = input instanceof _m0.Reader ? input : new _m0.Reader(input);
    let end = length === undefined ? reader.len : reader.pos + length;
    const message = createBaseBindChannel();
    while (reader.pos < end) {
      const tag = reader.uint32();
      switch (tag >>> 3) {
        case 1:
          message.userId = reader.string();
          break;
        case 2:
          message.userName = reader.string();
          break;
        default:
          reader.skipType(tag & 7);
          break;
      }
    }
    return message;
  },

  fromJSON(object: any): BindChannel {
    return {
      userId: isSet(object.userId) ? String(object.userId) : "",
      userName: isSet(object.userName) ? String(object.userName) : "",
    };
  },

  toJSON(message: BindChannel): unknown {
    const obj: any = {};
    message.userId !== undefined && (obj.userId = message.userId);
    message.userName !== undefined && (obj.userName = message.userName);
    return obj;
  },

  fromPartial<I extends Exact<DeepPartial<BindChannel>, I>>(object: I): BindChannel {
    const message = createBaseBindChannel();
    message.userId = object.userId ?? "";
    message.userName = object.userName ?? "";
    return message;
  },
};

type Builtin = Date | Function | Uint8Array | string | number | boolean | undefined;

export type DeepPartial<T> = T extends Builtin ? T
  : T extends Array<infer U> ? Array<DeepPartial<U>> : T extends ReadonlyArray<infer U> ? ReadonlyArray<DeepPartial<U>>
  : T extends {} ? { [K in keyof T]?: DeepPartial<T[K]> }
  : Partial<T>;

type KeysOfUnion<T> = T extends T ? keyof T : never;
export type Exact<P, I extends P> = P extends Builtin ? P
  : P & { [K in keyof P]: Exact<P[K], I[K]> } & { [K in Exclude<keyof I, KeysOfUnion<P>>]: never };

function isSet(value: any): boolean {
  return value !== null && value !== undefined;
}
