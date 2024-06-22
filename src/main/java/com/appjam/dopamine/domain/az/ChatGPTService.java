package com.appjam.dopamine.domain.az;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChatGPTService {


    // 지피티 요청 주소
    @Value("${open.ai.api.url}")
    private String apiUrl;

    // api key (지피티)
    @Value("${open.ai.api.subscriptionKey}")
    private String subscriptionKey;

    private final RestTemplate restTemplate;

    public Object createData(String chat) throws JsonProcessingException {
        String responseBody = "";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", subscriptionKey);

        HttpEntity<String> requestEntity = new HttpEntity<>(postGPT(chat), headers);
        //System.out.println(requestBody);  //로그 찍기
        ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);
        responseBody = responseEntity.getBody();


        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        System.out.println(jsonNode);
        String textValue = jsonNode.get("choices").get(0).get("message").get("content").asText();

        Map<String, String> map = new HashMap<>();
        map.put("massage", textValue);

        return map;
    }

    public String postGPT(String url) {
        return "{\n" +
                "  \"messages\": [\n" +
                "    {\n" +
                "      \"role\": \"system\",\n" +
                "      \"content\": [\n" +
                "        {\n" +
                "          \"type\": \"text\",\n" +
                "          \"text\": \" 사용자가 파일을 보낼껀데 그 사진 안에 사람이 2명이하면 0 아니면 1 만 숫자로 보내줘\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"role\": \"user\",\n" +
                "      \"content\": [\n" +
                "        {\n" +
                "          \"type\": \"image_url\",\n" +
                "          \"image_url\": {\n" +
                "            \"url\": \"" + url+ "\"\n" +
                "          }\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"role\": \"assistant\",\n" +
                "      \"content\": [\n" +
                "        {\n" +
                "          \"type\": \"text\",\n" +
                "          \"text\": \"{\\n   \\\"count\\\": 2\\n}\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ],\n" +
                "  \"temperature\": 0.7,\n" +
                "  \"top_p\": 0.95,\n" +
                "  \"max_tokens\": 800\n" +
                "}";
    }
}