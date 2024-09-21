package io.ratel.bookgarden.web_api.userbook.dto;

import io.ratel.bookgarden.domain.userbook.entity.UserBookEntity;
import io.ratel.bookgarden.domain.userbook.entity.Yn;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserBookModifyRequestDto {

    @NotNull
    @Positive
    private Long id;

    @NotNull
    @Positive
    private Long userId;

    @NotNull
    @Positive
    private Long bookId;

    @NotNull
    private Yn readCmpYn;

    public static UserBookEntity fromDto(UserBookModifyRequestDto userBookModifyRequestDto) {

        UserBookEntity userBookEntity = UserBookEntity.builder().
                userId(userBookModifyRequestDto.getUserId()).
                bookId(userBookModifyRequestDto.getBookId()).
                readCmpYn(userBookModifyRequestDto.getReadCmpYn()).
                build();
        userBookEntity.setId(userBookModifyRequestDto.getId());

        return userBookEntity;
    }
}
