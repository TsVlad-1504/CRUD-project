package web.controller;

import org.springframework.http.ResponseEntity;
import web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String allUsers(Model model) {

        model.addAttribute("users",
                userService.getAllUsers());

        return "users";
    }

    @GetMapping("/new")
    public String addUserForm(Model model) {

        model.addAttribute("user",
                new User());

        return "add-user";
    }

    @PostMapping
    public String saveUser(@ModelAttribute("user") User user) {

        userService.saveUser(user);

        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable("id") Long id,
                               Model model) {

        model.addAttribute("user",
                userService.getUserById(id));

        return "edit-user";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {

        userService.updateUser(user);

        return "redirect:/users";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUser((long) id);
        return "redirect:/users";
    }
}
