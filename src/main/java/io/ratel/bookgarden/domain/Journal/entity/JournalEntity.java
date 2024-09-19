package io.ratel.bookgarden.domain.Journal.entity;

import io.ratel.bookgarden.common.persistence.entity.AuditableFields;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "JOURNAL")
@Getter
public class JournalEntity extends AuditableFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Setter
    private Long id;

    @Setter
    @Column(name = "USER_BOOK_ID", nullable = false)
    private Long userBookId;

    @Setter
    @Column(name = "TITLE", nullable = false, length = 200)
    private String title;

    @Setter
    @Column(name = "CONTENT", nullable = true, length = 4000)
    private String content;

    protected JournalEntity() {}

    private JournalEntity(Long userBookId, String title, String content) {
        this.userBookId = userBookId;
        this.title = title;
        this.content = content;
    }

    public static JournalEntity of(Long userBookId, String title, String content) {
        return new JournalEntity(userBookId, title, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JournalEntity that = (JournalEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
