package io.ratel.bookgarden.web_api.userbook.dto;

import io.ratel.bookgarden.domain.Journal.entity.JournalEntity;
import io.ratel.bookgarden.domain.bookinfo.entity.BookInfoEntity;
import io.ratel.bookgarden.domain.userbook.entity.UserBookEntity;
import io.ratel.bookgarden.domain.userbook.entity.Yn;
import lombok.*;

import java.util.List;

/**
 * The type User book detail get response dto.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserBookDetailGetResponseDto {

    /*
     * 도서 ID
     */
    private Long bookId;

    /*
     * 도서명
     */
    private String title;

    /*
     * 저자
     */
    private String author;

    /*
     * 설명
     */
    private String description;

    /*
     * 출판사
     */
    private String publisher;

    /*
     * 독서 완료 여부
     */
    private Yn readCmpYn;

    /*
     * 독서 기록 목록
     */
    private List<JournalEntity> journalEntities;

    /**
     * From entity user book detail get response dto.
     *
     * @param userBookEntity  the user book entity
     * @param bookInfoEntity  the book info entity
     * @param journalEntities the journal entities
     * @return the user book detail get response dto
     */
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
                readCmpYn(userBookEntity.getReadCmpYn()).
                journalEntities(journalEntities).
                build();
    }
}
