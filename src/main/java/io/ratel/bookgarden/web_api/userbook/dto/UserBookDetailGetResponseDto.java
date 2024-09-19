package io.ratel.bookgarden.web_api.userbook.dto;

import io.ratel.bookgarden.domain.Journal.entity.JournalEntity;
import io.ratel.bookgarden.domain.bookinfo.entity.BookInfoEntity;
import io.ratel.bookgarden.domain.userbook.entity.UserBookEntity;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserBookDetailGetResponseDto {

    // 사용자책 ID
    // 책 제목
    // 책 설명
    // 저자
    // 출판사
    // 글귀 ID
    // 글귀내용
    // 기록 ID
    // 기록내용
    // 리뷰 ID
    // 리뷰내용

    private Long bookId;

    private String title;

    private String author;

    private String description;

    private String publisher;

    private Long phraseId;

    private String phrase;

    private List<JournalEntity> journalEntities;

    private Long reviewId;

    private String review;

    public static UserBookDetailGetResponseDto fromEntity(UserBookEntity userBookEntity,
                                                          BookInfoEntity bookInfoEntity,
                                                          List<JournalEntity> journalEntities
                                                          ) {
        return UserBookDetailGetResponseDto.builder().
                bookId(userBookEntity.getBookId()).
                title(bookInfoEntity.getTitle()).
                author(bookInfoEntity.getAuthor()).
                description(bookInfoEntity.getDescription()).
                publisher(bookInfoEntity.getPublisher()).
                journalEntities(journalEntities).
                build();
    }
}
