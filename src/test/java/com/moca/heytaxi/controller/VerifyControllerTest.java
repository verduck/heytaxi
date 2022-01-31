package com.moca.heytaxi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moca.heytaxi.dto.TokenDTO;
import com.moca.heytaxi.dto.VerifyDTO;
import com.moca.heytaxi.service.VerifyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockFilterConfig;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.security.config.BeanIds;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.ServletException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
public class VerifyControllerTest {
    private MockMvc mockMvc;
    private RestDocumentationResultHandler document;

    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private VerifyService verifyService;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) throws ServletException {

        DelegatingFilterProxy delegateProxyFilter = new DelegatingFilterProxy();
        delegateProxyFilter.init(new MockFilterConfig(webApplicationContext.getServletContext(), BeanIds.SPRING_SECURITY_FILTER_CHAIN));

        document = document("{class-name}/{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint()));

        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .alwaysDo(document)
                .apply(documentationConfiguration(restDocumentation))
                .addFilter(delegateProxyFilter)
                .build();
    }

    @Test
    public void request() throws Exception {
        VerifyDTO.Response response = new VerifyDTO.Response();
        response.setSuccess(true);
        response.setMessage("pending");
        given(verifyService.request(any(VerifyDTO.Request.class))).willReturn(response);

        VerifyDTO.Request request = new VerifyDTO.Request();
        request.setPhone("01012345678");

        this.mockMvc.perform(post("/api/verify/request")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))).andExpect(status().isOk())
                .andDo(document.document(
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
        given(verifyService.verify(any(VerifyDTO.Request.class))).willReturn(response);

        VerifyDTO.Request request = new VerifyDTO.Request();
        request.setPhone("01012345678");
        request.setClientSecret("");
        request.setCode("123456");

        this.mockMvc.perform(post("/api/verify/verify")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))).andExpect(status().isOk())
                .andDo(document.document(
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
