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


    @ResponseBody
    @GetMapping("account/{user_id}")
    public Account getAccount(@PathVariable("user_id") int userId) {
        return accountService.findOne(userId).get();
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

    @PostMapping("/account/delete")
    public String deleteAccount(@ModelAttribute("account") Account account) {

        accountService.delete(account.getId());
        return "redirect:/admin/user";
    }

    @PostMapping("/account/update")
    public String updateAccount(@ModelAttribute("account") Account account) {
        accountService.updateAccount(account);
        return "redirect:/admin/user";
    }

    @PostMapping("account/edit_password")
    public String editPassword(@ModelAttribute("account") Account account) {
        if (account.getPassword().equals(account.getConfirmPassword())) {
            accountService.editPassword(account);
            return "profile/password_save";
        }
        else {
            return "/profile/password_error";
        }

    }

}
