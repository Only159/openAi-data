package cn.hzq.openai.data.domain.openai.service;

import cn.hzq.chatgml.session.OpenAiSession;
import cn.hzq.openai.data.domain.openai.model.aggregates.ChatProcessAggregates;
import cn.hzq.openai.data.types.common.Constants;
import cn.hzq.openai.data.types.exception.ChatGMLException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import javax.annotation.Resource;

/**
 * @author 黄照权
 * @Date 2024/4/21
 * @Description
 **/
@Slf4j
public abstract class AbstractChatService implements IChatService {
    @Resource
    protected OpenAiSession openAiSession;

    @Override
    public ResponseBodyEmitter completions(ChatProcessAggregates chatProcess) {
        // 1、校验权限 TODO:ChatGPT需要校验  ChatGML 不需要校验token

        // 2、请求应答
        ResponseBodyEmitter emitter = new ResponseBodyEmitter(3 * 60 * 1000L);
        emitter.onCompletion(() -> {
            log.info("流式问答请求完成，使用模型：{}", chatProcess.getModel());
        });
        emitter.onError(throwable -> log.error("流式问答请求异常，使用模型：{}", chatProcess.getModel(), throwable));

        // 3、应答处理
        try {
            this.doMessageResponse(chatProcess, emitter);
        } catch (Exception e) {
            throw new ChatGMLException(Constants.ResponseCode.UN_ERROR.getCode(), Constants.ResponseCode.UN_ERROR.getInfo());
        }
        // 4、返回结果
        return emitter;
    }

    protected abstract void doMessageResponse(ChatProcessAggregates chatProcess, ResponseBodyEmitter responseBodyEmitter) throws Exception;
}
