package ru.stocktaking.stocktaking.form;

import org.springframework.beans.factory.annotation.Autowired;
import ru.stocktaking.stocktaking.account.Account;
import ru.stocktaking.stocktaking.account.AccountService;
import ru.stocktaking.stocktaking.common.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class SampleController {

    private final AccountService accountService;

    @Autowired
    public SampleController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/")
    public String index(Model model, @CurrentUser Account account) {
        if (account == null) {
            model.addAttribute("message", "Добро пожаловать на сервис инвентарного учета.");
        } else {
            model.addAttribute("message", "Добро пожаловать на сервис инвентарного учета, " + account.getUsername());
        }

        return "index";
    }

    @GetMapping("/info")
    public String info(Model model) {
        model.addAttribute("message", "Info");
        return "info";
    }


    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        model.addAttribute("message", "Hello Admin, " + principal.getName());
        return "admin/admin";
    }

    @GetMapping("/admin/user")
    public String user(Model model) {

        List<Account> accounts = accountService.findAll();
        model.addAttribute("account", new Account());
        model.addAttribute("accounts", accounts);

        return "admin/user";
    }

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        Account account = accountService.findByUsername(principal.getName());

        model.addAttribute("account", account);
        model.addAttribute("principal", principal);

        return "profile/profile";
    }

}
