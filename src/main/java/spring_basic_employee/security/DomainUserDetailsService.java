package spring_basic_employee.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;



/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class DomainUserDetailsService implements ReactiveUserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);


    @Override
    @Transactional
    public Mono<UserDetails> findByUsername(final String login) {
        return null;
    }
}
