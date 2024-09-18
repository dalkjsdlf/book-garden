package io.ratel.bookgarden.web_api.userbook.application;

import io.ratel.bookgarden.domain.bookinfo.entity.BookInfoEntity;
import io.ratel.bookgarden.domain.bookinfo.service.BookInfoService;
import io.ratel.bookgarden.domain.userbook.entity.UserBookEntity;
import io.ratel.bookgarden.domain.userbook.service.UserBookService;
import io.ratel.bookgarden.web_api.userbook.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserBookApplication {

    private final UserBookService userBookService;
    private final BookInfoService bookInfoService;

    public List<UserBookGetResponseDto> getUserBooks(Long userId) {
        log.debug("userBookApplication으로 진입");

        List<BookInfoEntity> bookInfoEntities =  bookInfoService.getBooks();
        List<UserBookEntity> userBookEntities = userBookService.getUserBooks(userId);

        Map<Long, BookInfoEntity> bookInfoEntityMap = bookInfoEntities.stream()
                .collect(Collectors.toConcurrentMap(BookInfoEntity::getId, Function.identity()));


        List<UserBookGetResponseDto> userBookGetResponseDtos = new ArrayList<>();
        for(UserBookEntity userBookEntity : userBookEntities) {

            Long bookId = userBookEntity.getBookId();
            BookInfoEntity bookInfoEntity = bookInfoEntityMap.get(bookId);
            UserBookGetResponseDto userBookGetResponseDto = null;
            if(bookInfoEntity != null) {
                userBookGetResponseDto = UserBookGetResponseDto.fromEntity(userBookEntity, bookInfoEntity);
            }

            if(userBookGetResponseDto != null) {
                userBookGetResponseDtos.add(userBookGetResponseDto);
            }
        }

        return userBookGetResponseDtos;
    }

    public UserBookDetailGetResponseDto getUserBookById(Long id) {

        UserBookEntity userBookEntity = userBookService.getUserBookById(id);
        Long bookId = userBookEntity.getBookId();
        BookInfoEntity bookInfoEntity =  bookInfoService.getBookById(bookId);

        return UserBookDetailGetResponseDto.fromEntity(userBookEntity, bookInfoEntity);
    }

    public UserBookResponseDto createUserBook(UserBookCreateRequestDto userBookCreateRequestDto, Long userId) {
        return null;
    }

    public UserBookResponseDto updateUserBook(UserBookUpdateRequestDto userBookUpdateRequestDto, Long id, Long userId) {
        return null;
    }

    public UserBookResponseDto deleteUserBook(Long id, Long userId) {
        return null;
    }



}
