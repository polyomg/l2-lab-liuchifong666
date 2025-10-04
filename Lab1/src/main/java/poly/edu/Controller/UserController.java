package poly.edu.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import poly.edu.model.User;

@Controller
public class UserController {

    @GetMapping("/user")
    public String display() {
        return "user"; 
    }

    @PostMapping("/create")
    public String sendDetail(String username, String password, Model model) {
        
        if ("phong".equals(username) && "123".equals(password)) {
            User u = new User(username, password);
            model.addAttribute("USER", u);
            model.addAttribute("message", "Đăng nhập thành công" );
            return "detail"; 
        } else {
            model.addAttribute("message", "Đăng nhập thất bại ");
            return "user"; 
        }
    }
}
