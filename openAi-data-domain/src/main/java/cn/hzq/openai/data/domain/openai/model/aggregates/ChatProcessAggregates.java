package cn.hzq.openai.data.domain.openai.model.aggregates;

import cn.hzq.openai.data.domain.openai.model.entitty.MessageEntity;
import cn.hzq.openai.data.types.enums.ChatGMLModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 黄照权
 * @Date 2024/4/21
 * @Description 聚合实体类
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatProcessAggregates {
    /**
     * 验证信息
     */
    private String token;
    /**
     * 默认模型
     */
    private  String model = ChatGMLModel.GLM_3_5_TURBO.getCode();
    /**
     * 问题描述
     */
    private List<MessageEntity> messages;
}
