package io.ratel.bookgarden.userbook.application;

import io.ratel.bookgarden.web_api.userbook.application.UserBookApplication;
import io.ratel.bookgarden.web_api.userbook.dto.UserBookDetailGetResponseDto;
import io.ratel.bookgarden.web_api.userbook.dto.UserBookGetResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("사용자도서 응용계층 테스트")
@SpringBootTest
@ActiveProfiles("test")
public class UserBookApplicationTest {

    private Logger LOGGER = LoggerFactory.getLogger(UserBookApplicationTest.class);

    @Autowired
    private UserBookApplication userBookApplication;

    @DisplayName("[성공] 도서정보 조회")
    @Test
    public void 사용자_사용자도서조회_사용자도서(){
        //given
        Long userId = 1L;

        //when
        List<UserBookGetResponseDto> userBookGetResponseDtos = userBookApplication.getUserBooks(userId);
        LOGGER.debug("조회한 도서정보 타이틀 {}", userBookGetResponseDtos.get(0).getTitle());
        LOGGER.debug("조회한 도서정보 커버이미지 {}", userBookGetResponseDtos.get(0).getCover());
        LOGGER.debug("조회한 도서정보 완독여부 {}", userBookGetResponseDtos.get(0).getReadCmpYn());

        //then
        assertThat(userBookGetResponseDtos.size()).isGreaterThan(0);
        assertThat(userBookGetResponseDtos.get(0).getTitle()).isEqualTo("Spring Boot Guide");
    }

    @DisplayName("[성공] 도서정보 조회 상세")
    @Test
    public void 사용자도서ID_사용자도서조회_사용자도서(){
        //given
        Long userBookId = 1L;

        //when
        UserBookDetailGetResponseDto userBookDetailGetResponseDto = userBookApplication.getUserBookById(userBookId);
        LOGGER.debug("조회한 도서정보 타이틀 {}", userBookDetailGetResponseDto.getTitle());
        LOGGER.debug("조회한 도서정보 저자 {}", userBookDetailGetResponseDto.getAuthor());
        LOGGER.debug("조회한 도서정보 설명 {}", userBookDetailGetResponseDto.getDescription());

        //then
        assertThat(userBookDetailGetResponseDto).isNotNull();
        assertThat(userBookDetailGetResponseDto.getTitle()).isEqualTo("Spring Boot Guide");
    }
}
