package me.whiteship.demospringsecurityform.form;

import me.whiteship.demospringsecurityform.account.Account;
import me.whiteship.demospringsecurityform.account.AccountRepository;
import me.whiteship.demospringsecurityform.book.BookRepository;
import me.whiteship.demospringsecurityform.common.CurrentUser;
import me.whiteship.demospringsecurityform.common.SecurityLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.concurrent.Callable;

@Controller
public class SampleController {

    private final SampleService sampleService;

    private final AccountRepository accountRepository;
    private final BookRepository bookRepository;

    @Autowired
    public SampleController(SampleService sampleService, AccountRepository accountRepository, BookRepository bookRepository) {
        this.sampleService = sampleService;
        this.accountRepository = accountRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/")
    public String index(Model model, @CurrentUser Account account) {
        if (account == null) {
            model.addAttribute("message", "Hello Spring Security");
        } else {
            model.addAttribute("message", "Hello, " + account.getUsername());
        }

        return "index";
    }

    @GetMapping("/info")
    public String info(Model model) {
        model.addAttribute("message", "Info");
        return "info";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal) {
        model.addAttribute("message", "Hello " + principal.getName());
        sampleService.dashboard();
        return "dashboard";
    }

    @GetMapping("/admin")
    public String admin(Model model, Principal principal) {
        model.addAttribute("message", "Hello Admin, " + principal.getName());
        return "admin";
    }

    @GetMapping("/user")
    public String user(Model model, Principal principal) {
        model.addAttribute("message", "Hello User, " + principal.getName());
        model.addAttribute("books", bookRepository.findCurrentUserBooks());
        return "user";
    }

}
