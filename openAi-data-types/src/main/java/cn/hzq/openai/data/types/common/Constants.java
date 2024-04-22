package cn.hzq.openai.data.types.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author 黄照权
 * @Date 2024/4/21
 * @Description 统一返回结果
 **/
public class Constants {
    /**
     * @Author HZQ
     * @Date  2024/4/21
     * @Description 统一响应代码
    */
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public enum ResponseCode{
        SUCCESS("0000","成功"),
        UN_ERROR("0001","未知失败"),
        ILLEGAL_PARAMETER("0002","非法参数"),
        TOKEN_ERROR("0003","权限拦截");
        private String code;
        private String info;
    }
}
