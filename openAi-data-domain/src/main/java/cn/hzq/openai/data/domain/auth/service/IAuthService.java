package cn.hzq.openai.data.domain.auth.service;

import cn.hzq.openai.data.domain.auth.model.entity.AuthStateEntity;

/**
 * @author 黄照权
 * @Date 2024/4/22
 * @Description 鉴权验证服务接口
 **/
public interface IAuthService {
    /**
     * 登录验证
     * @param code 验证码
     * @return Token
     */
    AuthStateEntity doLogin(String code);
    boolean checkToken(String token);
}
