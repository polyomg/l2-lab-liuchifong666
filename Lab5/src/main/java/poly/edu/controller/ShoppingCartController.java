package poly.edu.controller;

import poly.edu.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ShoppingCartController {

    @Autowired
    ShoppingCartService cart;

    @GetMapping("/cart")
    public String view(Model model) {
        model.addAttribute("cartItems", cart.getItems());
        model.addAttribute("count", cart.getCount());
        model.addAttribute("amount", cart.getAmount());
        return "cart/index";
    }

    @PostMapping("/cart/add/{id}")
    public String add(@PathVariable Integer id) {
        cart.add(id);
        return "redirect:/cart";
    }

    @PostMapping("/cart/remove/{id}")
    public String remove(@PathVariable Integer id) {
        cart.remove(id);
        return "redirect:/cart";
    }

    @PostMapping("/cart/update")
    public String update(@RequestParam Integer id, @RequestParam Integer qty) {
        cart.update(id, qty == null ? 0 : qty);
        return "redirect:/cart";
    }

    @PostMapping("/cart/clear")
    public String clear() {
        cart.clear();
        return "redirect:/cart";
    }
}
