package io.ratel.bookgarden.web_api.userbook.dto;

import io.ratel.bookgarden.domain.userbook.entity.UserBookEntity;
import io.ratel.bookgarden.domain.userbook.entity.Yn;
import lombok.*;

/**
 * The type User book response dto.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserBookResponseDto {

    private Long userId;

    private Long bookId;

    private Yn readCmpYn;

    /**
     * From entity user book response dto.
     *
     * @param userBookEntity the user book entity
     * @return the user book response dto
     */
    public static UserBookResponseDto fromEntity(UserBookEntity userBookEntity) {
        return UserBookResponseDto.builder().
                bookId(userBookEntity.getBookId()).
                userId(userBookEntity.getUserId()).
                readCmpYn(userBookEntity.getReadCmpYn()).
                build();
    }
}
