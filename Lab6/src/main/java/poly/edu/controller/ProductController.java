package poly.edu.controller;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;          // <— THÊM DÒNG NÀY
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import poly.edu.dao.ProductDAO;
import poly.edu.entity.Product;

@Controller
public class ProductController {

  @Autowired
  ProductDAO dao;

  @GetMapping("/product/sort")
  public String sort(Model model, @RequestParam("field") Optional<String> field) {
    String f = field.orElse("price");
    Sort sort = Sort.by(Direction.DESC, f);
    model.addAttribute("field", f.toUpperCase());
    List<Product> items = dao.findAll(sort);
    model.addAttribute("items", items);
    return "product/sort";
  }

  @GetMapping("/product/page")
  public String paginate(Model model, @RequestParam("p") Optional<Integer> p) {
    int pageIndex = Math.max(0, p.orElse(0));            
    Pageable pageable = PageRequest.of(pageIndex, 5);    
    Page<Product> page = dao.findAll(pageable);
    model.addAttribute("page", page);
    model.addAttribute("items", page.getContent());
    return "product/page";
  }
}
