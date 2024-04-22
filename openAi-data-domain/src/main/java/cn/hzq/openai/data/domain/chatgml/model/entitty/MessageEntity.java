package cn.hzq.openai.data.domain.chatgml.model.entitty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 黄照权
 * @Date 2024/4/21
 * @Description 消息实体类
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageEntity {
    /**
     * 角色
     */
    private String role;
    /**
     * 内容
     */
    private String content;
    /**
     * 姓名
     */
    private String name;
}
