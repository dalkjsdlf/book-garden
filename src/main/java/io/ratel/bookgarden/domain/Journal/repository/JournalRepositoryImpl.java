package io.ratel.bookgarden.domain.Journal.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import io.ratel.bookgarden.domain.Journal.entity.JournalEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static io.ratel.bookgarden.domain.Journal.entity.QJournalEntity.journalEntity;


@Repository
@RequiredArgsConstructor
public class JournalRepositoryImpl implements JournalRepository {

    private final JPAQueryFactory queryFactory;
    private final JournalJpaRepository journalJpaRepository;

    @Override
    public List<JournalEntity> selectAll() {
        return queryFactory.
                selectFrom(journalEntity).fetch();
    }

    @Override
    public Optional<JournalEntity> selectById(Long id) {
        return Optional.ofNullable(
          queryFactory.
          selectFrom(journalEntity).
                  where(journalEntity.id.eq(id)).fetchOne()
        );
    }

    @Override
    public List<JournalEntity> selectByUserBookId(Long userBookId) {
        return queryFactory.
                selectFrom(journalEntity).
                where(journalEntity.userBookId.eq(userBookId)).fetch();
    }

    @Override
    public JournalEntity save(JournalEntity iJournalEntity) {
        return journalJpaRepository.save(iJournalEntity);
    }

    @Override
    public Long deleteById(Long journalId) {
        return queryFactory.
                delete(journalEntity).
                where(journalEntity.id.eq(journalId)).
                execute();
    }
}
