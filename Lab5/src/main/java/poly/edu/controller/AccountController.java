package poly.edu.controller;

import poly.edu.service.CookieService;
import poly.edu.service.ParamService;
import poly.edu.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
public class AccountController {

    @Autowired CookieService cookieService;
    @Autowired ParamService paramService;
    @Autowired SessionService sessionService;

    @GetMapping("/account/login")
    public String loginForm(Model model) {
        model.addAttribute("rememberedUser", cookieService.getValue("user"));
        return "account/login";
    }

    @PostMapping("/account/login")
    public String loginSubmit(Model model) {
        String un = paramService.getString("username", "");
        String pw = paramService.getString("password", "");
        boolean rm = paramService.getBoolean("remember", false);

        if ("poly".equals(un) && "123".equals(pw)) {
            sessionService.set("username", un);
            if (rm) cookieService.add("user", un, 24 * 10); // 10 ngày
            else cookieService.remove("user");
            model.addAttribute("message", "Đăng nhập thành công!");
        } else {
            model.addAttribute("message", "Sai tài khoản hoặc mật khẩu!");
        }
        model.addAttribute("rememberedUser", cookieService.getValue("user"));
        return "account/login";
    }

    @GetMapping("/account/register")
    public String registerForm() { return "account/register"; }

    @PostMapping("/account/register")
    public String registerSubmit(@RequestParam("avatar") MultipartFile avatar, Model model) {
        File saved = paramService.save(avatar, "/uploads");
        model.addAttribute("uploaded", saved != null ? saved.getName() : "(không có file)");
        return "account/register";
    }
}
