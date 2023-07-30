package com.library.management.notification.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Slf4j
@ConfigurationProperties(prefix = "library", ignoreUnknownFields = true)
@Component
public class MemberServiceRestTemplate {

    public static final String MEMBER_PATH = "/api/v1/member/all-emails";
    private final RestTemplate restTemplate;

    private String memberServiceHost;

    public void setMemberServiceHost(String memberServiceHost) {
        this.memberServiceHost = memberServiceHost;
    }

    public MemberServiceRestTemplate(RestTemplateBuilder restTemplateBuilder,
                                     @Value("${library.member-service.user}") String memberUser,
                                     @Value("${library.member-service.password}")String memberPassword) {
        this.restTemplate = restTemplateBuilder
                .basicAuthentication(memberUser, memberPassword)
                .build();
    }

    public List<String> getEmailAddressAll() {

        log.debug("Calling Member Service");

        ResponseEntity<List<String>> responseEntity = restTemplate
                .exchange(memberServiceHost + MEMBER_PATH, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<String>>(){});



        List<String>  emailAddresses = Objects.requireNonNull(responseEntity.getBody());

        return emailAddresses;
    }

}
