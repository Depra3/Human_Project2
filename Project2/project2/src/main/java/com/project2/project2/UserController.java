package com.project2.project2;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String index() {
        return "home";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(User user, HttpServletRequest req) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return "redirect:/";
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("gUserid", existingUser);
            return "login";
        }
    }

    @RequestMapping("/loginout")
    public String doLoginout(HttpServletRequest req){
        HttpSession session = req.getSession();
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(User user) {
        userRepository.save(user);
        return "redirect:/login";
    }

    // 회원정보 목록 출력
    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> userList = userRepository.findAll();
        model.addAttribute("userList", userList);
        return "users";
    }
}
