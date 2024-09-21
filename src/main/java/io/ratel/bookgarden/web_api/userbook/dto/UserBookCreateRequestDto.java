package io.ratel.bookgarden.web_api.userbook.dto;


import io.ratel.bookgarden.domain.userbook.entity.UserBookEntity;
import io.ratel.bookgarden.domain.userbook.entity.Yn;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

/**
 * The type User book create request dto.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserBookCreateRequestDto {

    @NotNull
    @Positive
    private Long bookId;

    @NotNull
    @Positive
    private Long userId;

    @NotNull
    private Yn readCmpYn;

    /**
     * From dto user book entity.
     *
     * @param userBookCreateRequestDto the user book create request dto
     * @return the user book entity
     */
    public static UserBookEntity fromDto(UserBookCreateRequestDto userBookCreateRequestDto) {
        return UserBookEntity.builder().
                bookId(userBookCreateRequestDto.getBookId()).
                userId(userBookCreateRequestDto.getUserId()).
                readCmpYn(userBookCreateRequestDto.getReadCmpYn()).
                build();
    }

    /**
     * To entity user book entity.
     *
     * @param userId the user id
     * @return the user book entity
     */
    public UserBookEntity toEntity(Long userId) {
        return UserBookEntity.builder()
                .bookId(this.bookId)
                .readCmpYn(this.readCmpYn)
                .userId(userId)
                .build();
    }
}
