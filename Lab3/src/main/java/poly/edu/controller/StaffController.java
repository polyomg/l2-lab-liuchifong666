package poly.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import poly.edu.model.Staff;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/staff")
public class StaffController {

    
    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("now", new Date());
        return "demo/index"; 
    }

    
    @GetMapping("/form")
    public String form(Model model) {
        Staff s = Staff.builder()
                .id("S01")
                .fullname("")
                .photo("avatar.jpg")   
                .gender(true)
                .birthday(new Date())
                .salary(5_000_000.0)
                .level(0)
                .build();
        model.addAttribute("staff", s);
        model.addAttribute("levels", List.of("Úy", "Tá", "Tướng"));
        return "demo/StaffForm";
    }

    
    @GetMapping("/param")
    public String paramGet(@ModelAttribute("staff") Staff staff, Model model) {
       
        if (staff == null || staff.getId() == null) {
            staff = Staff.builder()
                    .id("NV01").fullname("Nguyễn Văn A")
                    .photo("a.jpg").gender(true)
                    .birthday(new Date()).salary(7_000_000.0).level(2)
                    .build();
        }
        model.addAttribute("staff", staff);
        return "demo/StaffParam";
    }

    
    @PostMapping("/param")
    public String paramPost(@ModelAttribute("staff") Staff staff, Model model) {
        
        model.addAttribute("staff", staff);
        return "demo/StaffParam";
    }

   
    @GetMapping("/control")
    public String control(Model model) {
        List<Staff> list = List.of(
                Staff.builder().id("NV01").fullname("Nguyễn Văn A").gender(true).level(0).salary(5_000_000.0).photo("avatar.jpg").build(),
                Staff.builder().id("NV02").fullname("Trần Thị B").gender(false).level(1).salary(7_500_000.0).photo("avatar2.jpg").build(),
                Staff.builder().id("NV03").fullname("Lê Văn C").gender(true).level(2).salary(12_000_000.0).photo("avatar3.jpg").build()
        );
        model.addAttribute("list", list);
        model.addAttribute("levels", List.of("Úy", "Tá", "Tướng"));
        return "demo/StaffControl"; 
    }
}
