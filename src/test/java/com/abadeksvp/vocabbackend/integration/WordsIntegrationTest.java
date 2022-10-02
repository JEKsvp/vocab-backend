package com.abadeksvp.vocabbackend.integration;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.http.MediaType;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class WordsIntegrationTest extends AbstractIntegrationTest {

    public static final String ID_PLACEHOLDER = "<ID>";

    @Test
    public void integrationTest() throws Exception {
        String createWordRequest = IOUtils.toString(getClass().getResource("/request/words/create-word-request.json"), Charset.defaultCharset());
        String actualCreateWordResponse = mockMvc.perform(post("/v1/words")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createWordRequest))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String wordId = new JSONObject(actualCreateWordResponse).get("id").toString();
        String expectedCreateWordResponse = IOUtils.toString(getClass().getResource("/response/words/create-word-response.json"), Charset.defaultCharset())
                .replaceAll(ID_PLACEHOLDER, wordId);
        JSONAssert.assertEquals(expectedCreateWordResponse, actualCreateWordResponse, JSONCompareMode.NON_EXTENSIBLE);


        String updateWordRequest = IOUtils.toString(getClass().getResource("/request/words/update-word-request.json"), Charset.defaultCharset());
        String actualUpdateWordResponse = mockMvc.perform(put("/v1/words")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateWordRequest))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String expectedUpdateWordResponse = IOUtils.toString(getClass().getResource("/response/words/update-word-response.json"), Charset.defaultCharset())
                .replaceAll(ID_PLACEHOLDER, wordId);
        JSONAssert.assertEquals(expectedUpdateWordResponse, actualUpdateWordResponse, JSONCompareMode.NON_EXTENSIBLE);

        String changeStatusRequest = IOUtils.toString(getClass().getResource("/request/words/change-status-request.json"), Charset.defaultCharset());
        String actualChangeStatusWordResponse = mockMvc.perform(patch("/v1/words/status")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(changeStatusRequest))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        String expectedChangeStatusRequest = IOUtils.toString(getClass().getResource("/response/words/change-status-response.json"), Charset.defaultCharset())
                .replaceAll(ID_PLACEHOLDER, wordId);
        JSONAssert.assertEquals(expectedChangeStatusRequest, actualChangeStatusWordResponse, JSONCompareMode.NON_EXTENSIBLE);
    }
}
