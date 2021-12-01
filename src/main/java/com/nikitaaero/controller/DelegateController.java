package com.nikitaaero.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class DelegateController {
    private final RestTemplate template;
    private final String delegateeURL;

    public DelegateController(@Value("${delegatee.host}") String delegateeHost) {
        this.delegateeURL = "http://" + delegateeHost;
        this.template = restTemplate();
    }

    @GetMapping(value = "/", produces = MimeTypeUtils.TEXT_HTML_VALUE)
    ResponseEntity<String> delegate() {
        System.out.println("delegate handler invoked");
        return template.getForEntity(delegateeURL, String.class);
    }

    private static RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_HTML));
        restTemplate.getMessageConverters().add(converter);
        return restTemplate;
    }
}
