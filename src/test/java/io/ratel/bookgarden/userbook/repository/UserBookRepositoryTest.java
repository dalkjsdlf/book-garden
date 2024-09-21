package io.ratel.bookgarden.userbook.repository;

import io.ratel.bookgarden.domain.userbook.entity.UserBookEntity;
import io.ratel.bookgarden.domain.userbook.repository.UserBookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("사용자별도서 Repository 조회 테스트")
@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserBookRepositoryTest {

    @Autowired
    private UserBookRepository userBookRepository;

    @DisplayName("[성공] 사용자도서 정보 조회 다건조회")
    @Test
    public void given사용자ID_when해당사용자도서정보조회_then도서정보다건목록(){
        //given
        Long userId = 1L;

        //when
        List<UserBookEntity> userBookEntities = userBookRepository.selectByUserId(userId);

        //then
        assertThat(userBookEntities).isNotEmpty();
        assertThat(userBookEntities.size()).isGreaterThan(0);
    }

    @DisplayName("[성공] 사용자도서 정보 조회 단건조회")
    @Test
    public void given사용자도서ID_when해당사용자도서정보조회_then도서정보다건목록(){
        //given
        Long userBookId = 1L;

        //when
        Optional<UserBookEntity> optUserBookEntity = userBookRepository.selectById(userBookId);

        //then
        assertThat(optUserBookEntity).isNotNull();
    }

}
