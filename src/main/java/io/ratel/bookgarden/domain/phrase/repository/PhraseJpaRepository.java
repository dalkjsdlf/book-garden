package io.ratel.bookgarden.domain.phrase.repository;

import io.ratel.bookgarden.domain.phrase.entity.PhraseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhraseJpaRepository extends JpaRepository<PhraseEntity, Long> {
}
