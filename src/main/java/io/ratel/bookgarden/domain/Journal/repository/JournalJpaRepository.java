package io.ratel.bookgarden.domain.Journal.repository;

import io.ratel.bookgarden.domain.Journal.entity.JournalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalJpaRepository extends JpaRepository<JournalEntity, Long> {
}
