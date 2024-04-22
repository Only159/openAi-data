package cn.hzq.openai.data.trigger.http;

import cn.hzq.openai.data.domain.auth.service.IAuthService;
import cn.hzq.openai.data.domain.chatgml.model.aggregates.ChatProcessAggregates;
import cn.hzq.openai.data.domain.chatgml.model.entitty.MessageEntity;
import cn.hzq.openai.data.domain.chatgml.service.IChatService;
import cn.hzq.openai.data.trigger.http.dto.ChatGMLRequestDTO;
import cn.hzq.openai.data.types.common.Constants;
import cn.hzq.openai.data.types.exception.ChatGMLException;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * @author 黄照权
 * @Date 2024/4/22
 * @Description ChatGML AI 服务
 **/
@Slf4j
@RestController()
@CrossOrigin("${app.config.cross-origin}")
@RequestMapping("/api/${app.config.api-version}/chatgml/")
public class ChatGMLAIServiceController {

    @Resource
    private IChatService chatService;
    @Resource
    private IAuthService authService;


    @PostMapping("chat/completions")
    public ResponseBodyEmitter completionsStream(@RequestBody ChatGMLRequestDTO requestDTO, @RequestHeader("Authorization") String token, HttpServletResponse response) {
        log.info("流式问答请求开始，使用模型：{} 请求信息：{}", requestDTO.getModel(), JSON.toJSONString(requestDTO.getMessages()));

        try{
            // 1、基础配置；流式输出，编码，禁用缓存
            response.setContentType("text/event-stream");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Cache-Control", "no-cache");

            // 2. 构建异步响应对象【对Token 过期拦截】
            ResponseBodyEmitter emitter = new ResponseBodyEmitter(3 * 60 * 1000L);
            boolean success = authService.checkToken(token);

            if (!success) {
                try {
                    emitter.send(Constants.ResponseCode.TOKEN_ERROR.getCode());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                emitter.complete();
                return emitter;
            }
            // 3、构建参数
            ChatProcessAggregates chatProcessAggregates = ChatProcessAggregates.builder()
                    .token(token)
                    .model(requestDTO.getModel())
                    .messages(requestDTO.getMessages().stream()
                            .map(entity -> MessageEntity.builder()
                                    .role(entity.getRole())
                                    .content(entity.getContent())
                                    .name(entity.getName())
                                    .build())
                            .collect(Collectors.toList()))
                    .build();
            // 3、请求结果返回
            return chatService.completions(emitter,chatProcessAggregates);
        } catch (Exception e){
            log.error("流式应答，请求模型：{} 发生异常", requestDTO.getModel(), e);
            throw new ChatGMLException(e.getMessage());
        }
    }
}
