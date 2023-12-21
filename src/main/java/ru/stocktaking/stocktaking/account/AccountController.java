package ru.stocktaking.stocktaking.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @ResponseBody
    @GetMapping("/account/{role}/{username}/{password}")
    public Account createAccount(@ModelAttribute Account account) {
        return accountService.createNew(account);
    }

    @PostMapping("account")
    public String create(@ModelAttribute("account") Account account) {

        accountService.createNew(account);

        return "redirect:/admin/user";
    }

    @PostMapping("/account/{user_id}")
    public String delete(@PathVariable("user_id") int userId) {
        accountService.delete(userId);
        return "redirect:/admin/user";
    }

}
