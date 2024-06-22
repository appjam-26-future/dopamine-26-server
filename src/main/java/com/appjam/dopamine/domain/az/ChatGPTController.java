package com.appjam.dopamine.domain.az;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chatgpt")
public class ChatGPTController {

    private final ChatGPTService chatGPTService;

    @PostMapping
    public Object createData(@RequestBody MessageRequest imgUrl) throws JsonProcessingException {
        return chatGPTService.createData(imgUrl.message());
    }
}
