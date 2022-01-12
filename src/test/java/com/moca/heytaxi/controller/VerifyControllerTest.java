package com.moca.heytaxi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moca.heytaxi.dto.TokenDTO;
import com.moca.heytaxi.dto.VerifyDTO;
import com.moca.heytaxi.service.VerifyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest
public class VerifyControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private VerifyService verifyService;

    @Test
    public void request() throws Exception {
        VerifyDTO.Response response = new VerifyDTO.Response();
        response.setSuccess(true);
        response.setMessage("pending");
        when(verifyService.request(any(VerifyDTO.Request.class))).thenReturn(response);

        VerifyDTO.Request request = new VerifyDTO.Request();
        request.setPhone("01012345678");

        this.mockMvc.perform(post("/api/verify/request")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))).andExpect(status().isOk())
                .andDo(document("verify-request",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        PayloadDocumentation.requestFields(
                                PayloadDocumentation.fieldWithPath("phone").type(JsonFieldType.STRING).description("핸드폰 번호"),
                                PayloadDocumentation.fieldWithPath("clientSecret").type(JsonFieldType.NULL).description(""),
                                PayloadDocumentation.fieldWithPath("code").type(JsonFieldType.NULL).description("")
                        ),
                        PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("success").description("성공 여부"),
                                PayloadDocumentation.fieldWithPath("message").description("결과 설명")
                        )));
    }

    @Test
    public void verify() throws Exception {
        TokenDTO response = new TokenDTO();
        response.setSuccess(true);
        response.setMessage("approved");
        response.setToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJsb2dnZWRJbkFzIjoiYWRtaW4iLCJpYXQiOjE0MjI3Nzk2Mzh9.gzSraSYS8EXBxLN_oWnFSRgCzcmJmMjLiuyu5CSpyHI");
        when(verifyService.verify(any(VerifyDTO.Request.class))).thenReturn(response);

        VerifyDTO.Request request = new VerifyDTO.Request();
        request.setPhone("01012345678");
        request.setClientSecret("");
        request.setCode("123456");

        this.mockMvc.perform(post("/api/verify")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))).andExpect(status().isOk())
                .andDo(document("verify",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        PayloadDocumentation.requestFields(
                                PayloadDocumentation.fieldWithPath("phone").type(JsonFieldType.STRING).description("핸드폰 번호"),
                                PayloadDocumentation.fieldWithPath("clientSecret").type(JsonFieldType.STRING).description(""),
                                PayloadDocumentation.fieldWithPath("code").type(JsonFieldType.STRING).description("인증 코드")
                        ),
                        PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("success").description("성공 여부"),
                                PayloadDocumentation.fieldWithPath("message").description("결과 설명"),
                                PayloadDocumentation.fieldWithPath("token").description("토큰값")
                        )));
    }


}