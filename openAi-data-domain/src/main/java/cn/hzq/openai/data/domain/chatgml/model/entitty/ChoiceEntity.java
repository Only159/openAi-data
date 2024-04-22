package cn.hzq.openai.data.domain.chatgml.model.entitty;

import lombok.Data;

/**
 * @author 黄照权
 * @Date 2024/4/21
 * @Description 选择实体类
 **/
@Data
public class ChoiceEntity {
    /**
     * stream = true 请求参数里返回的属性是 delta
     */
    private MessageEntity delta;
    /**
     * stream = false 请求参数里返回的属性是 delta
     */
    private MessageEntity message;
}
