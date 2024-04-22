package cn.hzq.openai.data.trigger.http.dto;

import cn.hzq.openai.data.types.enums.ChatGMLModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 黄照权
 * @Date 2024/4/22
 * @Description
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatGMLRequestDTO {
    /** 默认模型 */
    private String model = ChatGMLModel.CHATGLM_TURBO.getCode();

    /** 问题描述 */
    private List<MessageEntity> messages;
}
