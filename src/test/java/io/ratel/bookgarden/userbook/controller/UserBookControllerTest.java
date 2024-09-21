package io.ratel.bookgarden.userbook.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.ratel.bookgarden.common.aop.ApiControllerAdvice;
import io.ratel.bookgarden.common.constants.WebApiConst;
import io.ratel.bookgarden.domain.userbook.entity.Yn;
import io.ratel.bookgarden.web_api.userbook.controller.UserBookController;
import io.ratel.bookgarden.web_api.userbook.dto.UserBookCreateRequestDto;
import io.ratel.bookgarden.web_api.userbook.dto.UserBookModifyRequestDto;
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

@DisplayName("사용자도서 Controller 테스트")
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserBookControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;
    private final Logger log = LoggerFactory.getLogger(UserBookControllerTest.class);

    @Autowired
    private UserBookController userBookController;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userBookController).
                setControllerAdvice(new ApiControllerAdvice()).build();
    }

    @Test
    @DisplayName("[성공] 사용자도서 목록조회, 사용자 ID로 다건조회")
    void given사용자ID_when사용자도서목록조회_then사용자도서목록() throws Exception {
        Long userId = 1L;
        String title = "Spring Boot Guide";
        Yn yn = Yn.N;
        ResultActions resultActions = mockMvc.perform(get("/api/v1/userbooks").
                        header(WebApiConst.USER_ID_HEADER, userId).
                        contentType(MediaType.APPLICATION_JSON).
                        accept(MediaType.APPLICATION_JSON));

        log.debug("#DEBUG 사용자도서 조회 RESPONSE값 조회 {}", resultActions.
                andReturn().
                getResponse().
                getContentAsString());

        resultActions.andExpect(status().isOk()).
                andExpect(jsonPath("$[0].title").value(title)).
                andExpect(jsonPath("$[0].readCmpYn").value(yn.toString()));
    }

    @Test
    @DisplayName("[성공] 사용자도서 조회, 사용자도서 ID로 단건조회")
    void given사용자도서ID_when사용자도서조회_then사용자도서() throws Exception {
        Long userId = 1L;
        String title = "Spring Boot Guide";
        String author = "John Doe";
        String journalTitle = "생성형 AI 기본정의";
        ResultActions resultActions = mockMvc.perform(get("/api/v1/userbooks/1").
                header(WebApiConst.USER_ID_HEADER, userId).
                contentType(MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON));

        log.debug("#DEBUG 사용자도서 조회 RESPONSE값 조회 {}", resultActions.
                andReturn().
                getResponse().
                getContentAsString());

        resultActions.andExpect(status().isOk()).
                andExpect(jsonPath("$.title").value(title)).
                andExpect(jsonPath("$.author").value(author)).
                andExpect(jsonPath("$.readCmpYn").value(Yn.N.toString())).
                andExpect(jsonPath("$.bookId").value(1)).
                andExpect(jsonPath("$.journalEntities.size()").value(1)).
                andExpect(jsonPath("$.journalEntities[0].title").value(journalTitle));
    }


    @Test
    @Transactional
    @DisplayName("[성공] 사용자도서 정보 입력, 단건입력")
    void given사용자도서_when사용자도서입력_then사용자도서1건입력성공() throws Exception {

        Long userId = 1L;
        Long bookId = 2L;
        Yn yn = Yn.N;

        UserBookCreateRequestDto userBookCreateRequestDto = UserBookCreateRequestDto.builder().
                bookId(bookId).
                readCmpYn(yn).
                build();

        String jsonString = objectMapper.writeValueAsString(userBookCreateRequestDto);

        //when
        ResultActions resultActions = mockMvc.perform(post("/api/v1/userbooks").
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
    @DisplayName("[성공] 사용자도서 정보 수정, 단건수정")
    void given사용자도서_when사용자도서수정_then사용자도서1건수정성공() throws Exception {
        Long userId = 1L;
        Long bookId = 1L;
        Long id = 1L;

        UserBookModifyRequestDto userBookModifyRequestDto = UserBookModifyRequestDto.builder().
                id(id).
                userId(userId).
                bookId(bookId).
                readCmpYn(Yn.N).
                build();

        String jsonString = objectMapper.writeValueAsString(userBookModifyRequestDto);

        //when
        ResultActions resultActions = mockMvc.perform(put("/api/v1/userbooks/1").
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
    @DisplayName("[성공] 사용자도서 정보 삭제, 단건삭제")
    void given사용자도서_when사용자도서삭제_then사용자도서1건삭제성공() throws Exception {
        Long userId = 1L;

        //when
        ResultActions resultActions = mockMvc.perform(delete("/api/v1/userbooks/1").
                header(WebApiConst.USER_ID_HEADER, userId).
                accept(MediaType.APPLICATION_JSON).
                contentType(MediaType.APPLICATION_JSON));

        log.debug("#DEBUG result {}" + resultActions.andReturn().getResponse().getContentAsString());

        //then
        resultActions.andExpect(status().isOk());
    }
}
