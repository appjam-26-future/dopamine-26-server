package com.appjam.dopamine.domain.az;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chatgpt")
@Tag(name = "ai", description = "ai 관련 api 입니다.")
public class ChatGPTController {

    private final ChatGPTService chatGPTService;

    @PostMapping("/image")
    @Operation(summary = "이미지 판별 ai", description = "사진 안에 사람이 2명 이하면 0 아니면 1")
    public Object createData(@RequestBody MessageRequest imgUrl) throws JsonProcessingException {
        return chatGPTService.createData(imgUrl.message());
    }

    @PostMapping("/gen")
    @Operation(summary = "ai 도파민 미션 루틴", description = "일주일간의 미션을 ai로 계획합니다.")
    public Object genData(@RequestBody Gen gen) throws JsonProcessingException {
        return chatGPTService.genData(gen);
    }
}