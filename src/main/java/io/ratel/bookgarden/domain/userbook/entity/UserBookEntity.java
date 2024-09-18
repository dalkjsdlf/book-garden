package io.ratel.bookgarden.domain.userbook.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "USER_BOOK", uniqueConstraints = @UniqueConstraint(columnNames = {"USER_ID", "BOOK_ID"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserBookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_ID", nullable = false)
    private Long userId;

    @Column(name = "BOOK_ID", nullable = false)
    private Long bookId;

    @Enumerated(EnumType.STRING)
    @Column(name = "READ_CMP_YN", nullable = false)
    private Yn readCmpYn;

    protected UserBookEntity(Long userId, Long bookId) {}

    private UserBookEntity(Long userId, Long bookId, Yn readCmpYn) {
        this.userId = userId;
        this.bookId = bookId;
        this.readCmpYn = readCmpYn;
    }

    public static UserBookEntity of(Long userId, Long bookId, Yn readCmpYn) {
         return new UserBookEntity(userId, bookId, readCmpYn);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBookEntity that = (UserBookEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}