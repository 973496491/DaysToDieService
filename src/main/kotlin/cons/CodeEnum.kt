package com.loko.utils.cons

enum class CodeEnum(val code: Int, val message: String) {
    SUCCESS(code = 0, message = "成功"),

    FAILED(code = -1, message = "失败"),

    REGISTER_SUCCESS(code = SUCCESS.code, message = "注册成功."),

    REGISTER_FAILED(code = 100001, message = "注册失败."),

    REGISTERED(code = 100002, message = "用户已注册."),

    NOT_USER(code = 100003, message = "用户不存在."),

    PASSWORD_FAILED(code = 100004, message = "用户密码错误."),

    USERINFO_NOTHING(code = 100005, message = "获取用户信息失败."),

    TOKEN_INVALID(code = 100006, message = "token无效."),

    TOKEN_GENERATE_FAILED(code = 100007, message = "token生成失败."),

    USER_CONFIG_FAILED(code = 100008, message = "生成用户信息失败."),

    FILE_IS_EMPTY(code = 100009, message = "当前文件为空."),

    FILE_UPLOAD_FAILED(code = 100010, message = "文件上传失败."),

    FILE_INSERT_FAILED(code = 100011, message = "文件插入数据库失败."),

    DATABASE_FILE_NOT_EXIST(code = 100012, message = "数据库文件不存在."),

    FILE_DELETE_FAILED(code = 100013, message = "删除文件失败."),

    GET_ILLUSTRATED_INFO_GROUP_FAILED(code = 100014, message = "获取图鉴分组信息失败."),

    UPDATE_ILLUSTRATED_INFO_FAILED(code = 100015, message = "更新图鉴信息失败."),

    INSERT_ILLUSTRATED_INFO_FAILED(code = 100016, message = "插入图鉴信息失败."),

    ILLUSTRATED_INFO_UNKNOWN(code = 100017, message = "图鉴信息不存在."),

    DELETE_ZOMBIE_FAILED(code = 100018, message = "删除古神失败."),

    INSERT_ZOMBIE_FAILED(code = 100019, message = "插入古神信息失败."),

    UPDATE_ZOMBIE_FAILED(code = 100020, message = "更新古神信息失败."),

    DELETE_PROP_FAILED(code = 100021, message = "删除道具失败."),

    INSERT_PROP_FAILED(code = 100022, message = "插入道具信息失败."),

    UPDATE_PROP_FAILED(code = 100023, message = "更新道具信息失败."),

    XML_INFO_IS_NULL(code = 100024, message = "XML信息不存在."),

    WHITELIST_NOT_FOUND(code = 100025, message = "白名单信息不存在."),

    KEY_INFO_NOT_FOUND(code = 100026, message = "密钥信息不存在."),
}