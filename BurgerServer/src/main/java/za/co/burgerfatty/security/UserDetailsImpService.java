package za.co.burgerfatty.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import za.co.burgerfatty.models.BurgerUser;
import za.co.burgerfatty.service.BurgerUsersService;

@Service
public class UserDetailsImpService implements UserDetailsService {
    private final BurgerUsersService burgerUsersService;

    public UserDetailsImpService(BurgerUsersService burgerUsersService) {
        this.burgerUsersService = burgerUsersService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BurgerUser user = burgerUsersService.getUserByEmail(username);
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
