package cn.hzq.openai.data.config;

import cn.hzq.chatgml.session.OpenAiSession;
import cn.hzq.chatgml.session.defaults.DefaultOpenAiSessionFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 黄照权
 * @Date 2024/4/21
 * @Description 智谱AI配置类
 **/
@Configuration
@EnableConfigurationProperties(ChatGMLSDKConfigProperties.class)
public class ChatGMLSDKConfig {
    /**
     * 开启智谱AI会话
     * @param properties 智谱AI配置文件
     */
    @Bean
    public OpenAiSession openAiSession(ChatGMLSDKConfigProperties properties) {
        // 配置文件
        cn.hzq.chatgml.session.Configuration configuration = new cn.hzq.chatgml.session.Configuration();
        configuration.setApiHost(properties.getApiHost());
        configuration.setApiSecretKey(properties.getApiKey());
        // 会话工厂
        DefaultOpenAiSessionFactory factory = new DefaultOpenAiSessionFactory(configuration);
        // 开启会话
        return factory.openSession();
    }
}
