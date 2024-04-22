package cn.hzq.openai.data.types.exception;

/**
 * @author 黄照权
 * @Date 2024/4/21
 * @Description 智普AI异常
 **/
public class ChatGMLException extends RuntimeException {
    /**
     * 异常状态码
     */
    private String code;
    /**
     * 异常信息
     */
    private String message;

    /**
     * ChatGML 异常构造器
     *
     * @param code 状态码
     */
    public ChatGMLException(String code) {
        this.code = code;
    }

    /**
     * ChatGML 异常构造器
     *
     * @param code    状态码
     * @param message 异常信息
     */
    public ChatGMLException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * ChatGML 异常构造器
     *
     * @param code  状态码
     * @param cause 异常原因 （Throwable）
     */
    public ChatGMLException(String code, Throwable cause) {
        this.code = code;
        super.initCause(cause);
    }

    /**
     * ChatGML 异常构造器
     * @param code 状态码
     * @param message 异常信息
     * @param cause 异常原因（Throwable）
     */
    public ChatGMLException(String code, String message, Throwable cause) {
        this.code = code;
        this.message = message;
        super.initCause(cause);
    }


}
