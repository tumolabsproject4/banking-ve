package edu.tumo.banking.service.user;

import edu.tumo.banking.domain.user.UserModel;
import edu.tumo.banking.repository.user.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@Transactional
public class BankingUserDetailsService implements UserDetailsService {

    private final Logger logger = LogManager.getLogger(BankingUserDetailsService.class);

    private final UserRepository userRepository;

    public BankingUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<UserModel> optionalUserModel = userRepository.findByUserName(username);
        if (optionalUserModel.isEmpty()) {
            logger.error("User was not found with username - {}", username);
            throw new UsernameNotFoundException("User was not found");
        }
        final UserModel userModel = optionalUserModel.get();
        return new User(userModel.getUsername(), userModel.getPassword(), Collections.emptyList());
    }

}
