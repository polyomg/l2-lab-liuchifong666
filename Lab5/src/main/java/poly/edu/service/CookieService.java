package poly.edu.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CookieService {
    @Autowired HttpServletRequest request;
    @Autowired HttpServletResponse response;

    public Cookie get(String name) {
        Cookie[] arr = request.getCookies();
        if (arr == null) return null;
        for (Cookie c : arr) if (c.getName().equals(name)) return c;
        return null;
    }
    public String getValue(String name) {
        Cookie c = get(name);
        return c == null ? "" : c.getValue();
    }
    public Cookie add(String name, String value, int hours) {
        Cookie c = new Cookie(name, value);
        c.setPath("/");
        c.setHttpOnly(true);
        c.setMaxAge(hours * 3600);
        response.addCookie(c);
        return c;
    }
    public void remove(String name) {
        Cookie c = new Cookie(name, "");
        c.setPath("/");
        c.setMaxAge(0);
        response.addCookie(c);
    }
}
