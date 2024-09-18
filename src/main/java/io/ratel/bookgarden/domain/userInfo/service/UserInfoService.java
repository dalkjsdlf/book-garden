package io.ratel.bookgarden.domain.userInfo.service;

import io.ratel.bookgarden.domain.userInfo.entity.UserInfoEntity;
import io.ratel.bookgarden.domain.userInfo.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;

    public List<UserInfoEntity>  getUsers(){
        return userInfoRepository.findAll();
    }

    public UserInfoEntity getUserById(Long id){
        return userInfoRepository.findById(id).orElse(null);
    }
}
