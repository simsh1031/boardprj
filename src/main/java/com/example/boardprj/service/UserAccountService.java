package com.example.boardprj.service;

import com.example.boardprj.model.entity.UserAccount;
import com.example.boardprj.model.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // Component
@RequiredArgsConstructor // final 처리 되어있는 필드들에 대한 생성자
// -> 생성자 주입
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserAccount register(String username, String password) {
        if (userAccountRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 사용자");
        } // Controller -> 처리

        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(username);
        userAccount.setPassword(passwordEncoder.encode(password));
        userAccount.setRole("ROLE_USER");
        return userAccountRepository.save(userAccount);
    }
}
