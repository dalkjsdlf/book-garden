package io.ratel.bookgarden.domain.phrase.repository;

import io.ratel.bookgarden.domain.phrase.entity.PhraseEntity;

import java.util.List;
import java.util.Optional;

public interface PhraseRepository {

    public List<PhraseEntity> selectAll();

    public Optional<PhraseEntity> selectById(Long id);

    List<PhraseEntity> selectByUserBookId(Long userBookId);

    PhraseEntity save(PhraseEntity phraseEntity);

    Long deleteById(Long phraseId);
}
