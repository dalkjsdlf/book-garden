package io.ratel.bookgarden.web_api.userbook.dto;

import io.ratel.bookgarden.domain.bookinfo.entity.BookInfoEntity;
import io.ratel.bookgarden.domain.userbook.entity.UserBookEntity;
import io.ratel.bookgarden.domain.userbook.entity.Yn;
import lombok.*;

/**
 * The type User book get response dto.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserBookGetResponseDto {
    private Long id;
    private String title;
    private String cover;
    private String author;
    private String publisher;
    private Yn readCmpYn;

    /**
     * From entity user book get response dto.
     *
     * @param userBookEntity the user book entity
     * @param bookInfoEntity the book info entity
     * @return the user book get response dto
     */
    public static UserBookGetResponseDto fromEntity(UserBookEntity userBookEntity,
                                                    BookInfoEntity bookInfoEntity) {
        return UserBookGetResponseDto.builder()
                .id(userBookEntity.getId())
                .title(bookInfoEntity.getTitle()) // 실제 로직에 따라 title을 설정
                .cover(bookInfoEntity.getCover()) // 실제 로직에 따라 cover를 설정
                .publisher(bookInfoEntity.getPublisher()) // 실제 로직에 따라 publisher를 설정
                .author(bookInfoEntity.getAuthor()) // 실제 로직에 따라 author를 설정
                .readCmpYn(userBookEntity.getReadCmpYn())
                .build();

    }
}