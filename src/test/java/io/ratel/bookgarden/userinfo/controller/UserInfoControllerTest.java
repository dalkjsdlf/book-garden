package io.ratel.bookgarden.userinfo.controller;

import io.ratel.bookgarden.common.aop.ApiControllerAdvice;
import io.ratel.bookgarden.common.constants.WebApiConst;
import io.ratel.bookgarden.web_api.userinfo.controller.UserInfoController;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@Transactional
@DisplayName("사용자정보 컨트롤러 조회 테스트")
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class UserInfoControllerTest {

    private MockMvc mockMvc;

    private final Logger log = LoggerFactory.getLogger(UserInfoControllerTest.class);

    @Autowired
    private UserInfoController userInfoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userInfoController).
                setControllerAdvice(new ApiControllerAdvice()).build();
    }

    @Test
    @DisplayName("[성공] 사용자 정보 조회, 다건조회")
    void given조건없음_when사용자정보조회_then사용자정보다건() throws Exception {
        Long userId = 1L;
        String name = "yeonho choi";
        String email = "dalkjsdlf@naver.com";


        ResultActions resultActions = mockMvc.perform(get("/api/v1/users")
                        .header(WebApiConst.USER_ID_HEADER, userId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON));

        log.debug("#DEBUG result {}" + resultActions.andReturn().getResponse().getContentAsString());

        resultActions.andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value(name))
        .andExpect(jsonPath("$[0].email").value(email));
    }

    @Test
    @DisplayName("[성공] 사용자 정보 조회, ID로 단건조회")
    void given사용자ID_when사용자정보조회_then사용자정보단건() throws Exception {
        Long userId = 1L;
        String name = "yeonho choi";
        String email = "dalkjsdlf@naver.com";

        //when
        ResultActions resultActions = mockMvc.perform(get("/api/v1/users/1")
                        .header(WebApiConst.USER_ID_HEADER, userId)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON));

        log.debug("#DEBUG result {}" + resultActions.andReturn().getResponse().getContentAsString());

        //then
                resultActions.andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.email").value(email));
    }

}
