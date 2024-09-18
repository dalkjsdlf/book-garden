package io.ratel.bookgarden.bookInfo.service;

import io.ratel.bookgarden.common.persistence.config.datasource.BookGardenDbConfig;
import io.ratel.bookgarden.domain.bookinfo.entity.BookInfoEntity;
import io.ratel.bookgarden.domain.bookinfo.service.BookInfoService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("책 정보 조회 Service Test")
@SpringBootTest
@Import({BookGardenDbConfig.class})  // Configuration 클래스 가져오기
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
@Rollback
public class BookInfoServiceTest {

    private final BookInfoService bookInfoService;
    private Logger LOGGER = LoggerFactory.getLogger(BookInfoServiceTest.class);
    public BookInfoServiceTest(@Autowired BookInfoService bookInfoService) {
        this.bookInfoService = bookInfoService;
    }

    @DisplayName("[성공] 조건없이 모든 도서정보 조회")
    @Test
    public void 조건없이_모든책정보조회_책정보(){
        //given

        //when
        List<BookInfoEntity> books = bookInfoService.getBooks();

        int cnt = books.size();
        LOGGER.debug("도서정보 건수 조회 {}",cnt);
        LOGGER.debug("================================================");
        for(BookInfoEntity book : books) {
            LOGGER.debug("책제목 [{}] 저자 [{}] 출판사 [{}]",book.getTitle(), book.getAuthor(), book.getPublisher());
        }
        LOGGER.debug("================================================");
        //then
        assertThat(books.size()).isGreaterThan(0);
    }

    @DisplayName("[성공] 책 제목으로 도서정보 조회")
    @Test
    public void 도서제목_책정보조회_책정보(){
        //given
        String title = "Spring";
        //when
        List<BookInfoEntity> books = bookInfoService.getBooksByTitle(title);

        int cnt = books.size();
        LOGGER.debug("도서정보 건수 조회 {}",cnt);
        LOGGER.debug("================================================");

        for(BookInfoEntity book : books) {
            LOGGER.debug("책제목 [{}] 저자 [{}] 출판사 [{}]",book.getTitle(), book.getAuthor(), book.getPublisher());
        }
        LOGGER.debug("================================================");
        //then
        assertThat(books.size()).isGreaterThan(0);
    }
}
