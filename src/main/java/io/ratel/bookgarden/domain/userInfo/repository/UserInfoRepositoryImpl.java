package io.ratel.bookgarden.domain.userInfo.repository;


import com.querydsl.jpa.impl.JPAQueryFactory;
import io.ratel.bookgarden.domain.userInfo.entity.UserInfoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static io.ratel.bookgarden.domain.userInfo.entity.QUserInfoEntity.userInfoEntity;

@Repository
@RequiredArgsConstructor
public class UserInfoRepositoryImpl implements UserInfoRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<UserInfoEntity> selectAll() {
        return queryFactory.
                selectFrom(userInfoEntity).
                fetch();
    }

    @Override
    public Optional<UserInfoEntity> selectById(Long id) {
        return Optional.ofNullable(
                queryFactory.
                        selectFrom(userInfoEntity).
                        where(userInfoEntity.id.eq(id)).
                        fetchOne());
    }
}
