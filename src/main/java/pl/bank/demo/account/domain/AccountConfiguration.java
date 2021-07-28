package pl.bank.demo.account.domain;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class AccountConfiguration {

    @Bean
    public AccountFacade createAccountFacade(AccountRepository accountRepository) {
        return AccountFacade.builder()
                .accountRepository(accountRepository)
                .build();
    }
}