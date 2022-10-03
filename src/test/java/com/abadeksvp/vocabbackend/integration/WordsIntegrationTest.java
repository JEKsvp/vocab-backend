package com.abadeksvp.vocabbackend.integration;

import com.abadeksvp.vocabbackend.integration.helpers.TestUuidGenerator;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.testcontainers.shaded.com.google.common.base.Charsets;

import java.nio.charset.Charset;
import java.util.UUID;

import static com.abadeksvp.vocabbackend.integration.helpers.TestUserManager.DEFAULT_TEST_USERNAME;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class WordsIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private TestUuidGenerator uuidGenerator;

    @Test
    public void integrationTest() throws Exception {
        testUserManager.signUpDefaultTestUser();
        HttpHeaders authHeader = testUserManager.obtainDefaultUserHeader();
        UUID WORD_UUID = UUID.fromString("5d764a34-04c7-4878-a676-8574f9a336a4");
        uuidGenerator.setUuid(WORD_UUID);

        String createWordRequest = IOUtils.toString(getClass().getResource("/request/words/create-word-request.json"), Charsets.UTF_8);
        String actualCreateWordResponse = mockMvc.perform(post("/v1/words")
                        .contentType(MediaType.APPLICATION_JSON)
                        .headers(authHeader)
                        .content(createWordRequest))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String expectedCreateWordResponse = IOUtils.toString(getClass().getResource("/response/words/create-word-response.json"), Charsets.UTF_8);
        JSONAssert.assertEquals(expectedCreateWordResponse, actualCreateWordResponse, JSONCompareMode.NON_EXTENSIBLE);


        String updateWordRequest = IOUtils.toString(getClass().getResource("/request/words/update-word-request.json"), Charsets.UTF_8);
        String actualUpdateWordResponse = mockMvc.perform(put("/v1/words")
                        .contentType(MediaType.APPLICATION_JSON)
                        .headers(authHeader)
                        .content(updateWordRequest))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String expectedUpdateWordResponse = IOUtils.toString(getClass().getResource("/response/words/update-word-response.json"), Charsets.UTF_8);
        JSONAssert.assertEquals(expectedUpdateWordResponse, actualUpdateWordResponse, JSONCompareMode.NON_EXTENSIBLE);

        String changeStatusRequest = IOUtils.toString(getClass().getResource("/request/words/change-status-request.json"), Charsets.UTF_8);
        String actualChangeStatusWordResponse = mockMvc.perform(patch("/v1/words/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .headers(authHeader)
                        .content(changeStatusRequest))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String expectedChangeStatusRequest = IOUtils.toString(getClass().getResource("/response/words/change-status-response.json"), Charsets.UTF_8);
        JSONAssert.assertEquals(expectedChangeStatusRequest, actualChangeStatusWordResponse, JSONCompareMode.NON_EXTENSIBLE);
    }
}
