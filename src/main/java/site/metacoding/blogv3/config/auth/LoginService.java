package site.metacoding.blogv3.config.auth;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.blogv3.domain.user.User;
import site.metacoding.blogv3.domain.user.UserRepository;

@RequiredArgsConstructor
@Service // IoC 컨테이너 등록됨.
public class LoginService implements UserDetailsService { // 타입 찾기 위해 implements 필요함

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // System.out.println("username : " + username);
        System.out.println(1);
        Optional<User> userOp = userRepository.findByUsername(username);
        System.out.println(2);
        if (userOp.isPresent()) {
            return new LoginUser(userOp.get());
        }
        System.out.println(3);
        return null; // 내부적으로 터져서 throw를 타지 않기 때문에 그냥 null return
    }
}
