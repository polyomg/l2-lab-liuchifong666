package poly.edu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import poly.edu.dao.ProductDAO;   // <-- phải có import này

@Controller
public class ReportController {

    @Autowired
    private ProductDAO productDAO;   // <-- KHÔNG phải Object

    @GetMapping("/report/inventory-by-category")
    public String inventoryByCategory(Model model) {
        model.addAttribute("items", productDAO.getInventoryByCategory());
        return "report/inventory-by-category";
    }
}
