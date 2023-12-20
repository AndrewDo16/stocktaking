package ru.stocktaking.stocktaking.common;

import ru.stocktaking.stocktaking.account.Account;
import ru.stocktaking.stocktaking.account.AccountService;
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
        Account admin = createAdmin("admin");
    }

    private Account createUser(String usename) {
        Account account = new Account();
        account.setUsername(usename);
        account.setPassword("123");
        account.setRole("USER");
        return accountService.createNew(account);
    }
    private Account createAdmin(String usename) {
        Account account = new Account();
        account.setUsername(usename);
        account.setPassword("admin");
        account.setRole("ADMIN");
        return accountService.createNew(account);
    }
}
