package springboot.springboot.controller;

import jakarta.validation.Valid;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springboot.springboot.exception.UserAlreadyException;
import springboot.springboot.model.User;
import springboot.springboot.service.UserService;

@Slf4j
@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String showUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "allUsers";
    }

    @GetMapping("/newUser")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "newUser";
    }

    @ExceptionHandler
    public ResponseEntity<String> handle(UserAlreadyException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.PAYMENT_REQUIRED);
    }

    @SneakyThrows
    @PostMapping("/allUsers")
    public String create(@ModelAttribute @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "newUser";
        userService.createUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String readUser(@PathVariable int id, Model model) {
        model.addAttribute("user", userService.readUser(id));
        return "show";
    }

    @GetMapping("{id}/edit")
    public String edit(Model model, @PathVariable int id) {
        model.addAttribute("user", userService.readUser(id));
        return "updateUser";
    }

    @PatchMapping("{id}")
    public String update(@ModelAttribute @Valid User user, BindingResult bindingResult,
                         @PathVariable int id) {
        if (bindingResult.hasErrors())
            return "updateUser";
        userService.updateUser(user, id);
        return "redirect:/users";
    }

    @DeleteMapping("{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
