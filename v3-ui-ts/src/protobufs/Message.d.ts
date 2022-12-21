import * as $protobuf from "protobufjs";
/** Namespace Message. */
export namespace Message {

    /** Properties of a Msg. */
    interface IMsg {

        /** Msg dataType */
        dataType?: (Message.Msg.DataType|null);

        /** Msg chatMsg */
        chatMsg?: (Message.IChatMsg|null);

        /** Msg chatMsgAll */
        chatMsgAll?: (Message.IChatMsgAll|null);

        /** Msg chatMsgByGroup */
        chatMsgByGroup?: (Message.IChatMsgByGroup|null);

        /** Msg bindChannel */
        bindChannel?: (Message.IBindChannel|null);
    }

    /** Represents a Msg. */
    class Msg implements IMsg {

        /**
         * Constructs a new Msg.
         * @param [properties] Properties to set
         */
        constructor(properties?: Message.IMsg);

        /** Msg dataType. */
        public dataType: Message.Msg.DataType;

        /** Msg chatMsg. */
        public chatMsg?: (Message.IChatMsg|null);

        /** Msg chatMsgAll. */
        public chatMsgAll?: (Message.IChatMsgAll|null);

        /** Msg chatMsgByGroup. */
        public chatMsgByGroup?: (Message.IChatMsgByGroup|null);

        /** Msg bindChannel. */
        public bindChannel?: (Message.IBindChannel|null);

        /** Msg dataBody. */
        public dataBody?: ("chatMsg"|"chatMsgAll"|"chatMsgByGroup"|"bindChannel");

        /**
         * Creates a new Msg instance using the specified properties.
         * @param [properties] Properties to set
         * @returns Msg instance
         */
        public static create(properties?: Message.IMsg): Message.Msg;

        /**
         * Encodes the specified Msg message. Does not implicitly {@link Message.Msg.verify|verify} messages.
         * @param message Msg message or plain object to encode
         * @param [writer] Writer to encode to
         * @returns Writer
         */
        public static encode(message: Message.IMsg, writer?: $protobuf.Writer): $protobuf.Writer;

        /**
         * Encodes the specified Msg message, length delimited. Does not implicitly {@link Message.Msg.verify|verify} messages.
         * @param message Msg message or plain object to encode
         * @param [writer] Writer to encode to
         * @returns Writer
         */
        public static encodeDelimited(message: Message.IMsg, writer?: $protobuf.Writer): $protobuf.Writer;

        /**
         * Decodes a Msg message from the specified reader or buffer.
         * @param reader Reader or buffer to decode from
         * @param [length] Message length if known beforehand
         * @returns Msg
         * @throws {Error} If the payload is not a reader or valid buffer
         * @throws {$protobuf.util.ProtocolError} If required fields are missing
         */
        public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): Message.Msg;

        /**
         * Decodes a Msg message from the specified reader or buffer, length delimited.
         * @param reader Reader or buffer to decode from
         * @returns Msg
         * @throws {Error} If the payload is not a reader or valid buffer
         * @throws {$protobuf.util.ProtocolError} If required fields are missing
         */
        public static decodeDelimited(reader: ($protobuf.Reader|Uint8Array)): Message.Msg;

        /**
         * Verifies a Msg message.
         * @param message Plain object to verify
         * @returns `null` if valid, otherwise the reason why it is not
         */
        public static verify(message: { [k: string]: any }): (string|null);

        /**
         * Creates a Msg message from a plain object. Also converts values to their respective internal types.
         * @param object Plain object
         * @returns Msg
         */
        public static fromObject(object: { [k: string]: any }): Message.Msg;

        /**
         * Creates a plain object from a Msg message. Also converts values to other types if specified.
         * @param message Msg
         * @param [options] Conversion options
         * @returns Plain object
         */
        public static toObject(message: Message.Msg, options?: $protobuf.IConversionOptions): { [k: string]: any };

