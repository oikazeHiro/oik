/*eslint-disable block-scoped-var, id-length, no-control-regex, no-magic-numbers, no-prototype-builtins, no-redeclare, no-shadow, no-var, sort-vars*/
"use strict";

var $protobuf = require("protobufjs/minimal");

// Common aliases
var $Reader = $protobuf.Reader, $Writer = $protobuf.Writer, $util = $protobuf.util;

// Exported root namespace
var $root = $protobuf.roots["default"] || ($protobuf.roots["default"] = {});

$root.Message = (function() {

    /**
     * Namespace Message.
     * @exports Message
     * @namespace
     */
    var Message = {};

    Message.Msg = (function() {

        /**
         * Properties of a Msg.
         * @memberof Message
         * @interface IMsg
         * @property {Message.Msg.DataType|null} [dataType] Msg dataType
         * @property {Message.IChatMsg|null} [chatMsg] Msg chatMsg
         * @property {Message.IChatMsgAll|null} [chatMsgAll] Msg chatMsgAll
         * @property {Message.IChatMsgByGroup|null} [chatMsgByGroup] Msg chatMsgByGroup
         * @property {Message.IBindChannel|null} [bindChannel] Msg bindChannel
         */

        /**
         * Constructs a new Msg.
         * @memberof Message
         * @classdesc Represents a Msg.
         * @implements IMsg
         * @constructor
         * @param {Message.IMsg=} [properties] Properties to set
         */
        function Msg(properties) {
            if (properties)
                for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                    if (properties[keys[i]] != null)
                        this[keys[i]] = properties[keys[i]];
        }

        /**
         * Msg dataType.
         * @member {Message.Msg.DataType} dataType
         * @memberof Message.Msg
         * @instance
         */
        Msg.prototype.dataType = 0;

        /**
         * Msg chatMsg.
         * @member {Message.IChatMsg|null|undefined} chatMsg
         * @memberof Message.Msg
         * @instance
         */
        Msg.prototype.chatMsg = null;

        /**
         * Msg chatMsgAll.
         * @member {Message.IChatMsgAll|null|undefined} chatMsgAll
         * @memberof Message.Msg
         * @instance
         */
        Msg.prototype.chatMsgAll = null;

        /**
         * Msg chatMsgByGroup.
         * @member {Message.IChatMsgByGroup|null|undefined} chatMsgByGroup
         * @memberof Message.Msg
         * @instance
         */
        Msg.prototype.chatMsgByGroup = null;

        /**
         * Msg bindChannel.
         * @member {Message.IBindChannel|null|undefined} bindChannel
         * @memberof Message.Msg
         * @instance
         */
        Msg.prototype.bindChannel = null;

        // OneOf field names bound to virtual getters and setters
        var $oneOfFields;

        /**
         * Msg dataBody.
         * @member {"chatMsg"|"chatMsgAll"|"chatMsgByGroup"|"bindChannel"|undefined} dataBody
         * @memberof Message.Msg
         * @instance
         */
        Object.defineProperty(Msg.prototype, "dataBody", {
            get: $util.oneOfGetter($oneOfFields = ["chatMsg", "chatMsgAll", "chatMsgByGroup", "bindChannel"]),
            set: $util.oneOfSetter($oneOfFields)
        });

        /**
         * Creates a new Msg instance using the specified properties.
         * @function create
         * @memberof Message.Msg
         * @static
         * @param {Message.IMsg=} [properties] Properties to set
         * @returns {Message.Msg} Msg instance
         */
        Msg.create = function create(properties) {
            return new Msg(properties);
        };

        /**
         * Encodes the specified Msg message. Does not implicitly {@link Message.Msg.verify|verify} messages.
         * @function encode
         * @memberof Message.Msg
         * @static
         * @param {Message.IMsg} message Msg message or plain object to encode
         * @param {$protobuf.Writer} [writer] Writer to encode to
         * @returns {$protobuf.Writer} Writer
         */
        Msg.encode = function encode(message, writer) {
            if (!writer)
                writer = $Writer.create();
            if (message.dataType != null && Object.hasOwnProperty.call(message, "dataType"))
                writer.uint32(/* id 1, wireType 0 =*/8).int32(message.dataType);
            if (message.chatMsg != null && Object.hasOwnProperty.call(message, "chatMsg"))
                $root.Message.ChatMsg.encode(message.chatMsg, writer.uint32(/* id 2, wireType 2 =*/18).fork()).ldelim();
            if (message.chatMsgAll != null && Object.hasOwnProperty.call(message, "chatMsgAll"))
                $root.Message.ChatMsgAll.encode(message.chatMsgAll, writer.uint32(/* id 3, wireType 2 =*/26).fork()).ldelim();
            if (message.chatMsgByGroup != null && Object.hasOwnProperty.call(message, "chatMsgByGroup"))
                $root.Message.ChatMsgByGroup.encode(message.chatMsgByGroup, writer.uint32(/* id 4, wireType 2 =*/34).fork()).ldelim();
            if (message.bindChannel != null && Object.hasOwnProperty.call(message, "bindChannel"))
                $root.Message.BindChannel.encode(message.bindChannel, writer.uint32(/* id 5, wireType 2 =*/42).fork()).ldelim();
            return writer;
        };

        /**
         * Encodes the specified Msg message, length delimited. Does not implicitly {@link Message.Msg.verify|verify} messages.
         * @function encodeDelimited
         * @memberof Message.Msg
         * @static
         * @param {Message.IMsg} message Msg message or plain object to encode
         * @param {$protobuf.Writer} [writer] Writer to encode to
         * @returns {$protobuf.Writer} Writer
         */
        Msg.encodeDelimited = function encodeDelimited(message, writer) {
            return this.encode(message, writer).ldelim();
        };

        /**
         * Decodes a Msg message from the specified reader or buffer.
         * @function decode
         * @memberof Message.Msg
         * @static
         * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
         * @param {number} [length] Message length if known beforehand
         * @returns {Message.Msg} Msg
         * @throws {Error} If the payload is not a reader or valid buffer
         * @throws {$protobuf.util.ProtocolError} If required fields are missing
         */
        Msg.decode = function decode(reader, length) {
            if (!(reader instanceof $Reader))
                reader = $Reader.create(reader);
            var end = length === undefined ? reader.len : reader.pos + length, message = new $root.Message.Msg();
            while (reader.pos < end) {
                var tag = reader.uint32();
                switch (tag >>> 3) {
                case 1:
                    message.dataType = reader.int32();
                    break;
                case 2:
                    message.chatMsg = $root.Message.ChatMsg.decode(reader, reader.uint32());
                    break;
                case 3:
                    message.chatMsgAll = $root.Message.ChatMsgAll.decode(reader, reader.uint32());
                    break;
                case 4:
                    message.chatMsgByGroup = $root.Message.ChatMsgByGroup.decode(reader, reader.uint32());
                    break;
                case 5:
                    message.bindChannel = $root.Message.BindChannel.decode(reader, reader.uint32());
                    break;
                default:
                    reader.skipType(tag & 7);
                    break;
                }
            }
            return message;
        };

        /**
         * Decodes a Msg message from the specified reader or buffer, length delimited.
         * @function decodeDelimited
         * @memberof Message.Msg
         * @static
         * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
         * @returns {Message.Msg} Msg
         * @throws {Error} If the payload is not a reader or valid buffer
         * @throws {$protobuf.util.ProtocolError} If required fields are missing
         */
        Msg.decodeDelimited = function decodeDelimited(reader) {
            if (!(reader instanceof $Reader))
                reader = new $Reader(reader);
            return this.decode(reader, reader.uint32());
        };

        /**
         * Verifies a Msg message.
         * @function verify
         * @memberof Message.Msg
         * @static
         * @param {Object.<string,*>} message Plain object to verify
         * @returns {string|null} `null` if valid, otherwise the reason why it is not
         */
        Msg.verify = function verify(message) {
            if (typeof message !== "object" || message === null)
                return "object expected";
            var properties = {};
            if (message.dataType != null && message.hasOwnProperty("dataType"))
                switch (message.dataType) {
                default:
                    return "dataType: enum value expected";
                case 0:
                case 1:
                case 2:
                case 3:
                    break;
                }
            if (message.chatMsg != null && message.hasOwnProperty("chatMsg")) {
                properties.dataBody = 1;
                {
                    var error = $root.Message.ChatMsg.verify(message.chatMsg);
                    if (error)
                        return "chatMsg." + error;
                }
            }
            if (message.chatMsgAll != null && message.hasOwnProperty("chatMsgAll")) {
                if (properties.dataBody === 1)
                    return "dataBody: multiple values";
                properties.dataBody = 1;
                {
                    var error = $root.Message.ChatMsgAll.verify(message.chatMsgAll);
                    if (error)
                        return "chatMsgAll." + error;
                }
            }
            if (message.chatMsgByGroup != null && message.hasOwnProperty("chatMsgByGroup")) {
                if (properties.dataBody === 1)
                    return "dataBody: multiple values";
                properties.dataBody = 1;
                {
                    var error = $root.Message.ChatMsgByGroup.verify(message.chatMsgByGroup);
                    if (error)
                        return "chatMsgByGroup." + error;
                }
            }
            if (message.bindChannel != null && message.hasOwnProperty("bindChannel")) {
                if (properties.dataBody === 1)
                    return "dataBody: multiple values";
                properties.dataBody = 1;
                {
                    var error = $root.Message.BindChannel.verify(message.bindChannel);
                    if (error)
                        return "bindChannel." + error;
                }
            }
            return null;
        };

        /**
         * Creates a Msg message from a plain object. Also converts values to their respective internal types.
         * @function fromObject
         * @memberof Message.Msg
         * @static
         * @param {Object.<string,*>} object Plain object
         * @returns {Message.Msg} Msg
         */
        Msg.fromObject = function fromObject(object) {
            if (object instanceof $root.Message.Msg)
                return object;
            var message = new $root.Message.Msg();
            switch (object.dataType) {
            case "chatMsgType":
            case 0:
                message.dataType = 0;
                break;
            case "chatMsgAllType":
            case 1:
                message.dataType = 1;
                break;
            case "chatMsgByGroupType":
            case 2:
                message.dataType = 2;
                break;
            case "bindChannelType":
            case 3:
                message.dataType = 3;
                break;
            }
            if (object.chatMsg != null) {
                if (typeof object.chatMsg !== "object")
                    throw TypeError(".Message.Msg.chatMsg: object expected");
                message.chatMsg = $root.Message.ChatMsg.fromObject(object.chatMsg);
            }
            if (object.chatMsgAll != null) {
                if (typeof object.chatMsgAll !== "object")
                    throw TypeError(".Message.Msg.chatMsgAll: object expected");
                message.chatMsgAll = $root.Message.ChatMsgAll.fromObject(object.chatMsgAll);
            }
            if (object.chatMsgByGroup != null) {
                if (typeof object.chatMsgByGroup !== "object")
                    throw TypeError(".Message.Msg.chatMsgByGroup: object expected");
                message.chatMsgByGroup = $root.Message.ChatMsgByGroup.fromObject(object.chatMsgByGroup);
            }
            if (object.bindChannel != null) {
                if (typeof object.bindChannel !== "object")
                    throw TypeError(".Message.Msg.bindChannel: object expected");
                message.bindChannel = $root.Message.BindChannel.fromObject(object.bindChannel);
            }
            return message;
        };

        /**
         * Creates a plain object from a Msg message. Also converts values to other types if specified.
         * @function toObject
         * @memberof Message.Msg
         * @static
         * @param {Message.Msg} message Msg
         * @param {$protobuf.IConversionOptions} [options] Conversion options
         * @returns {Object.<string,*>} Plain object
         */
        Msg.toObject = function toObject(message, options) {
            if (!options)
                options = {};
            var object = {};
            if (options.defaults)
                object.dataType = options.enums === String ? "chatMsgType" : 0;
            if (message.dataType != null && message.hasOwnProperty("dataType"))
                object.dataType = options.enums === String ? $root.Message.Msg.DataType[message.dataType] : message.dataType;
            if (message.chatMsg != null && message.hasOwnProperty("chatMsg")) {
                object.chatMsg = $root.Message.ChatMsg.toObject(message.chatMsg, options);
                if (options.oneofs)
                    object.dataBody = "chatMsg";
            }
            if (message.chatMsgAll != null && message.hasOwnProperty("chatMsgAll")) {
                object.chatMsgAll = $root.Message.ChatMsgAll.toObject(message.chatMsgAll, options);
                if (options.oneofs)
                    object.dataBody = "chatMsgAll";
            }
            if (message.chatMsgByGroup != null && message.hasOwnProperty("chatMsgByGroup")) {
                object.chatMsgByGroup = $root.Message.ChatMsgByGroup.toObject(message.chatMsgByGroup, options);
                if (options.oneofs)
                    object.dataBody = "chatMsgByGroup";
            }
            if (message.bindChannel != null && message.hasOwnProperty("bindChannel")) {
                object.bindChannel = $root.Message.BindChannel.toObject(message.bindChannel, options);
                if (options.oneofs)
                    object.dataBody = "bindChannel";
            }
            return object;
        };

        /**
         * Converts this Msg to JSON.
         * @function toJSON
         * @memberof Message.Msg
         * @instance
         * @returns {Object.<string,*>} JSON object
         */
        Msg.prototype.toJSON = function toJSON() {
            return this.constructor.toObject(this, $protobuf.util.toJSONOptions);
        };

        /**
         * DataType enum.
         * @name Message.Msg.DataType
         * @enum {number}
         * @property {number} chatMsgType=0 chatMsgType value
         * @property {number} chatMsgAllType=1 chatMsgAllType value
         * @property {number} chatMsgByGroupType=2 chatMsgByGroupType value
         * @property {number} bindChannelType=3 bindChannelType value
         */
        Msg.DataType = (function() {
            var valuesById = {}, values = Object.create(valuesById);
            values[valuesById[0] = "chatMsgType"] = 0;
            values[valuesById[1] = "chatMsgAllType"] = 1;
            values[valuesById[2] = "chatMsgByGroupType"] = 2;
            values[valuesById[3] = "bindChannelType"] = 3;
            return values;
        })();

        return Msg;
    })();

    Message.ChatMsg = (function() {

        /**
         * Properties of a ChatMsg.
         * @memberof Message
         * @interface IChatMsg
         * @property {string|null} [userId] ChatMsg userId
         * @property {string|null} [userName] ChatMsg userName
         * @property {string|null} [acceptId] ChatMsg acceptId
         * @property {string|null} [acceptName] ChatMsg acceptName
         * @property {string|null} [message] ChatMsg message
         */

        /**
         * Constructs a new ChatMsg.
         * @memberof Message
         * @classdesc Represents a ChatMsg.
         * @implements IChatMsg
         * @constructor
         * @param {Message.IChatMsg=} [properties] Properties to set
         */
        function ChatMsg(properties) {
            if (properties)
                for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                    if (properties[keys[i]] != null)
                        this[keys[i]] = properties[keys[i]];
        }

        /**
         * ChatMsg userId.
         * @member {string} userId
         * @memberof Message.ChatMsg
         * @instance
         */
        ChatMsg.prototype.userId = "";

        /**
         * ChatMsg userName.
         * @member {string} userName
         * @memberof Message.ChatMsg
         * @instance
         */
        ChatMsg.prototype.userName = "";

        /**
         * ChatMsg acceptId.
         * @member {string} acceptId
         * @memberof Message.ChatMsg
         * @instance
         */
        ChatMsg.prototype.acceptId = "";

        /**
         * ChatMsg acceptName.
         * @member {string} acceptName
         * @memberof Message.ChatMsg
         * @instance
         */
        ChatMsg.prototype.acceptName = "";

        /**
         * ChatMsg message.
         * @member {string} message
         * @memberof Message.ChatMsg
         * @instance
         */
        ChatMsg.prototype.message = "";

        /**
         * Creates a new ChatMsg instance using the specified properties.
         * @function create
         * @memberof Message.ChatMsg
         * @static
         * @param {Message.IChatMsg=} [properties] Properties to set
         * @returns {Message.ChatMsg} ChatMsg instance
         */
        ChatMsg.create = function create(properties) {
            return new ChatMsg(properties);
        };

        /**
         * Encodes the specified ChatMsg message. Does not implicitly {@link Message.ChatMsg.verify|verify} messages.
         * @function encode
         * @memberof Message.ChatMsg
         * @static
         * @param {Message.IChatMsg} message ChatMsg message or plain object to encode
         * @param {$protobuf.Writer} [writer] Writer to encode to
         * @returns {$protobuf.Writer} Writer
         */
        ChatMsg.encode = function encode(message, writer) {
            if (!writer)
                writer = $Writer.create();
            if (message.userId != null && Object.hasOwnProperty.call(message, "userId"))
                writer.uint32(/* id 1, wireType 2 =*/10).string(message.userId);
            if (message.userName != null && Object.hasOwnProperty.call(message, "userName"))
                writer.uint32(/* id 2, wireType 2 =*/18).string(message.userName);
            if (message.acceptId != null && Object.hasOwnProperty.call(message, "acceptId"))
                writer.uint32(/* id 3, wireType 2 =*/26).string(message.acceptId);
            if (message.acceptName != null && Object.hasOwnProperty.call(message, "acceptName"))
                writer.uint32(/* id 4, wireType 2 =*/34).string(message.acceptName);
            if (message.message != null && Object.hasOwnProperty.call(message, "message"))
                writer.uint32(/* id 5, wireType 2 =*/42).string(message.message);
            return writer;
        };

        /**
         * Encodes the specified ChatMsg message, length delimited. Does not implicitly {@link Message.ChatMsg.verify|verify} messages.
         * @function encodeDelimited
         * @memberof Message.ChatMsg
         * @static
         * @param {Message.IChatMsg} message ChatMsg message or plain object to encode
         * @param {$protobuf.Writer} [writer] Writer to encode to
         * @returns {$protobuf.Writer} Writer
         */
        ChatMsg.encodeDelimited = function encodeDelimited(message, writer) {
            return this.encode(message, writer).ldelim();
        };

        /**
         * Decodes a ChatMsg message from the specified reader or buffer.
         * @function decode
         * @memberof Message.ChatMsg
         * @static
         * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
         * @param {number} [length] Message length if known beforehand
         * @returns {Message.ChatMsg} ChatMsg
         * @throws {Error} If the payload is not a reader or valid buffer
         * @throws {$protobuf.util.ProtocolError} If required fields are missing
         */
        ChatMsg.decode = function decode(reader, length) {
            if (!(reader instanceof $Reader))
                reader = $Reader.create(reader);
            var end = length === undefined ? reader.len : reader.pos + length, message = new $root.Message.ChatMsg();
            while (reader.pos < end) {
                var tag = reader.uint32();
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
        };

        /**
         * Decodes a ChatMsg message from the specified reader or buffer, length delimited.
         * @function decodeDelimited
         * @memberof Message.ChatMsg
         * @static
         * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
         * @returns {Message.ChatMsg} ChatMsg
         * @throws {Error} If the payload is not a reader or valid buffer
         * @throws {$protobuf.util.ProtocolError} If required fields are missing
         */
        ChatMsg.decodeDelimited = function decodeDelimited(reader) {
            if (!(reader instanceof $Reader))
                reader = new $Reader(reader);
            return this.decode(reader, reader.uint32());
        };

        /**
         * Verifies a ChatMsg message.
         * @function verify
         * @memberof Message.ChatMsg
         * @static
         * @param {Object.<string,*>} message Plain object to verify
         * @returns {string|null} `null` if valid, otherwise the reason why it is not
         */
        ChatMsg.verify = function verify(message) {
            if (typeof message !== "object" || message === null)
                return "object expected";
            if (message.userId != null && message.hasOwnProperty("userId"))
                if (!$util.isString(message.userId))
                    return "userId: string expected";
            if (message.userName != null && message.hasOwnProperty("userName"))
                if (!$util.isString(message.userName))
                    return "userName: string expected";
            if (message.acceptId != null && message.hasOwnProperty("acceptId"))
                if (!$util.isString(message.acceptId))
                    return "acceptId: string expected";
            if (message.acceptName != null && message.hasOwnProperty("acceptName"))
                if (!$util.isString(message.acceptName))
                    return "acceptName: string expected";
            if (message.message != null && message.hasOwnProperty("message"))
                if (!$util.isString(message.message))
                    return "message: string expected";
            return null;
        };

        /**
         * Creates a ChatMsg message from a plain object. Also converts values to their respective internal types.
         * @function fromObject
         * @memberof Message.ChatMsg
         * @static
         * @param {Object.<string,*>} object Plain object
         * @returns {Message.ChatMsg} ChatMsg
         */
        ChatMsg.fromObject = function fromObject(object) {
            if (object instanceof $root.Message.ChatMsg)
                return object;
            var message = new $root.Message.ChatMsg();
            if (object.userId != null)
                message.userId = String(object.userId);
            if (object.userName != null)
                message.userName = String(object.userName);
            if (object.acceptId != null)
                message.acceptId = String(object.acceptId);
            if (object.acceptName != null)
                message.acceptName = String(object.acceptName);
            if (object.message != null)
                message.message = String(object.message);
            return message;
        };

        /**
         * Creates a plain object from a ChatMsg message. Also converts values to other types if specified.
         * @function toObject
         * @memberof Message.ChatMsg
         * @static
         * @param {Message.ChatMsg} message ChatMsg
         * @param {$protobuf.IConversionOptions} [options] Conversion options
         * @returns {Object.<string,*>} Plain object
         */
        ChatMsg.toObject = function toObject(message, options) {
            if (!options)
                options = {};
            var object = {};
            if (options.defaults) {
                object.userId = "";
                object.userName = "";
                object.acceptId = "";
                object.acceptName = "";
                object.message = "";
            }
            if (message.userId != null && message.hasOwnProperty("userId"))
                object.userId = message.userId;
            if (message.userName != null && message.hasOwnProperty("userName"))
                object.userName = message.userName;
            if (message.acceptId != null && message.hasOwnProperty("acceptId"))
                object.acceptId = message.acceptId;
            if (message.acceptName != null && message.hasOwnProperty("acceptName"))
                object.acceptName = message.acceptName;
            if (message.message != null && message.hasOwnProperty("message"))
                object.message = message.message;
            return object;
        };

        /**
         * Converts this ChatMsg to JSON.
         * @function toJSON
         * @memberof Message.ChatMsg
         * @instance
         * @returns {Object.<string,*>} JSON object
         */
        ChatMsg.prototype.toJSON = function toJSON() {
            return this.constructor.toObject(this, $protobuf.util.toJSONOptions);
        };

        return ChatMsg;
    })();

    Message.ChatMsgByGroup = (function() {

        /**
         * Properties of a ChatMsgByGroup.
         * @memberof Message
         * @interface IChatMsgByGroup
         * @property {string|null} [userId] ChatMsgByGroup userId
         * @property {string|null} [group] ChatMsgByGroup group
         * @property {string|null} [message] ChatMsgByGroup message
         */

        /**
         * Constructs a new ChatMsgByGroup.
         * @memberof Message
         * @classdesc Represents a ChatMsgByGroup.
         * @implements IChatMsgByGroup
         * @constructor
         * @param {Message.IChatMsgByGroup=} [properties] Properties to set
         */
        function ChatMsgByGroup(properties) {
            if (properties)
                for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                    if (properties[keys[i]] != null)
                        this[keys[i]] = properties[keys[i]];
        }

        /**
         * ChatMsgByGroup userId.
         * @member {string} userId
         * @memberof Message.ChatMsgByGroup
         * @instance
         */
        ChatMsgByGroup.prototype.userId = "";

        /**
         * ChatMsgByGroup group.
         * @member {string} group
         * @memberof Message.ChatMsgByGroup
         * @instance
         */
        ChatMsgByGroup.prototype.group = "";

        /**
         * ChatMsgByGroup message.
         * @member {string} message
         * @memberof Message.ChatMsgByGroup
         * @instance
         */
        ChatMsgByGroup.prototype.message = "";

        /**
         * Creates a new ChatMsgByGroup instance using the specified properties.
         * @function create
         * @memberof Message.ChatMsgByGroup
         * @static
         * @param {Message.IChatMsgByGroup=} [properties] Properties to set
         * @returns {Message.ChatMsgByGroup} ChatMsgByGroup instance
         */
        ChatMsgByGroup.create = function create(properties) {
            return new ChatMsgByGroup(properties);
        };

        /**
         * Encodes the specified ChatMsgByGroup message. Does not implicitly {@link Message.ChatMsgByGroup.verify|verify} messages.
         * @function encode
         * @memberof Message.ChatMsgByGroup
         * @static
         * @param {Message.IChatMsgByGroup} message ChatMsgByGroup message or plain object to encode
         * @param {$protobuf.Writer} [writer] Writer to encode to
         * @returns {$protobuf.Writer} Writer
         */
        ChatMsgByGroup.encode = function encode(message, writer) {
            if (!writer)
                writer = $Writer.create();
            if (message.userId != null && Object.hasOwnProperty.call(message, "userId"))
                writer.uint32(/* id 1, wireType 2 =*/10).string(message.userId);
            if (message.group != null && Object.hasOwnProperty.call(message, "group"))
                writer.uint32(/* id 2, wireType 2 =*/18).string(message.group);
            if (message.message != null && Object.hasOwnProperty.call(message, "message"))
                writer.uint32(/* id 3, wireType 2 =*/26).string(message.message);
            return writer;
        };

        /**
         * Encodes the specified ChatMsgByGroup message, length delimited. Does not implicitly {@link Message.ChatMsgByGroup.verify|verify} messages.
         * @function encodeDelimited
         * @memberof Message.ChatMsgByGroup
         * @static
         * @param {Message.IChatMsgByGroup} message ChatMsgByGroup message or plain object to encode
         * @param {$protobuf.Writer} [writer] Writer to encode to
         * @returns {$protobuf.Writer} Writer
         */
        ChatMsgByGroup.encodeDelimited = function encodeDelimited(message, writer) {
            return this.encode(message, writer).ldelim();
        };

        /**
         * Decodes a ChatMsgByGroup message from the specified reader or buffer.
         * @function decode
         * @memberof Message.ChatMsgByGroup
         * @static
         * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
         * @param {number} [length] Message length if known beforehand
         * @returns {Message.ChatMsgByGroup} ChatMsgByGroup
         * @throws {Error} If the payload is not a reader or valid buffer
         * @throws {$protobuf.util.ProtocolError} If required fields are missing
         */
        ChatMsgByGroup.decode = function decode(reader, length) {
            if (!(reader instanceof $Reader))
                reader = $Reader.create(reader);
            var end = length === undefined ? reader.len : reader.pos + length, message = new $root.Message.ChatMsgByGroup();
            while (reader.pos < end) {
                var tag = reader.uint32();
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
        };

        /**
         * Decodes a ChatMsgByGroup message from the specified reader or buffer, length delimited.
         * @function decodeDelimited
         * @memberof Message.ChatMsgByGroup
         * @static
         * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
         * @returns {Message.ChatMsgByGroup} ChatMsgByGroup
         * @throws {Error} If the payload is not a reader or valid buffer
         * @throws {$protobuf.util.ProtocolError} If required fields are missing
         */
        ChatMsgByGroup.decodeDelimited = function decodeDelimited(reader) {
            if (!(reader instanceof $Reader))
                reader = new $Reader(reader);
            return this.decode(reader, reader.uint32());
        };

        /**
         * Verifies a ChatMsgByGroup message.
         * @function verify
         * @memberof Message.ChatMsgByGroup
         * @static
         * @param {Object.<string,*>} message Plain object to verify
         * @returns {string|null} `null` if valid, otherwise the reason why it is not
         */
        ChatMsgByGroup.verify = function verify(message) {
            if (typeof message !== "object" || message === null)
                return "object expected";
            if (message.userId != null && message.hasOwnProperty("userId"))
                if (!$util.isString(message.userId))
                    return "userId: string expected";
            if (message.group != null && message.hasOwnProperty("group"))
                if (!$util.isString(message.group))
                    return "group: string expected";
            if (message.message != null && message.hasOwnProperty("message"))
                if (!$util.isString(message.message))
                    return "message: string expected";
            return null;
        };

        /**
         * Creates a ChatMsgByGroup message from a plain object. Also converts values to their respective internal types.
         * @function fromObject
         * @memberof Message.ChatMsgByGroup
         * @static
         * @param {Object.<string,*>} object Plain object
         * @returns {Message.ChatMsgByGroup} ChatMsgByGroup
         */
        ChatMsgByGroup.fromObject = function fromObject(object) {
            if (object instanceof $root.Message.ChatMsgByGroup)
                return object;
            var message = new $root.Message.ChatMsgByGroup();
            if (object.userId != null)
                message.userId = String(object.userId);
            if (object.group != null)
                message.group = String(object.group);
            if (object.message != null)
                message.message = String(object.message);
            return message;
        };

        /**
         * Creates a plain object from a ChatMsgByGroup message. Also converts values to other types if specified.
         * @function toObject
         * @memberof Message.ChatMsgByGroup
         * @static
         * @param {Message.ChatMsgByGroup} message ChatMsgByGroup
         * @param {$protobuf.IConversionOptions} [options] Conversion options
         * @returns {Object.<string,*>} Plain object
         */
        ChatMsgByGroup.toObject = function toObject(message, options) {
            if (!options)
                options = {};
            var object = {};
            if (options.defaults) {
                object.userId = "";
                object.group = "";
                object.message = "";
            }
            if (message.userId != null && message.hasOwnProperty("userId"))
                object.userId = message.userId;
            if (message.group != null && message.hasOwnProperty("group"))
                object.group = message.group;
            if (message.message != null && message.hasOwnProperty("message"))
                object.message = message.message;
            return object;
        };

        /**
         * Converts this ChatMsgByGroup to JSON.
         * @function toJSON
         * @memberof Message.ChatMsgByGroup
         * @instance
         * @returns {Object.<string,*>} JSON object
         */
        ChatMsgByGroup.prototype.toJSON = function toJSON() {
            return this.constructor.toObject(this, $protobuf.util.toJSONOptions);
        };

        return ChatMsgByGroup;
    })();

    Message.ChatMsgAll = (function() {

        /**
         * Properties of a ChatMsgAll.
         * @memberof Message
         * @interface IChatMsgAll
         * @property {string|null} [userId] ChatMsgAll userId
         * @property {string|null} [message] ChatMsgAll message
         */

        /**
         * Constructs a new ChatMsgAll.
         * @memberof Message
         * @classdesc Represents a ChatMsgAll.
         * @implements IChatMsgAll
         * @constructor
         * @param {Message.IChatMsgAll=} [properties] Properties to set
         */
        function ChatMsgAll(properties) {
            if (properties)
                for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                    if (properties[keys[i]] != null)
                        this[keys[i]] = properties[keys[i]];
        }

        /**
         * ChatMsgAll userId.
         * @member {string} userId
         * @memberof Message.ChatMsgAll
         * @instance
         */
        ChatMsgAll.prototype.userId = "";

        /**
         * ChatMsgAll message.
         * @member {string} message
         * @memberof Message.ChatMsgAll
         * @instance
         */
        ChatMsgAll.prototype.message = "";

        /**
         * Creates a new ChatMsgAll instance using the specified properties.
         * @function create
         * @memberof Message.ChatMsgAll
         * @static
         * @param {Message.IChatMsgAll=} [properties] Properties to set
         * @returns {Message.ChatMsgAll} ChatMsgAll instance
         */
        ChatMsgAll.create = function create(properties) {
            return new ChatMsgAll(properties);
        };

        /**
         * Encodes the specified ChatMsgAll message. Does not implicitly {@link Message.ChatMsgAll.verify|verify} messages.
         * @function encode
         * @memberof Message.ChatMsgAll
         * @static
         * @param {Message.IChatMsgAll} message ChatMsgAll message or plain object to encode
         * @param {$protobuf.Writer} [writer] Writer to encode to
         * @returns {$protobuf.Writer} Writer
         */
        ChatMsgAll.encode = function encode(message, writer) {
            if (!writer)
                writer = $Writer.create();
            if (message.userId != null && Object.hasOwnProperty.call(message, "userId"))
                writer.uint32(/* id 1, wireType 2 =*/10).string(message.userId);
            if (message.message != null && Object.hasOwnProperty.call(message, "message"))
                writer.uint32(/* id 2, wireType 2 =*/18).string(message.message);
            return writer;
        };

        /**
         * Encodes the specified ChatMsgAll message, length delimited. Does not implicitly {@link Message.ChatMsgAll.verify|verify} messages.
         * @function encodeDelimited
         * @memberof Message.ChatMsgAll
         * @static
         * @param {Message.IChatMsgAll} message ChatMsgAll message or plain object to encode
         * @param {$protobuf.Writer} [writer] Writer to encode to
         * @returns {$protobuf.Writer} Writer
         */
        ChatMsgAll.encodeDelimited = function encodeDelimited(message, writer) {
            return this.encode(message, writer).ldelim();
        };

        /**
         * Decodes a ChatMsgAll message from the specified reader or buffer.
         * @function decode
         * @memberof Message.ChatMsgAll
         * @static
         * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
         * @param {number} [length] Message length if known beforehand
         * @returns {Message.ChatMsgAll} ChatMsgAll
         * @throws {Error} If the payload is not a reader or valid buffer
         * @throws {$protobuf.util.ProtocolError} If required fields are missing
         */
        ChatMsgAll.decode = function decode(reader, length) {
            if (!(reader instanceof $Reader))
                reader = $Reader.create(reader);
            var end = length === undefined ? reader.len : reader.pos + length, message = new $root.Message.ChatMsgAll();
            while (reader.pos < end) {
                var tag = reader.uint32();
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
        };

        /**
         * Decodes a ChatMsgAll message from the specified reader or buffer, length delimited.
         * @function decodeDelimited
         * @memberof Message.ChatMsgAll
         * @static
         * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
         * @returns {Message.ChatMsgAll} ChatMsgAll
         * @throws {Error} If the payload is not a reader or valid buffer
         * @throws {$protobuf.util.ProtocolError} If required fields are missing
         */
        ChatMsgAll.decodeDelimited = function decodeDelimited(reader) {
            if (!(reader instanceof $Reader))
                reader = new $Reader(reader);
            return this.decode(reader, reader.uint32());
        };

        /**
         * Verifies a ChatMsgAll message.
         * @function verify
         * @memberof Message.ChatMsgAll
         * @static
         * @param {Object.<string,*>} message Plain object to verify
         * @returns {string|null} `null` if valid, otherwise the reason why it is not
         */
        ChatMsgAll.verify = function verify(message) {
            if (typeof message !== "object" || message === null)
                return "object expected";
            if (message.userId != null && message.hasOwnProperty("userId"))
                if (!$util.isString(message.userId))
                    return "userId: string expected";
            if (message.message != null && message.hasOwnProperty("message"))
                if (!$util.isString(message.message))
                    return "message: string expected";
            return null;
        };

        /**
         * Creates a ChatMsgAll message from a plain object. Also converts values to their respective internal types.
         * @function fromObject
         * @memberof Message.ChatMsgAll
         * @static
         * @param {Object.<string,*>} object Plain object
         * @returns {Message.ChatMsgAll} ChatMsgAll
         */
        ChatMsgAll.fromObject = function fromObject(object) {
            if (object instanceof $root.Message.ChatMsgAll)
                return object;
            var message = new $root.Message.ChatMsgAll();
            if (object.userId != null)
                message.userId = String(object.userId);
            if (object.message != null)
                message.message = String(object.message);
            return message;
        };

        /**
         * Creates a plain object from a ChatMsgAll message. Also converts values to other types if specified.
         * @function toObject
         * @memberof Message.ChatMsgAll
         * @static
         * @param {Message.ChatMsgAll} message ChatMsgAll
         * @param {$protobuf.IConversionOptions} [options] Conversion options
         * @returns {Object.<string,*>} Plain object
         */
        ChatMsgAll.toObject = function toObject(message, options) {
            if (!options)
                options = {};
            var object = {};
            if (options.defaults) {
                object.userId = "";
                object.message = "";
            }
            if (message.userId != null && message.hasOwnProperty("userId"))
                object.userId = message.userId;
            if (message.message != null && message.hasOwnProperty("message"))
                object.message = message.message;
            return object;
        };

        /**
         * Converts this ChatMsgAll to JSON.
         * @function toJSON
         * @memberof Message.ChatMsgAll
         * @instance
         * @returns {Object.<string,*>} JSON object
         */
        ChatMsgAll.prototype.toJSON = function toJSON() {
            return this.constructor.toObject(this, $protobuf.util.toJSONOptions);
        };

        return ChatMsgAll;
    })();

    Message.BindChannel = (function() {

        /**
         * Properties of a BindChannel.
         * @memberof Message
         * @interface IBindChannel
         * @property {string|null} [userId] BindChannel userId
         * @property {string|null} [userName] BindChannel userName
         */

        /**
         * Constructs a new BindChannel.
         * @memberof Message
         * @classdesc Represents a BindChannel.
         * @implements IBindChannel
         * @constructor
         * @param {Message.IBindChannel=} [properties] Properties to set
         */
        function BindChannel(properties) {
            if (properties)
                for (var keys = Object.keys(properties), i = 0; i < keys.length; ++i)
                    if (properties[keys[i]] != null)
                        this[keys[i]] = properties[keys[i]];
        }

        /**
         * BindChannel userId.
         * @member {string} userId
         * @memberof Message.BindChannel
         * @instance
         */
        BindChannel.prototype.userId = "";

        /**
         * BindChannel userName.
         * @member {string} userName
         * @memberof Message.BindChannel
         * @instance
         */
        BindChannel.prototype.userName = "";

        /**
         * Creates a new BindChannel instance using the specified properties.
         * @function create
         * @memberof Message.BindChannel
         * @static
         * @param {Message.IBindChannel=} [properties] Properties to set
         * @returns {Message.BindChannel} BindChannel instance
         */
        BindChannel.create = function create(properties) {
            return new BindChannel(properties);
        };

        /**
         * Encodes the specified BindChannel message. Does not implicitly {@link Message.BindChannel.verify|verify} messages.
         * @function encode
         * @memberof Message.BindChannel
         * @static
         * @param {Message.IBindChannel} message BindChannel message or plain object to encode
         * @param {$protobuf.Writer} [writer] Writer to encode to
         * @returns {$protobuf.Writer} Writer
         */
        BindChannel.encode = function encode(message, writer) {
            if (!writer)
                writer = $Writer.create();
            if (message.userId != null && Object.hasOwnProperty.call(message, "userId"))
                writer.uint32(/* id 1, wireType 2 =*/10).string(message.userId);
            if (message.userName != null && Object.hasOwnProperty.call(message, "userName"))
                writer.uint32(/* id 2, wireType 2 =*/18).string(message.userName);
            return writer;
        };

        /**
         * Encodes the specified BindChannel message, length delimited. Does not implicitly {@link Message.BindChannel.verify|verify} messages.
         * @function encodeDelimited
         * @memberof Message.BindChannel
         * @static
         * @param {Message.IBindChannel} message BindChannel message or plain object to encode
         * @param {$protobuf.Writer} [writer] Writer to encode to
         * @returns {$protobuf.Writer} Writer
         */
        BindChannel.encodeDelimited = function encodeDelimited(message, writer) {
            return this.encode(message, writer).ldelim();
        };

        /**
         * Decodes a BindChannel message from the specified reader or buffer.
         * @function decode
         * @memberof Message.BindChannel
         * @static
         * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
         * @param {number} [length] Message length if known beforehand
         * @returns {Message.BindChannel} BindChannel
         * @throws {Error} If the payload is not a reader or valid buffer
         * @throws {$protobuf.util.ProtocolError} If required fields are missing
         */
        BindChannel.decode = function decode(reader, length) {
            if (!(reader instanceof $Reader))
                reader = $Reader.create(reader);
            var end = length === undefined ? reader.len : reader.pos + length, message = new $root.Message.BindChannel();
            while (reader.pos < end) {
                var tag = reader.uint32();
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
        };

        /**
         * Decodes a BindChannel message from the specified reader or buffer, length delimited.
         * @function decodeDelimited
         * @memberof Message.BindChannel
         * @static
         * @param {$protobuf.Reader|Uint8Array} reader Reader or buffer to decode from
         * @returns {Message.BindChannel} BindChannel
         * @throws {Error} If the payload is not a reader or valid buffer
         * @throws {$protobuf.util.ProtocolError} If required fields are missing
         */
        BindChannel.decodeDelimited = function decodeDelimited(reader) {
            if (!(reader instanceof $Reader))
                reader = new $Reader(reader);
            return this.decode(reader, reader.uint32());
        };

        /**
         * Verifies a BindChannel message.
         * @function verify
         * @memberof Message.BindChannel
         * @static
         * @param {Object.<string,*>} message Plain object to verify
         * @returns {string|null} `null` if valid, otherwise the reason why it is not
         */
        BindChannel.verify = function verify(message) {
            if (typeof message !== "object" || message === null)
                return "object expected";
            if (message.userId != null && message.hasOwnProperty("userId"))
                if (!$util.isString(message.userId))
                    return "userId: string expected";
            if (message.userName != null && message.hasOwnProperty("userName"))
                if (!$util.isString(message.userName))
                    return "userName: string expected";
            return null;
        };

        /**
         * Creates a BindChannel message from a plain object. Also converts values to their respective internal types.
         * @function fromObject
         * @memberof Message.BindChannel
         * @static
         * @param {Object.<string,*>} object Plain object
         * @returns {Message.BindChannel} BindChannel
         */
        BindChannel.fromObject = function fromObject(object) {
            if (object instanceof $root.Message.BindChannel)
                return object;
            var message = new $root.Message.BindChannel();
            if (object.userId != null)
                message.userId = String(object.userId);
            if (object.userName != null)
                message.userName = String(object.userName);
            return message;
        };

        /**
         * Creates a plain object from a BindChannel message. Also converts values to other types if specified.
         * @function toObject
         * @memberof Message.BindChannel
         * @static
         * @param {Message.BindChannel} message BindChannel
         * @param {$protobuf.IConversionOptions} [options] Conversion options
         * @returns {Object.<string,*>} Plain object
         */
        BindChannel.toObject = function toObject(message, options) {
            if (!options)
                options = {};
            var object = {};
            if (options.defaults) {
                object.userId = "";
                object.userName = "";
            }
            if (message.userId != null && message.hasOwnProperty("userId"))
                object.userId = message.userId;
            if (message.userName != null && message.hasOwnProperty("userName"))
                object.userName = message.userName;
            return object;
        };

        /**
         * Converts this BindChannel to JSON.
         * @function toJSON
         * @memberof Message.BindChannel
         * @instance
         * @returns {Object.<string,*>} JSON object
         */
        BindChannel.prototype.toJSON = function toJSON() {
            return this.constructor.toObject(this, $protobuf.util.toJSONOptions);
        };

        return BindChannel;
    })();

    return Message;
})();

module.exports = $root;
