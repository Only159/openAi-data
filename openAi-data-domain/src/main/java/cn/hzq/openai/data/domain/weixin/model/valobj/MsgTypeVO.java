package cn.hzq.openai.data.domain.weixin.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author 黄照权
 * @Date 2024/4/22
 * @Description 微信公众号消息类型值对象，用于描述对象属性的值，为值对象。
 **/
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum MsgTypeVO {
    EVENT("event", "事件消息"),
    TEXT("text", "文本消息");

    private String code;
    private String desc;
}
