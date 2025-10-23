package poly.edu.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import poly.edu.model.HinhChuNhat;

@Controller
public class hinhchunhatController {

    @GetMapping("/tinh")
    public String showForm() {
        return "formtinh";
    }

    @PostMapping("/ketqua")
    public String calculate(
            @RequestParam(name = "width", required = false) String width,
            @RequestParam(name = "height", required = false) String height,
            Model model) {

        if (width == null || width.isBlank() || height == null || height.isBlank()) {
            model.addAttribute("error", "Vui lòng nhập cả chiều dài và chiều rộng!");
            return "formtinh";
        }

        double w, h;
        try {
            w = Double.parseDouble(width.trim());
            h = Double.parseDouble(height.trim());
        } catch (NumberFormatException e) {
            model.addAttribute("error", "Vui lòng nhập số hợp lệ!");
            return "formtinh";
        }

        if (w <= 0 || h <= 0) {
            model.addAttribute("error", "Chiều dài và chiều rộng phải lớn hơn 0!");
            return "formtinh";
        }

        HinhChuNhat hcn = new HinhChuNhat(w, h);
        model.addAttribute("hcn", hcn);

        return "formketqua";
    }}