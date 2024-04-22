package cn.hzq.openai.data.domain.weixin.service;

import cn.hzq.openai.data.domain.weixin.model.entity.UserBehaviorMessageEntity;

/**
 * @author 黄照权
 * @Date 2024/4/22
 * @Description 受理用户行为接口
 **/
public interface IWeiXinBehaviorService {
    String acceptUserBehavior(UserBehaviorMessageEntity userBehaviorMessageEntity);
}
