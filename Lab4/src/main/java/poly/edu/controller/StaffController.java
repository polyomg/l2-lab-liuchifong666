package poly.edu.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import poly.edu.model.Staff;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/staff")
public class StaffController {

    
    @GetMapping("/create/form")
    public String createForm(@ModelAttribute("staff") Staff staff, Model model) {
        if (staff.getId() == null) { // seed mặc định khi lần đầu mở
            staff = Staff.builder()
                    .id("user@gmail.com")
                    .fullname("Nguyễn Văn A")
                    .gender(true)
                    .birthday(LocalDate.now().minusYears(20))
                    .salary(12345.6789)
                    .level(2)
                    .photo("avatar3.jpg")
                    .build();
        }
        model.addAttribute("staff", staff);
        model.addAttribute("levels", List.of("Úy", "Tá", "Tướng"));
        model.addAttribute("message", "Vui lòng nhập thông tin nhân viên!");
        return "demo/staff-create";
    }

    
    @PostMapping("/create/save")
    public String createSave(@ModelAttribute("staff") Staff staff,
                             @RequestPart(value = "photo_file", required = false) MultipartFile photoFile,
                             Model model) {
        if (photoFile != null && !photoFile.isEmpty()) {
            
            staff.setPhoto(photoFile.getOriginalFilename());
        }
        model.addAttribute("levels", List.of("Úy", "Tá", "Tướng"));
        model.addAttribute("message", "Xin chào " + staff.getFullname());
        return "demo/staff-create";
    }

    
    @PostMapping(value = "/create/save", params = "validate")
    public String createValidate(@Valid @ModelAttribute("staff") Staff staff,
                                 Errors errors,
                                 @RequestPart(value = "photo_file", required = false) MultipartFile photoFile,
                                 Model model) {
        if (photoFile != null && !photoFile.isEmpty()) {
            staff.setPhoto(photoFile.getOriginalFilename());
        }
        model.addAttribute("levels", List.of("Úy", "Tá", "Tướng"));
        model.addAttribute("message", errors.hasErrors() ? "Vui lòng sửa lỗi!" : "Dữ liệu hợp lệ!");
        return "demo/staff-validate";
    }
}
