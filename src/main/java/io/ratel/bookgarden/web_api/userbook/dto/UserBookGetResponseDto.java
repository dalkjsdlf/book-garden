package io.ratel.bookgarden.web_api.userbook.dto;

import io.ratel.bookgarden.domain.bookinfo.entity.BookInfoEntity;
import io.ratel.bookgarden.domain.userbook.entity.UserBookEntity;
import io.ratel.bookgarden.domain.userbook.entity.Yn;
import io.ratel.bookgarden.web_api.journal.dto.JournalGetResponseDto;
import lombok.*;

import java.util.List;

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

    public static UserBookGetResponseDto fromEntity(UserBookEntity userBookEntity,
                                                    BookInfoEntity bookInfoEntity) {
        return UserBookGetResponseDto.builder()
                .id(userBookEntity.getId())
                .title(bookInfoEntity.getTitle()) // 실제 로직에 따라 title을 설정
                .cover(bookInfoEntity.getCover()) // 실제 로직에 따라 cover를 설정
                .readCmpYn(userBookEntity.getReadCmpYn())
                .build();
    }
}