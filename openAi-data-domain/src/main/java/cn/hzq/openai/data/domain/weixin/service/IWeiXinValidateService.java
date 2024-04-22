package cn.hzq.openai.data.domain.weixin.service;

/**
 * @author 黄照权
 * @Date 2024/4/22
 * @Description 验签接口
 **/
public interface IWeiXinValidateService {
    boolean checkSign(String signature, String timestamp, String nonce);
}
