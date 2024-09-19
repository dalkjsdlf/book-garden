package io.ratel.bookgarden.journal.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.ratel.bookgarden.common.aop.ApiControllerAdvice;
import io.ratel.bookgarden.common.constants.WebApiConst;
import io.ratel.bookgarden.web_api.journal.controller.JournalController;
import io.ratel.bookgarden.web_api.journal.dto.JournalCreateRequestDto;
import io.ratel.bookgarden.web_api.journal.dto.JournalModifyRequestDto;
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


@DisplayName("독서기록 Controller 테스트")
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class JournalControllerTest {

    private MockMvc mockMvc;

    private final Logger log = LoggerFactory.getLogger(JournalControllerTest.class);

    private ObjectMapper objectMapper;

    @Autowired
    private JournalController journalController;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(journalController).
                setControllerAdvice(new ApiControllerAdvice()).build();
    }

    @Test
    @DisplayName("[성공] 독서기록 정보 조회, ID로 단건조회")
    void given독서기록ID_when독서기록정보조회_then독서기록단건() throws Exception {
        Long userId = 1L;
        String title = "생성형 AI 기본정의";
        //when
        ResultActions resultActions = mockMvc.perform(get("/api/v1/journals/1")
                .header(WebApiConst.USER_ID_HEADER, userId)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        log.debug("#DEBUG result {}" + resultActions.andReturn().getResponse().getContentAsString());

        //then
        resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(title));
    }

    @Test
    @Transactional
    @DisplayName("[성공] 독서기록 정보 입력, 단건입력")
    void given독서기록_when독서기록입력_then독서기록1건입력성공() throws Exception {
        Long userId = 1L;
        Long userBookId = 1L;
        String title = "사회의 어두운 면을 지켜보며 시작";
        String content = "이 책의 첫 페이지의 내용부터 시작되는 부분을 보면 ...";

        JournalCreateRequestDto journalCreateRequestDto = JournalCreateRequestDto.builder().
                userBookId(userBookId).
                title(title).
                content(content).
                build();

        String jsonString = objectMapper.writeValueAsString(journalCreateRequestDto);

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/v1/journals").
                header(WebApiConst.USER_ID_HEADER, userId).
                accept(MediaType.APPLICATION_JSON).
                contentType(MediaType.APPLICATION_JSON).
                content(jsonString));

        log.debug("#DEBUG result {}" + resultActions.andReturn().getResponse().getContentAsString());

        //then
        resultActions.andExpect(status().isCreated());
    }

    @Test
    @Transactional
    @DisplayName("[성공] 독서기록 정보 수정, 단건수정")
    void given독서기록_when독서기록수정_then독서기록1건수정성공() throws Exception {
        Long userId = 1L;
        Long userBookId = 1L;
        Long journalId = 1L;
        String title = "사회의 어두운 면을 지켜보며 시작";
        String content = "생성형 AI란 어쩌구 저쩌구";

        JournalModifyRequestDto journalModifyRequestDto = JournalModifyRequestDto.builder().
                id(journalId).
                userBookId(userBookId).
                title(title).
                content(content).
                build();

        String jsonString = objectMapper.writeValueAsString(journalModifyRequestDto);

        //when
        ResultActions resultActions = mockMvc.perform(put("/api/v1/journals").
                header(WebApiConst.USER_ID_HEADER, userId).
                accept(MediaType.APPLICATION_JSON).
                contentType(MediaType.APPLICATION_JSON).
                content(jsonString));

        log.debug("#DEBUG result {}" + resultActions.andReturn().getResponse().getContentAsString());

        //then
        resultActions.andExpect(status().isOk());
    }

    @Test
    @Transactional
    @DisplayName("[성공] 독서기록 정보 삭제, 단건삭제")
    void given독서기록_when독서기록삭제_then독서기록1건삭제성공() throws Exception {
        Long userId = 1L;

        //when
        ResultActions resultActions = mockMvc.perform(delete("/api/v1/journals/1").
                header(WebApiConst.USER_ID_HEADER, userId).
                accept(MediaType.APPLICATION_JSON).
                contentType(MediaType.APPLICATION_JSON));

        log.debug("#DEBUG result {}" + resultActions.andReturn().getResponse().getContentAsString());

        //then
        resultActions.andExpect(status().isOk());
    }
}
