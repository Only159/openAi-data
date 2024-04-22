package cn.hzq.openai.data.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author 黄照权
 * @Date 2024/4/21
 * @Description 智谱AI配置文件
 **/

@Data
@ConfigurationProperties(prefix = "chatgml.sdk.config",ignoreInvalidFields = true)
public class ChatGMLSDKConfigProperties {
    /**
     * 转发地址
     */
    private String apiHost;
    /**
     * apiKey
     */
    private String apiKey;
}
