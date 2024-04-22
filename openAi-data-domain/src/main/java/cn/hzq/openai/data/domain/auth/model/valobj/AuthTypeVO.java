package cn.hzq.openai.data.domain.auth.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author 黄照权
 * @Date 2024/4/22
 * @Description 验证结果枚举类
 **/
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum AuthTypeVO {
    A0000("0000","验证成功"),
    A0001("0001","验证码不存在"),
    A0002("0002","验证码无效");

    private String code;
    private String info;

}
