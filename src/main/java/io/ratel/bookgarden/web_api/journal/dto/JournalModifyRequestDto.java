package io.ratel.bookgarden.web_api.journal.dto;

import io.ratel.bookgarden.domain.Journal.entity.JournalEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class JournalModifyRequestDto {
    private Long id;
    private String title;
    private String content;
    private Long userBookId;

    public static JournalEntity fromDto(JournalModifyRequestDto journalCreateRequestDto) {
        JournalEntity journalEntity = JournalEntity.
                of(journalCreateRequestDto.userBookId,
                        journalCreateRequestDto.title,
                        journalCreateRequestDto.content);

        journalEntity.setId(journalCreateRequestDto.getId());

        return journalEntity;
    }
}
