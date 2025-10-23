package poly.edu.service;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ParamService {

    @Autowired
    HttpServletRequest request;

    @Autowired
    ServletContext app;

    // Đọc chuỗi
    public String getString(String name, String defaultValue) {
        String v = request.getParameter(name);
        return (v == null || v.isBlank()) ? defaultValue : v.trim();
    }

    // Đọc số nguyên
    public int getInt(String name, int defaultValue) {
        try {
            String v = request.getParameter(name);
            return (v == null || v.isBlank()) ? defaultValue : Integer.parseInt(v.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    // Đọc số thực
    public double getDouble(String name, double defaultValue) {
        try {
            String v = request.getParameter(name);
            return (v == null || v.isBlank()) ? defaultValue : Double.parseDouble(v.trim());
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    // Đọc boolean
    public boolean getBoolean(String name, boolean defaultValue) {
        String v = request.getParameter(name);
        if (v == null) return defaultValue;
        v = v.trim().toLowerCase();
        return v.equals("true") || v.equals("on") || v.equals("1");
    }

    // Đọc ngày theo pattern (ví dụ: "yyyy-MM-dd")
    public Date getDate(String name, String pattern) {
        try {
            String v = request.getParameter(name);
            if (v == null || v.isBlank()) return null;
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            sdf.setLenient(false);
            return sdf.parse(v.trim());
        } catch (Exception e) {
            throw new RuntimeException("Sai định dạng ngày: " + pattern);
        }
    }

    // Lưu file upload vào /static/uploads (nếu chạy embedded)
    public File save(MultipartFile file, String path) {
        try {
            if (file == null || file.isEmpty()) return null;

            // Lấy đường dẫn vật lý
            String realPath = app.getRealPath(path);
            if (realPath == null) {
                // Khi chạy embedded Tomcat, dùng thư mục static
                realPath = System.getProperty("user.dir")
                        + File.separator + "src"
                        + File.separator + "main"
                        + File.separator + "resources"
                        + File.separator + "static"
                        + path;
            }

            Path dir = Path.of(realPath);
            Files.createDirectories(dir);

            String filename = file.getOriginalFilename();
            if (filename == null || filename.isBlank()) {
                filename = String.valueOf(System.currentTimeMillis());
            }

            Path target = dir.resolve(filename);
            file.transferTo(target.toFile());
            return target.toFile();
        } catch (Exception e) {
            throw new RuntimeException("Lỗi lưu file: " + e.getMessage(), e);
        }
    }
}
