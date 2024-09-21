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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("사용자도서 Application 테스트")
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserBookApplicationTest {

    private Logger log = LoggerFactory.getLogger(UserBookApplicationTest.class);

    @Autowired
    private UserBookApplication userBookApplication;

    @DisplayName("[성공] 도서정보 조회")
    @Test
    public void given사용자_when사용자도서조회_then사용자도서(){
        //given
        Long userId = 1L;

        //when
        List<UserBookGetResponseDto> userBookGetResponseDtos = userBookApplication.getUserBooks(userId);
        log.debug("조회한 도서정보 타이틀 {}", userBookGetResponseDtos.get(0).getTitle());
        log.debug("조회한 도서정보 커버이미지 {}", userBookGetResponseDtos.get(0).getCover());
        log.debug("조회한 도서정보 완독여부 {}", userBookGetResponseDtos.get(0).getReadCmpYn());

        //then
        assertThat(userBookGetResponseDtos.size()).isGreaterThan(0);
        assertThat(userBookGetResponseDtos.get(0).getTitle()).isEqualTo("Spring Boot Guide");
    }

    @DisplayName("[성공] 도서정보 조회 상세")
    @Test
    public void given사용자도서ID_when사용자도서조회_then사용자도서(){
        //given
        Long userBookId = 1L;

        //when
        UserBookDetailGetResponseDto userBookDetailGetResponseDto = userBookApplication.getUserBookById(userBookId);
        log.debug("#DEBUG 조회한 도서정보 타이틀 {}", userBookDetailGetResponseDto.getTitle());
        log.debug("#DEBUG 조회한 도서정보 저자 {}", userBookDetailGetResponseDto.getAuthor());
        log.debug("#DEBUG 조회한 도서정보 설명 {}", userBookDetailGetResponseDto.getDescription());
        log.debug("#DEBUG 독서기록 조회 {}", userBookDetailGetResponseDto.getJournalEntities().get(0).getTitle());

        //then
        assertThat(userBookDetailGetResponseDto).isNotNull();
        assertThat(userBookDetailGetResponseDto.getTitle()).isEqualTo("Spring Boot Guide");
        assertThat(userBookDetailGetResponseDto.getJournalEntities().size()).isGreaterThan(0);
    }
}
