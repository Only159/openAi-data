package cn.hzq.openai.data.domain.chatgml.service;

import cn.hzq.openai.data.domain.chatgml.model.aggregates.ChatProcessAggregates;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

/**
 * @author 黄照权
 * @Date 2024/4/21
 * @Description
 **/
public interface IChatService {
    ResponseBodyEmitter completions(ResponseBodyEmitter emitter,ChatProcessAggregates chatProcess);

}
