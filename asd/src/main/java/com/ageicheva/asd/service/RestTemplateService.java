package com.ageicheva.asd.service;

import com.ageicheva.common.dto.BuyDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@AllArgsConstructor
@Service
public class RestTemplateService {
    private RestTemplate restTemplate;
    Gson gson = new GsonBuilder().create();


    public BuyDTO postBuy(BuyDTO dto) {
        String payLoadStr = gson.toJson(dto);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("path", payLoadStr);
        HttpEntity<BuyDTO> requestBody = new HttpEntity<>(dto, headers);
       ResponseEntity<BuyDTO> responseEntity = restTemplate.exchange(
                "http://localhost:8082/buy",
                HttpMethod.POST,
               requestBody,
                BuyDTO.class
        );
        return responseEntity.getBody();

    }

}
