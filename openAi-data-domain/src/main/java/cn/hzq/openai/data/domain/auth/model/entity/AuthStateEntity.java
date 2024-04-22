package cn.hzq.openai.data.domain.auth.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 黄照权
 * @Date 2024/4/22
 * @Description 鉴权结果
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthStateEntity {
    private String code;
    private String info;
    private String openId;
    private String token;
}
