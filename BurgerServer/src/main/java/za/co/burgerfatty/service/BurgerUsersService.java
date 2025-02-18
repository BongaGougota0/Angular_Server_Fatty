package za.co.burgerfatty.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import za.co.burgerfatty.exception.UserNotFound;
import za.co.burgerfatty.models.BurgerUser;
import za.co.burgerfatty.repositories.BurgerUserRepo;
import java.util.Optional;

@Service
public class BurgerUsersService {
    private final BurgerUserRepo burgerUserRepo;
    private final PasswordEncoder passwordEncoder;

    public BurgerUsersService(BurgerUserRepo burgerUserRepo, PasswordEncoder passwordEncoder) {
        this.burgerUserRepo = burgerUserRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean createNewUser(BurgerUser newUser) {
        if (newUser == null || newUser.getEmail() == null) {
            return false;
        }
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        if (burgerUserRepo.findUserByEmail(newUser.getEmail())
                .isPresent()) return false;
        burgerUserRepo.save(newUser);
        return true;
    }

    public BurgerUser getUserByEmail(String userEmail) {
        return burgerUserRepo.findUserByEmail(userEmail)
                .orElseThrow(() -> new UserNotFound(
                        String.format("User with email %s not found", userEmail)
                ));
    }

    public BurgerUser getUserById(int id) {
        Optional<BurgerUser> existingUser =  burgerUserRepo.findById(id);
        if (existingUser.isPresent()) {
            return existingUser.get();
        }
        throw new UserNotFound(String.format("User with id %d not found", id));
    }
}
