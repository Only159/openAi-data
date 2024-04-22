package cn.hzq.openai.data.domain.weixin.service.validate;

import cn.hzq.openai.data.domain.weixin.service.IWeiXinValidateService;
import cn.hzq.openai.data.types.sdk.weixin.SignatureUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author 黄照权
 * @Date 2024/4/22
 * @Description 验签接口实现
 **/
@Service
public class WeiXinValidateService implements IWeiXinValidateService {
    @Value("${wx.config.token}")
    private String token;

    @Override
    public boolean checkSign(String signature, String timestamp, String nonce) {
        return SignatureUtil.check(token,signature,timestamp,nonce);
    }
}
