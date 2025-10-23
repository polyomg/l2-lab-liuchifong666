package poly.edu.Controller;

import poly.edu.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @GetMapping("/form")
    public String form(Model model) {
       
        Product p = new Product("iPhone 30", 5000.0);
        model.addAttribute("p1", p);

        
        model.addAttribute("p2", new Product());

        return "product/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("p2") Product p) {
        
        return "product/form";
    }

    @ModelAttribute("items")
    public List<Product> getItems() {
        return Arrays.asList(
                new Product("A", 1.0),
                new Product("B", 12.0)
        );
    }
}
