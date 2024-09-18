package io.ratel.bookgarden.domain.userInfo.entity;

import io.ratel.bookgarden.common.persistence.entity.AuditableFields;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "USER_INFO")
@Getter
public class UserInfoEntity extends AuditableFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Setter
    @Column(nullable = false, length = 100)
    private String name;

    @Setter
    @Column(nullable = false, length = 150)
    private String email;

    @Setter
    @Column(nullable = false, length = 200)
    private String password;

    protected UserInfoEntity() {}

    private UserInfoEntity(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static UserInfoEntity of(String name, String email, String password) {
        return new UserInfoEntity(name, email, password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfoEntity that = (UserInfoEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
