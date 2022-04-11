package site.metacoding.blogv3.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv3.domain.user.User;
import site.metacoding.blogv3.domain.user.UserRepository;

@RequiredArgsConstructor
@Service // IoC 등록
public class UserService {

    // DI
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void 회원가입(User user) { // 서비스는 dto보다 깔끔하게 오브젝트로 받는게 좋다. (웬만하면!!)
        String rawPassword = user.getPassword(); // 1234
        String encPassword = bCryptPasswordEncoder.encode(rawPassword); // 해쉬 알고리즘 (내장 라이브러리 사용)
        user.setPassword(encPassword);

        userRepository.save(user);
    }
}
