package cn.hzq.openai.data.domain.chatgml.service;

import cn.hzq.chatgml.model.ChatCompletionRequest;
import cn.hzq.chatgml.model.ChatCompletionResponse;
import cn.hzq.openai.data.domain.chatgml.model.aggregates.ChatProcessAggregates;
import cn.hzq.openai.data.domain.chatgml.model.entitty.MessageEntity;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 黄照权
 * @Date 2024/4/21
 * @Description
 **/
@Service
@Slf4j
public class ChatService extends AbstractChatService {
    @Override
    protected void doMessageResponse(ChatProcessAggregates chatProcess, ResponseBodyEmitter responseBodyEmitter) throws Exception {
        // 1.构建请求参数信息
        ChatCompletionRequest request = new ChatCompletionRequest();
        // 通过枚举的值（String 获取对应模型枚举对象）
        // request.setModel(Model.valueOf(chatProcess.getModel().toUpperCase()));
        // 可供模型调用的工具列表
        //request.setTools();
        // 消息内容构建 TODO
        /*request.setPrompt(new ArrayList<ChatCompletionRequest.Prompt>() {
            private static final long serialVersionUID = -7988151926241837899L;

            {
                add(ChatCompletionRequest.Prompt.builder()
                        .role(Role.user.getCode())
                        .content("什么是工场模式?-")
                        .build());
            }
        });*/
        request.setPrompt(new ArrayList<ChatCompletionRequest.Prompt>() {
            private static final long serialVersionUID = -7988151926241837899L;
            {
                List<MessageEntity> messages = chatProcess.getMessages();
                for (MessageEntity message : messages) {
                    add(ChatCompletionRequest.Prompt.builder()
                            .role(message.getRole())
                            .content(message.getContent())
                            .build());
                }
            }
        });

        // 3.请求应答
        openAiSession.completions(request, new EventSourceListener() {
            @Override
            public void onEvent(@NotNull EventSource eventSource, @Nullable String id, @Nullable String type, @NotNull String data) {
                ChatCompletionResponse response = JSON.parseObject(data, ChatCompletionResponse.class);
                List<ChatCompletionResponse.Choice> choices = response.getChoices();
                for (ChatCompletionResponse.Choice choice : choices) {
                    ChatCompletionResponse.Delta delta = choice.getDelta();
                    //TODO 角色判断
                    //应答完成
                    String finishReason = choice.getFinishReason();
                    if (StringUtils.isBlank((finishReason)) && "stop".equals(finishReason)){
                        responseBodyEmitter.complete();
                        break;
                    }
                    //发送消息
                    try {
                        responseBodyEmitter.send(delta.getContent());
                    }catch (Exception e){
                        throw new RuntimeException(e);
                    }
                }
            }

            
        });
    }
}
