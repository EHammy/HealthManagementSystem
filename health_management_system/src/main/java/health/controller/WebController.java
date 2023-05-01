package health.controller;

import java.util.Optional;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import health.beans.User;
//import health.beans.Vitals;
import health.beans.UserLoginForm;
import health.beans.Address;
import health.repository.UserRepository;
import health.repository.VitalRepository;
import jakarta.servlet.http.HttpSession;

@Controller
public class WebController {
	
	@Autowired
    UserRepository userRepository;
	
	@Autowired
    VitalRepository vitalRepository;

//User Sign Up
	@GetMapping("/userSignUp")
    public String showAdopterRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "/userSignUp";
    }

    @PostMapping("/userSignUp")
    public String submitUserRegistrationForm(@ModelAttribute("user") @Valid User user, BindingResult result, @RequestParam("street") String street, @RequestParam("city") String city, @RequestParam("state") String state) {
        if (result.hasErrors()) {
            return "/userSignUp";
        }
        Address address = new Address(street, city, state);
        user.setAddress(address);
        userRepository.save(user);
        return "redirect:/userLogin";
    }
 
    @GetMapping("/users/{username}")
    public ResponseEntity<User> getAdopterByUsername(@PathVariable String username) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
//User Login 
    @GetMapping("/userLogin")
    public String showAdopterLoginForm(Model model) {
        model.addAttribute("userLoginForm", new UserLoginForm());
        return "userLogin";
    }

    @PostMapping("/userLogin")
    public String submitUserLoginForm(@ModelAttribute("userLoginForm") @Valid UserLoginForm userLoginForm, BindingResult result, HttpSession session) {
        if (result.hasErrors()) {
            return "userLogin";
        }
        Optional<User> optionalUser = userRepository.findByUsername(userLoginForm.getUsername());
        if (optionalUser.isPresent() && optionalUser.get().getPassword().equals(userLoginForm.getPassword())) {
            User user = optionalUser.get();
            session.setAttribute("user", user);
            return "redirect:/userDashboard";
        } else {
            result.rejectValue("password", "error.invalidLogin", "Invalid username or password.");
            return "userLogin";
        }
    }
    
//User Dashboard
    @GetMapping("/userDashboard")
    public String showUserDashboard(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/userLogin";
        }
        model.addAttribute("user", user);
        return "userDashboard";
    }
    
 // User Logout
    @GetMapping("/userLogout")
    public String userLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/userLogin";
    }
//help page
    @GetMapping("/help")
    public String showHelpPage() {
        return "help";
    }
   

    
}