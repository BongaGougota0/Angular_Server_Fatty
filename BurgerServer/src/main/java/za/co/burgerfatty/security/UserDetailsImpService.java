package za.co.burgerfatty.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import za.co.burgerfatty.models.BurgerUser;
import za.co.burgerfatty.repositories.BurgerUserRepo;

@Service
public class UserDetailsImpService implements UserDetailsService {
    private final BurgerUserRepo burgerUserRepo;

    public UserDetailsImpService(BurgerUserRepo burgerUserRepo) {
        this.burgerUserRepo = burgerUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BurgerUser user = burgerUserRepo.findUserByEmail(username).get();
        if(user != null) {
            return User.builder()
                    .username(username)
                    .password(user.getPassword())
                    .roles(getRoles(user))
                    .build();
        }else {
            throw new UsernameNotFoundException(username);
        }
    }

    private String[] getRoles(BurgerUser burgerUser) {
        if(burgerUser.getRole() == null){
            return new String[]{"USER"};
        }
        return new String[]{burgerUser.getRole()};
    }
}
