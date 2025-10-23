package poly.edu.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;
import poly.edu.entity.Product;
import poly.edu.service.ProductService;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    // Danh sách sản phẩm
    @GetMapping({"/list", ""})
    public String list(Model model) {
        List<Product> items = productService.findAll();
        model.addAttribute("items", items);
        return "product/list";
    }

    // Create form
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("product", new Product());
        return "product/form";
    }

    // Edit form
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Product p = productService.findById(id);
        if (p == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        model.addAttribute("product", p);
        return "product/form";
    }

    // Save (Create/Update)
    @PostMapping("/save")
    public String save(@ModelAttribute("product") Product product, RedirectAttributes ra) {
        productService.save(product);
        ra.addFlashAttribute("msg", "Saved!");
        return "redirect:/product/list";
    }

    // Delete
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, RedirectAttributes ra) {
        productService.delete(id);
        ra.addFlashAttribute("msg", "Deleted!");
        return "redirect:/product/list";
    }

    // Bài 1: Tìm theo khoảng giá -> dùng view product/search.html
    @GetMapping("/search-price")
    public String searchByPrice(
            @RequestParam(value = "min", required = false) Double min,
            @RequestParam(value = "max", required = false) Double max,
            Model model) {

        List<Product> items = productService.searchByPrice(min, max);
        model.addAttribute("min", min);
        model.addAttribute("max", max);
        model.addAttribute("items", items);
        return "product/search";
    }

    // Bài 2: Tìm theo tên + phân trang -> dùng view product/search-and-page.html
    @GetMapping("/search-name")
    public String searchByName(
            @RequestParam(value = "q", required = false) String q,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            Model model) {

        Page<Product> p = productService.findPage(q, page, size);
        model.addAttribute("page", p);
        model.addAttribute("q", q);
        return "product/search-and-page";
    }
}
