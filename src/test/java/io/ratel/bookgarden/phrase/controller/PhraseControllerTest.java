package io.ratel.bookgarden.phrase.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.ratel.bookgarden.common.aop.ApiControllerAdvice;
import io.ratel.bookgarden.common.constants.WebApiConst;
import io.ratel.bookgarden.web_api.phrase.controller.PhraseController;
import io.ratel.bookgarden.web_api.phrase.dto.PhraseCreateRequestDto;
import io.ratel.bookgarden.web_api.phrase.dto.PhraseModifyRequestDto;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * The type Phrase controller test.
 */
@DisplayName("글귀 Controller 테스트")
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PhraseControllerTest {

    private MockMvc mockMvc;

    private final Logger log = LoggerFactory.getLogger(PhraseControllerTest.class);

    private ObjectMapper objectMapper;

    @Autowired
    private PhraseController phraseController;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(phraseController).
                setControllerAdvice(new ApiControllerAdvice()).build();
    }

    /**
     * Given 글귀 id when 글귀정보조회 then 글귀단건.
     *
     * @throws Exception the exception
     */
    @Test
    @DisplayName("[성공] 글귀 정보 조회, ID로 단건조회")
    void given글귀ID_when글귀정보조회_then글귀단건() throws Exception {
        Long userId = 1L;
        String content = "이 멋진 글귀를 보라";
        //when
        ResultActions resultActions = mockMvc.perform(get("/api/v1/phrases/1")
                .header(WebApiConst.USER_ID_HEADER, userId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        log.debug("#DEBUG result {}" + resultActions.andReturn().getResponse().getContentAsString());

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(content));
    }

    /**
     * Given 글귀 when 글귀입력 then 글귀 1 건입력성공.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional
    @DisplayName("[성공] 글귀 정보 입력, 단건입력")
    void given글귀_when글귀입력_then글귀1건입력성공() throws Exception {
        Long userId = 1L;
        Long userBookId = 1L;
        String content = "사회의 어두운 면을 지켜보며 시작";

        PhraseCreateRequestDto phraseCreateRequestDto = PhraseCreateRequestDto.builder().
                userBookId(userBookId).
                content(content).
                build();

        String jsonString = objectMapper.writeValueAsString(phraseCreateRequestDto);

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/v1/phrases").
                header(WebApiConst.USER_ID_HEADER, userId).
                accept(MediaType.APPLICATION_JSON).
                contentType(MediaType.APPLICATION_JSON).
                content(jsonString));

        log.debug("#DEBUG result {}" + resultActions.andReturn().getResponse().getContentAsString());

        //then
        resultActions.andExpect(status().isCreated());
    }

    /**
     * Given 글귀 when 글귀수정 then 글귀 1 건수정성공.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional
    @DisplayName("[성공] 글귀 정보 수정, 단건수정")
    void given글귀_when글귀수정_then글귀1건수정성공() throws Exception {
        Long userId = 1L;
        Long userBookId = 1L;
        Long phraseId = 1L;
        String content = "생성형 AI란 어쩌구 저쩌구";

        PhraseModifyRequestDto phraseModifyRequestDto = PhraseModifyRequestDto.builder().
                id(phraseId).
                userBookId(userBookId).
                content(content).
                build();

        String jsonString = objectMapper.writeValueAsString(phraseModifyRequestDto);

        //when
        ResultActions resultActions = mockMvc.perform(put("/api/v1/phrases").
                header(WebApiConst.USER_ID_HEADER, userId).
                accept(MediaType.APPLICATION_JSON).
                contentType(MediaType.APPLICATION_JSON).
                content(jsonString));

        log.debug("#DEBUG result {}" + resultActions.andReturn().getResponse().getContentAsString());

        //then
        resultActions.andExpect(status().isOk());
    }

    /**
     * Given 글귀 when 글귀삭제 then 글귀 1 건삭제성공.
     *
     * @throws Exception the exception
     */
    @Test
    @Transactional
    @DisplayName("[성공] 글귀 정보 삭제, 단건삭제")
    void given글귀_when글귀삭제_then글귀1건삭제성공() throws Exception {
        Long userId = 1L;

        //when
        ResultActions resultActions = mockMvc.perform(delete("/api/v1/phrases/1").
                header(WebApiConst.USER_ID_HEADER, userId).
                accept(MediaType.APPLICATION_JSON).
                contentType(MediaType.APPLICATION_JSON));

        log.debug("#DEBUG result {}" + resultActions.andReturn().getResponse().getContentAsString());

        //then
        resultActions.andExpect(status().isOk());
    }
}
