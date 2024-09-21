package io.ratel.bookgarden.domain.phrase.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.ratel.bookgarden.domain.phrase.entity.PhraseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static io.ratel.bookgarden.domain.phrase.entity.QPhraseEntity.phraseEntity;

@Repository
@RequiredArgsConstructor
public class PhraseRepositoryImpl implements PhraseRepository {

    private final JPAQueryFactory queryFactory;
    private final PhraseJpaRepository phraseJpaRepository;

    @Override
    public List<PhraseEntity> selectAll() {
        return queryFactory.
                selectFrom(phraseEntity).fetch();
    }

    @Override
    public Optional<PhraseEntity> selectById(Long id) {
        return Optional.ofNullable(
          queryFactory.
          selectFrom(phraseEntity).
                  where(phraseEntity.id.eq(id)).fetchOne()
        );
    }

    @Override
    public List<PhraseEntity> selectByUserBookId(Long userBookId) {
        return queryFactory.
                selectFrom(phraseEntity).
                where(phraseEntity.userBookId.eq(userBookId)).fetch();
    }

    @Override
    public PhraseEntity save(PhraseEntity iJournalEntity) {
        return phraseJpaRepository.save(iJournalEntity);
    }

    @Override
    public Long deleteById(Long journalId) {
        return queryFactory.
                delete(phraseEntity).
                where(phraseEntity.id.eq(journalId)).
                execute();
    }
}
