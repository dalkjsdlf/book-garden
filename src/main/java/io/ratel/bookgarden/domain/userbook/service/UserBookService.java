package io.ratel.bookgarden.domain.userbook.service;


import io.ratel.bookgarden.common.exception.BusinessErrorResult;
import io.ratel.bookgarden.common.exception.BusinessException;
import io.ratel.bookgarden.domain.userbook.entity.UserBookEntity;
import io.ratel.bookgarden.domain.userbook.repository.UserBookRepository;
import io.ratel.bookgarden.web_api.userbook.dto.UserBookCreateRequestDto;
import io.ratel.bookgarden.web_api.userbook.dto.UserBookUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserBookService {

    private final UserBookRepository userBookRepository;


    public List<UserBookEntity> getUserBooks(Long userId) {
        return userBookRepository.selectByUserId(userId);
    }

    public UserBookEntity getUserBookById(Long id) {
        Optional<UserBookEntity> optionalUserBookEntity = userBookRepository.selectById(id);
        return optionalUserBookEntity.orElseThrow(() -> new BusinessException(BusinessErrorResult.USER_BOOK_ALREADY_EXISTS_ERROR));
    }

    public void createUserBook(UserBookCreateRequestDto requestDto, Long userId) {
        UserBookEntity userBookEntity = requestDto.toEntity(userId);  // 실제 userId 로직으로 대체 필요
        userBookRepository.insert(userBookEntity);
    }

    public void updateUserBook(Long userId, Long id, UserBookUpdateRequestDto requestDto) {

        Optional<UserBookEntity> optUserBookEntity = userBookRepository.selectById(id);

        if (optUserBookEntity.isPresent()) {
            throw new BusinessException(BusinessErrorResult.USER_BOOK_ALREADY_EXISTS_ERROR);
        }

        UserBookEntity userBookEntity = UserBookEntity.of(userId, id, requestDto.getReadCmpYn());
        userBookRepository.update(userBookEntity);
    }
    public void deleteUserBook(Long id) {
        userBookRepository.deleteById(id);
    }
}