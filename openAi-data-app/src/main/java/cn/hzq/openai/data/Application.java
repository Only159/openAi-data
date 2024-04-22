package cn.hzq.openai.data;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 黄照权
 * @Date 2024/4/19
 * @Description 主启动类
 **/
@SpringBootApplication
@Configurable

public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
