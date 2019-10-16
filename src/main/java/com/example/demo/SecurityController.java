package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class SecurityController {

    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;



    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(){
        return "/";
    }       //return to index on logout

    @GetMapping("/register")
    public String showRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationPage(@Valid @ModelAttribute("user")
                                                  User user, @RequestParam String role, BindingResult result,
                                          Model model){
        model.addAttribute("user", user);
        if(result.hasErrors()){
            return "register";
        }

        if (role.equalsIgnoreCase("admin")){
            userService.saveAdmin(user);
            model.addAttribute("message", "Admin Account Created");
            return "index";
        }
        else if (role.equalsIgnoreCase("user")){
            userService.saveUser(user);
            model.addAttribute("message", "User Account Created");
            return "index";
        }
        else{
            return "index";
        }
    }
}
