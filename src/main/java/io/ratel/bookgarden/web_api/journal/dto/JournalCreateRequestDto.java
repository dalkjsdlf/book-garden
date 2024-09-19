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
public class JournalCreateRequestDto {
    private String title;
    private String content;
    private Long userBookId;

    public static JournalEntity fromDto(JournalCreateRequestDto journalCreateRequestDto) {
        return JournalEntity.
                of(journalCreateRequestDto.userBookId,
                        journalCreateRequestDto.title,
                        journalCreateRequestDto.content);
    }
}
