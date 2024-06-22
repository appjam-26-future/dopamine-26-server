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

    public Object genData(Gen gen) throws JsonProcessingException {
        String responseBody = "";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", subscriptionKey);

        HttpEntity<Object> requestEntity = new HttpEntity<>(genWork(gen), headers);
        //System.out.println(requestBody);  //로그 찍기
        ResponseEntity<String> responseEntity = restTemplate.exchange(apiUrl, HttpMethod.POST, requestEntity, String.class);
        responseBody = responseEntity.getBody();


        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        System.out.println(jsonNode);
        String textValue = jsonNode.get("choices").get(0).get("message").get("content").asText();



        return textValue;
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

    public Object genWork(Gen gen) {
        return "{\n" +
                "  \"messages\": [\n" +
                "    {\n" +
                "      \"role\": \"system\",\n" +
                "      \"content\": [\n" +
                "        {\n" +
                "          \"type\": \"text\",\n" +
                "          \"text\": \"너는 사용자가 도파민을 줄이도록 도와줘야해\\n\\n사용자로부터 정보를 받을껀데\\n사용자가 제공해준 정보를 바탕으로 도파민을 줄여가는 최적의 플랜을 제시해줘\\n\\n받는 정보는\\n\\n스크린타임,\\n한달 평균 걸음수,\\n사람 만나는 횟수,\\n운동 횟수, 한 번 당 시간,\\n\\n너가 일주일 일정을 짜줘야해\\n들어가야하는 일정은 다음과 같아\\n\\n핸드폰, 운동하기, 명상하기, 산책하기, 친구와 만남, 야외활동으로 구성해야하며\\n\\n하루에 2개의 활동으로 이루어져있으면 돼 다만 핸드폰 시간은 고정으로 해두고 주말에 핸드폰 시간은 늘려줘 핸드폰 외 하나의 활동은 매일마다 랜덤으로 시간을 몇시간 몇분으로 알려주면 좋겠어\\n\\n반환은 json으로만 해주고 부가설명은 생략해줘\\n\"\n" +
                "        }\n" +
                "      ]\n" +
                "    },\n" +
                "    {\n" +
                "      \"role\": \"user\",\n" +
                "      \"content\": [\n" +
                "        {\n" +
                "          \"type\": \"text\",\n" +
                "          \"text\": \"스크린타임 : " + gen.screenTime() + "시간\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"text\",\n" +
                "          \"text\": \"한달 평균 걸음수 : " + gen.workCount() + "보\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"text\",\n" +
                "          \"text\": \"사람 만나는 횟수 : " + gen.meetPeople() + "화\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"text\",\n" +
                "          \"text\": \"운동 횟수 " + gen.exerciseCount() + "화\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"type\": \"text\",\n" +
                "          \"text\": \"운동 한번당 시간" + gen.exerciseTime() + "시간\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";
    }
}