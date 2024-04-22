package cn.hzq.openai.data.test;

import cn.hzq.chatgml.session.OpenAiSession;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author 黄照权
 * @Date 2024/4/20
 * @Description 测试类
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {
    @Resource
    private OpenAiSession openAiSession;
    @Test
    public void test_chat_completions(){
        // 构建参数

    }
}
