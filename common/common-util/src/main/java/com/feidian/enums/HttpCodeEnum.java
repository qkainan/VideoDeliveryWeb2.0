package com.feidian.enums;

public enum HttpCodeEnum {
    // 成功
    SUCCESS(200,"操作成功"),
    // 登录
    NEED_LOGIN(401,"需要登录后操作"),
    NO_OPERATOR_AUTH(403,"无权限操作"),
    SYSTEM_ERROR(500,"出现错误"),
    USERNAME_EXIST(501,"用户名已存在"),
    PHONENUMBER_EXIST(502,"手机号已存在"),
    EMAIL_EXIST(503, "邮箱已存在"),
    REQUIRE_USERNAME(504, "必需填写用户名"),
    LOGIN_ERROR(505,"用户名或密码错误"),
    CONTENI_NOT_NULL(506,"评论内容不能为空" ),

    FILE_TYPE_ERROR(507,"文件类型错误，请上传png文件" ),
    USERNAME_NOT_NULL(508, "用户名不能为空"),
    GENDER_NOT_NULL(509, "性别不能为空"),
    PASSWORD_NOT_NULL(510, "密码不能为空"),
    EMAIL_NOT_NULL(511, "邮箱不能为空"),
    NICKNAME_EXIST(512, "昵称已存在"),
    ISADMIN_NOT_SPECIFICATION(513,"是否为管理员字符不规范"),
    PHONE_NOT_NULL(514,"电话号码不能为空" ),
    PHONE_EXIST(514,"电话号码已经存在" ),
    PASSWORD_NOT_STANDARDIZED(515,"密码不符合规范" ),
    STARTDATE_NOT_NULL(516,"开始时间不能为空" ),
    ENDDATE_NOT_NULL(517, "结束时间不能为空"),
    USERID_NOT_NULL(518, "用户ID不能为空"),
    TOTALDAYS_NOT_NULL(519, "总天数不能为空"),
    WORDSPERDAY_NOT_NULL(520, "每天单词数量不能为空"),
    STARTDATE_NOT_STANDARDIZED(521,"开始日期必须大于今天" ),
    MEMORYSTATE_NOT_CORRECT(522,"记忆状态不符合规范" ),
    NOT_THIS_WORD(523, "没有该单词"),
    REMEMBERNUMBER_TO_BIG(524, "记忆数量过大"),
    WORDBOOKID_NOT_NULL(525,"单词书id不能为null" );
    int code;
    String msg;

    HttpCodeEnum(int code, String errorMessage){
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}