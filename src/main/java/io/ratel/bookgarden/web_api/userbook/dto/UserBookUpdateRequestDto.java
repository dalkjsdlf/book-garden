package io.ratel.bookgarden.web_api.userbook.dto;

import io.ratel.bookgarden.domain.userbook.entity.UserBookEntity;
import io.ratel.bookgarden.domain.userbook.entity.Yn;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class UserBookUpdateRequestDto {

    @NotNull
    private Yn readCmpYn;

    public void updateEntity(UserBookEntity entity) {
        entity.setReadCmpYn(this.readCmpYn);
    }

    public UserBookUpdateRequestDto(Yn readCmpYn) {}
}