        /**
         * Converts this Msg to JSON.
         * @returns JSON object
         */
        public toJSON(): { [k: string]: any };
    }

    namespace Msg {

        /** DataType enum. */
        enum DataType {
            chatMsgType = 0,
            chatMsgAllType = 1,
            chatMsgByGroupType = 2,
            bindChannelType = 3
        }
    }

    /** Properties of a ChatMsg. */
    interface IChatMsg {

        /** ChatMsg userId */
        userId?: (string|null);

        /** ChatMsg userName */
        userName?: (string|null);

        /** ChatMsg acceptId */
        acceptId?: (string|null);

        /** ChatMsg acceptName */
        acceptName?: (string|null);

        /** ChatMsg message */
        message?: (string|null);
    }

    /** Represents a ChatMsg. */
    class ChatMsg implements IChatMsg {

        /**
         * Constructs a new ChatMsg.
         * @param [properties] Properties to set
         */
        constructor(properties?: Message.IChatMsg);

        /** ChatMsg userId. */
        public userId: string;

        /** ChatMsg userName. */
        public userName: string;

        /** ChatMsg acceptId. */
        public acceptId: string;

        /** ChatMsg acceptName. */
        public acceptName: string;

        /** ChatMsg message. */
        public message: string;

        /**
         * Creates a new ChatMsg instance using the specified properties.
         * @param [properties] Properties to set
         * @returns ChatMsg instance
         */
        public static create(properties?: Message.IChatMsg): Message.ChatMsg;

        /**
         * Encodes the specified ChatMsg message. Does not implicitly {@link Message.ChatMsg.verify|verify} messages.
         * @param message ChatMsg message or plain object to encode
         * @param [writer] Writer to encode to
         * @returns Writer
         */
        public static encode(message: Message.IChatMsg, writer?: $protobuf.Writer): $protobuf.Writer;

        /**
         * Encodes the specified ChatMsg message, length delimited. Does not implicitly {@link Message.ChatMsg.verify|verify} messages.
         * @param message ChatMsg message or plain object to encode
         * @param [writer] Writer to encode to
         * @returns Writer
         */
        public static encodeDelimited(message: Message.IChatMsg, writer?: $protobuf.Writer): $protobuf.Writer;

        /**
         * Decodes a ChatMsg message from the specified reader or buffer.
         * @param reader Reader or buffer to decode from
         * @param [length] Message length if known beforehand
         * @returns ChatMsg
         * @throws {Error} If the payload is not a reader or valid buffer
         * @throws {$protobuf.util.ProtocolError} If required fields are missing
         */
        public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): Message.ChatMsg;

        /**
         * Decodes a ChatMsg message from the specified reader or buffer, length delimited.
         * @param reader Reader or buffer to decode from
         * @returns ChatMsg
         * @throws {Error} If the payload is not a reader or valid buffer
         * @throws {$protobuf.util.ProtocolError} If required fields are missing
         */
        public static decodeDelimited(reader: ($protobuf.Reader|Uint8Array)): Message.ChatMsg;

        /**
         * Verifies a ChatMsg message.
         * @param message Plain object to verify
         * @returns `null` if valid, otherwise the reason why it is not
         */
        public static verify(message: { [k: string]: any }): (string|null);

        /**
         * Creates a ChatMsg message from a plain object. Also converts values to their respective internal types.
         * @param object Plain object
         * @returns ChatMsg
         */
        public static fromObject(object: { [k: string]: any }): Message.ChatMsg;

        /**
         * Creates a plain object from a ChatMsg message. Also converts values to other types if specified.
         * @param message ChatMsg
         * @param [options] Conversion options
         * @returns Plain object
         */
        public static toObject(message: Message.ChatMsg, options?: $protobuf.IConversionOptions): { [k: string]: any };

        /**
         * Converts this ChatMsg to JSON.
         * @returns JSON object
         */
        public toJSON(): { [k: string]: any };
    }

    /** Properties of a ChatMsgByGroup. */
    interface IChatMsgByGroup {

        /** ChatMsgByGroup userId */
        userId?: (string|null);

        /** ChatMsgByGroup group */
        group?: (string|null);

        /** ChatMsgByGroup message */
        message?: (string|null);
    }

    /** Represents a ChatMsgByGroup. */
    class ChatMsgByGroup implements IChatMsgByGroup {

        /**
         * Constructs a new ChatMsgByGroup.
         * @param [properties] Properties to set
         */
        constructor(properties?: Message.IChatMsgByGroup);

        /** ChatMsgByGroup userId. */
        public userId: string;

        /** ChatMsgByGroup group. */
        public group: string;

        /** ChatMsgByGroup message. */
        public message: string;

        /**
         * Creates a new ChatMsgByGroup instance using the specified properties.
         * @param [properties] Properties to set
         * @returns ChatMsgByGroup instance
         */
        public static create(properties?: Message.IChatMsgByGroup): Message.ChatMsgByGroup;

        /**
         * Encodes the specified ChatMsgByGroup message. Does not implicitly {@link Message.ChatMsgByGroup.verify|verify} messages.
         * @param message ChatMsgByGroup message or plain object to encode
         * @param [writer] Writer to encode to
         * @returns Writer
         */
        public static encode(message: Message.IChatMsgByGroup, writer?: $protobuf.Writer): $protobuf.Writer;

        /**
         * Encodes the specified ChatMsgByGroup message, length delimited. Does not implicitly {@link Message.ChatMsgByGroup.verify|verify} messages.
         * @param message ChatMsgByGroup message or plain object to encode
         * @param [writer] Writer to encode to
         * @returns Writer
         */
        public static encodeDelimited(message: Message.IChatMsgByGroup, writer?: $protobuf.Writer): $protobuf.Writer;

        /**
         * Decodes a ChatMsgByGroup message from the specified reader or buffer.
         * @param reader Reader or buffer to decode from
         * @param [length] Message length if known beforehand
         * @returns ChatMsgByGroup
         * @throws {Error} If the payload is not a reader or valid buffer
         * @throws {$protobuf.util.ProtocolError} If required fields are missing
         */
        public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): Message.ChatMsgByGroup;

        /**
         * Decodes a ChatMsgByGroup message from the specified reader or buffer, length delimited.
         * @param reader Reader or buffer to decode from
         * @returns ChatMsgByGroup
         * @throws {Error} If the payload is not a reader or valid buffer
         * @throws {$protobuf.util.ProtocolError} If required fields are missing
         */
        public static decodeDelimited(reader: ($protobuf.Reader|Uint8Array)): Message.ChatMsgByGroup;

        /**
         * Verifies a ChatMsgByGroup message.
         * @param message Plain object to verify
         * @returns `null` if valid, otherwise the reason why it is not
         */
        public static verify(message: { [k: string]: any }): (string|null);

        /**
         * Creates a ChatMsgByGroup message from a plain object. Also converts values to their respective internal types.
         * @param object Plain object
         * @returns ChatMsgByGroup
         */
        public static fromObject(object: { [k: string]: any }): Message.ChatMsgByGroup;

        /**
         * Creates a plain object from a ChatMsgByGroup message. Also converts values to other types if specified.
         * @param message ChatMsgByGroup
         * @param [options] Conversion options
         * @returns Plain object
         */
        public static toObject(message: Message.ChatMsgByGroup, options?: $protobuf.IConversionOptions): { [k: string]: any };

        /**
         * Converts this ChatMsgByGroup to JSON.
         * @returns JSON object
         */
        public toJSON(): { [k: string]: any };
    }

    /** Properties of a ChatMsgAll. */
    interface IChatMsgAll {

        /** ChatMsgAll userId */
        userId?: (string|null);

        /** ChatMsgAll message */
        message?: (string|null);
    }

    /** Represents a ChatMsgAll. */
    class ChatMsgAll implements IChatMsgAll {

        /**
         * Constructs a new ChatMsgAll.
         * @param [properties] Properties to set
         */
        constructor(properties?: Message.IChatMsgAll);

        /** ChatMsgAll userId. */
        public userId: string;

        /** ChatMsgAll message. */
        public message: string;

        /**
         * Creates a new ChatMsgAll instance using the specified properties.
         * @param [properties] Properties to set
         * @returns ChatMsgAll instance
         */
        public static create(properties?: Message.IChatMsgAll): Message.ChatMsgAll;

        /**
         * Encodes the specified ChatMsgAll message. Does not implicitly {@link Message.ChatMsgAll.verify|verify} messages.
         * @param message ChatMsgAll message or plain object to encode
         * @param [writer] Writer to encode to
         * @returns Writer
         */
        public static encode(message: Message.IChatMsgAll, writer?: $protobuf.Writer): $protobuf.Writer;

        /**
         * Encodes the specified ChatMsgAll message, length delimited. Does not implicitly {@link Message.ChatMsgAll.verify|verify} messages.
         * @param message ChatMsgAll message or plain object to encode
         * @param [writer] Writer to encode to
         * @returns Writer
         */
        public static encodeDelimited(message: Message.IChatMsgAll, writer?: $protobuf.Writer): $protobuf.Writer;

        /**
         * Decodes a ChatMsgAll message from the specified reader or buffer.
         * @param reader Reader or buffer to decode from
         * @param [length] Message length if known beforehand
         * @returns ChatMsgAll
         * @throws {Error} If the payload is not a reader or valid buffer
         * @throws {$protobuf.util.ProtocolError} If required fields are missing
         */
        public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): Message.ChatMsgAll;

        /**
         * Decodes a ChatMsgAll message from the specified reader or buffer, length delimited.
         * @param reader Reader or buffer to decode from
         * @returns ChatMsgAll
         * @throws {Error} If the payload is not a reader or valid buffer
         * @throws {$protobuf.util.ProtocolError} If required fields are missing
         */
        public static decodeDelimited(reader: ($protobuf.Reader|Uint8Array)): Message.ChatMsgAll;

        /**
         * Verifies a ChatMsgAll message.
         * @param message Plain object to verify
         * @returns `null` if valid, otherwise the reason why it is not
         */
        public static verify(message: { [k: string]: any }): (string|null);

        /**
         * Creates a ChatMsgAll message from a plain object. Also converts values to their respective internal types.
         * @param object Plain object
         * @returns ChatMsgAll
         */
        public static fromObject(object: { [k: string]: any }): Message.ChatMsgAll;

        /**
         * Creates a plain object from a ChatMsgAll message. Also converts values to other types if specified.
         * @param message ChatMsgAll
         * @param [options] Conversion options
         * @returns Plain object
         */
        public static toObject(message: Message.ChatMsgAll, options?: $protobuf.IConversionOptions): { [k: string]: any };

        /**
         * Converts this ChatMsgAll to JSON.
         * @returns JSON object
         */
        public toJSON(): { [k: string]: any };
    }

    /** Properties of a BindChannel. */
    interface IBindChannel {

        /** BindChannel userId */
        userId?: (string|null);
    }

    /** Represents a BindChannel. */
    class BindChannel implements IBindChannel {

        /**
         * Constructs a new BindChannel.
         * @param [properties] Properties to set
         */
        constructor(properties?: Message.IBindChannel);

        /** BindChannel userId. */
        public userId: string;

        /**
         * Creates a new BindChannel instance using the specified properties.
         * @param [properties] Properties to set
         * @returns BindChannel instance
         */
        public static create(properties?: Message.IBindChannel): Message.BindChannel;

        /**
         * Encodes the specified BindChannel message. Does not implicitly {@link Message.BindChannel.verify|verify} messages.
         * @param message BindChannel message or plain object to encode
         * @param [writer] Writer to encode to
         * @returns Writer
         */
        public static encode(message: Message.IBindChannel, writer?: $protobuf.Writer): $protobuf.Writer;

        /**
         * Encodes the specified BindChannel message, length delimited. Does not implicitly {@link Message.BindChannel.verify|verify} messages.
         * @param message BindChannel message or plain object to encode
         * @param [writer] Writer to encode to
         * @returns Writer
         */
        public static encodeDelimited(message: Message.IBindChannel, writer?: $protobuf.Writer): $protobuf.Writer;

        /**
         * Decodes a BindChannel message from the specified reader or buffer.
         * @param reader Reader or buffer to decode from
         * @param [length] Message length if known beforehand
         * @returns BindChannel
         * @throws {Error} If the payload is not a reader or valid buffer
         * @throws {$protobuf.util.ProtocolError} If required fields are missing
         */
        public static decode(reader: ($protobuf.Reader|Uint8Array), length?: number): Message.BindChannel;

        /**
         * Decodes a BindChannel message from the specified reader or buffer, length delimited.
         * @param reader Reader or buffer to decode from
         * @returns BindChannel
         * @throws {Error} If the payload is not a reader or valid buffer
         * @throws {$protobuf.util.ProtocolError} If required fields are missing
         */
        public static decodeDelimited(reader: ($protobuf.Reader|Uint8Array)): Message.BindChannel;

        /**
         * Verifies a BindChannel message.
         * @param message Plain object to verify
         * @returns `null` if valid, otherwise the reason why it is not
         */
        public static verify(message: { [k: string]: any }): (string|null);

        /**
         * Creates a BindChannel message from a plain object. Also converts values to their respective internal types.
         * @param object Plain object
         * @returns BindChannel
         */
        public static fromObject(object: { [k: string]: any }): Message.BindChannel;

        /**
         * Creates a plain object from a BindChannel message. Also converts values to other types if specified.
         * @param message BindChannel
         * @param [options] Conversion options
         * @returns Plain object
         */
        public static toObject(message: Message.BindChannel, options?: $protobuf.IConversionOptions): { [k: string]: any };

        /**
         * Converts this BindChannel to JSON.
         * @returns JSON object
         */
        public toJSON(): { [k: string]: any };
    }
}
