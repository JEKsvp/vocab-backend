package com.abadeksvp.vocabbackend.integration;

import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;
import org.springframework.http.MediaType;

import java.nio.charset.Charset;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class SignUpIntegrationTest extends AbstractIntegrationTest{

    @Test
    public void validSignUpTest() throws Exception {
        String requestBody = IOUtils.toString(getClass().getResource("/request/sign-up-request.json"), Charset.defaultCharset());
        String expectedResponse = IOUtils.toString(getClass().getResource("/response/sign-up-response.json"), Charset.defaultCharset());
        String actualResponse = mockMvc.perform(post("/v1/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        JSONAssert.assertEquals(expectedResponse, actualResponse, excludeFields("id"));
    }

    @Test
    public void invalidSignUpTest() throws Exception {
        String requestBody = IOUtils.toString(getClass().getResource("/request/invalid-sign-up-request.json"), Charset.defaultCharset());
        String expectedResponse = IOUtils.toString(getClass().getResource("/response/invalid-sign-up-response.json"), Charset.defaultCharset());
        String actualResponse = mockMvc.perform(post("/v1/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().is4xxClientError())
                .andReturn().getResponse().getContentAsString();
        JSONAssert.assertEquals(expectedResponse, actualResponse, excludeFields("id"));
    }

    @NotNull
    private CustomComparator excludeFields(String... fields) {
        Customization[] customizations = Arrays.stream(fields)
                .map(field -> new Customization(field, (o1, o2) -> true))
                .toArray(Customization[]::new);
        return new CustomComparator(JSONCompareMode.NON_EXTENSIBLE, customizations);
    }
}
