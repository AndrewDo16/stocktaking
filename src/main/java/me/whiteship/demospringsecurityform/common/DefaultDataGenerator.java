package me.whiteship.demospringsecurityform.common;

import me.whiteship.demospringsecurityform.account.Account;
import me.whiteship.demospringsecurityform.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DefaultDataGenerator implements ApplicationRunner {

    private final AccountService accountService;

    @Autowired
    public DefaultDataGenerator(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Account keesun = createUser("keesun");
        Account whiteship = createUser("whiteship");
    }

    private Account createUser(String usename) {
        Account account = new Account();
        account.setUsername(usename);
        account.setPassword("123");
        account.setRole("USER");
        return accountService.createNew(account);
    }
}
