package cn.hzq.openai.data.trigger.http.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 黄照权
 * @Date 2024/4/22
 * @Description
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageEntity {
    private String role;
    private String content;
    private String name;
}
