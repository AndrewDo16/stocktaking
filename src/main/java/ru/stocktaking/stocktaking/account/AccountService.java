package ru.stocktaking.stocktaking.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException(username);
        }

        return new UserAccount(account);
    }

    public Account createNew(Account account) {
        account.encodePassword(passwordEncoder);
        return this.accountRepository.save(account);
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Optional<Account> findOne(int userId) {
        return accountRepository.findById(userId);
    }

    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    public void updateAccount(Account account) {
        // Найти существующего пользователя по его id
        Optional<Account> existingAccountOptional = accountRepository.findById(account.getId());

        if (existingAccountOptional.isPresent()) {
            // Обновить существующего пользователя с новыми данными
            Account existingAccount = existingAccountOptional.get();
            existingAccount.setUsername(account.getUsername());
            existingAccount.setPassword(account.getPassword());
            existingAccount.setRole(account.getRole());
            existingAccount.encodePassword(passwordEncoder);
            // Сохранить обновленного пользователя
            accountRepository.save(existingAccount);
        } else {
            // Обработка ситуации, если пользователь не найден
            throw new RuntimeException("Пользователь с id " + account.getId() + " не найден");
        }
    }

    public void editPassword(Account account) {
        Account user = findByUsername(account.getUsername());
        user.setPassword(account.getPassword());
        user.encodePassword(passwordEncoder);
        accountRepository.save(user);
    }

    public void delete(int userId) {
        Optional<Account> account = accountRepository.findById(userId);
        accountRepository.delete(account.get());
    }
}
