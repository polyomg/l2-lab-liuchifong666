package poly.edu.controller;

import poly.edu.data.DB;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ItemController {

    @GetMapping("/")
    public String home() { return "redirect:/item"; }

    @GetMapping("/item")
    public String index(Model model) {
        model.addAttribute("items", DB.items.values());
        return "item/index";
    }
}
