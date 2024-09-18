package io.ratel.bookgarden.domain.Journal.repository;

import io.ratel.bookgarden.domain.Journal.entity.JournalEntity;

import java.util.List;
import java.util.Optional;

public interface JournalRepository {

    public List<JournalEntity> selectAll();

    public Optional<JournalEntity> selectById(Long id);

    List<JournalEntity> selectByUserBookId(Long userBookId);

    JournalEntity save(JournalEntity journalEntity);

    Long deleteById(Long journalId);
}
