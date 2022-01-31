package com.moca.heytaxi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moca.heytaxi.domain.Taxi;
import com.moca.heytaxi.domain.User;
import com.moca.heytaxi.dto.TaxiDTO;
import com.moca.heytaxi.service.TaxiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
public class TaxiControllerTest {
    private MockMvc mockMvc;
    private RestDocumentationResultHandler document;
    private final String TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjQxNzQwNDAwLCJleHAiOjE1NDkyNDA5NjAwfQ.8Bp8eQTOHEl0VZr7i99nT3IXq852Dhzz9yIsm6hCb1o";

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TaxiService taxiService;

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
    public void loadMyTaxi() throws Exception {
        final User user = new User();
        user.setId(1L);
        user.setName("홍길동");
        user.setUsername("01012345678");

        final Taxi taxi = new Taxi();
        taxi.setId(1L);
        taxi.setName("그랜저");
        taxi.setCarNumber("123가5678");
        taxi.setUser(user);

        given(taxiService.loadByUserId(any())).willReturn(taxi);

        this.mockMvc.perform(get("/api/taxi").header("Authorization", "Bearer " + TOKEN)).andExpect(status().isOk())
                .andDo(document.document(
                        requestHeaders(
                                headerWithName("Authorization").description("Bearer auth credentials")
                        ),
                        PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("success").type(JsonFieldType.BOOLEAN).description("성공 여부"),
                                PayloadDocumentation.fieldWithPath("message").type(JsonFieldType.STRING).description("결과 설명"),
                                PayloadDocumentation.fieldWithPath("taxi").description("택시 정보"),
                                PayloadDocumentation.fieldWithPath("taxi.name").type(JsonFieldType.STRING).description("택시 이름"),
                                PayloadDocumentation.fieldWithPath("taxi.carNumber").type(JsonFieldType.STRING).description("택시 자동차번호"),
                                PayloadDocumentation.fieldWithPath("taxi.driver").type(JsonFieldType.OBJECT).description("택시 기사 정보"),
                                PayloadDocumentation.fieldWithPath("taxi.driver.name").type(JsonFieldType.STRING).description("택시 기사 이름"),
                                PayloadDocumentation.fieldWithPath("taxi.driver.username").type(JsonFieldType.STRING).description("택시 기사 전화번호")
                                )));
    }

    @Test
    public void registerMyTaxi() throws Exception {
        final Taxi taxi = new Taxi();
        taxi.setName("그랜저");
        taxi.setCarNumber("123가4567");

        given(taxiService.createTaxi(any(Taxi.class))).willReturn(taxi);
        TaxiDTO.Request request = modelMapper.map(taxi, TaxiDTO.Request.class);

        this.mockMvc.perform(post("/api/taxi")
                .header("Authorization", "Bearer " + TOKEN)
                .content(objectMapper.writeValueAsString(request))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andDo(document.document(
                        requestHeaders(
                                headerWithName("Authorization").description("Bearer auth credentials")
                        ),
                        PayloadDocumentation.requestFields(
                                PayloadDocumentation.fieldWithPath("name").type(JsonFieldType.STRING).description("택시 이름"),
                                PayloadDocumentation.fieldWithPath("carNumber").description(JsonFieldType.STRING).description("자동차 번호")
                        ),
                        PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("success").type(JsonFieldType.BOOLEAN).description("성공 여부"),
                                PayloadDocumentation.fieldWithPath("message").type(JsonFieldType.STRING).description("결과 설명"),
                                PayloadDocumentation.fieldWithPath("taxi").description("택시 정보"),
                                PayloadDocumentation.fieldWithPath("taxi.name").description(JsonFieldType.STRING).description("택시 이름"),
                                PayloadDocumentation.fieldWithPath("taxi.carNumber").description(JsonFieldType.STRING).description("택시 자동차번호"),
                                PayloadDocumentation.fieldWithPath("taxi.driver").description(JsonFieldType.OBJECT).description("택시 기사 정보")
                        )
                ));
    }

    @Test
    public void deleteMyTaxi() throws Exception {
        this.mockMvc.perform(delete("/api/taxi").header("Authorization", "Bearer " + TOKEN)).andExpect(status().isOk())
                .andDo(document.document(
                        requestHeaders(
                                headerWithName("Authorization").description("Bearer auth credentials")
                        ),
                        PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("success").type(JsonFieldType.BOOLEAN).description("성공 여부"),
                                PayloadDocumentation.fieldWithPath("message").type(JsonFieldType.STRING).description("결과 설명"),
                                PayloadDocumentation.fieldWithPath("taxi").description("")
                        )));
    }
}
