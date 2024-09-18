package io.ratel.bookgarden.web_api.userbook.dto;

import io.ratel.bookgarden.domain.userbook.entity.UserBookEntity;
import io.ratel.bookgarden.domain.userbook.entity.Yn;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserBookResponseDto {

    private Long userId;

    private Long bookId;

    private Yn readCmpYn;

    public static UserBookResponseDto fromEntity(UserBookEntity userBookEntity) {
        return UserBookResponseDto.builder().
                bookId(userBookEntity.getBookId()).
                userId(userBookEntity.getUserId()).
                readCmpYn(userBookEntity.getReadCmpYn()).
                build();
    }
}
