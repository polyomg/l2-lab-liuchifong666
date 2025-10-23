package poly.edu.service;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    @Autowired
    private ObjectFactory<HttpSession> sessionFactory; // lấy session khi có request

    public void set(String name, Object value) {
        sessionFactory.getObject().setAttribute(name, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String name, T defaultValue) {
        Object val = sessionFactory.getObject().getAttribute(name);
        return val == null ? defaultValue : (T) val;
    }

    public void remove(String name) {
        sessionFactory.getObject().removeAttribute(name);
    }
}
