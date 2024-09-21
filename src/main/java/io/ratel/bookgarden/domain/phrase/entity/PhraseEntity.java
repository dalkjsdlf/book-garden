package io.ratel.bookgarden.domain.phrase.entity;

import io.ratel.bookgarden.common.persistence.entity.AuditableFields;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "PHRASE")
@Getter
public class PhraseEntity extends AuditableFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Setter
    private Long id;

    @Setter
    @Column(name = "USER_BOOK_ID", nullable = false)
    private Long userBookId;

    @Setter
    @Column(name = "CONTENT", nullable = true, length = 4000)
    private String content;

    protected PhraseEntity() {}

    private PhraseEntity(Long userBookId, String content) {
        this.userBookId = userBookId;
        this.content = content;
    }

    public static PhraseEntity of(Long userBookId, String content) {
        return new PhraseEntity(userBookId, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhraseEntity that = (PhraseEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
