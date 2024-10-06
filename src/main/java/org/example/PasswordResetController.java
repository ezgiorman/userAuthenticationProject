package org.example;

import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;

@Controller
public class PasswordResetController {

    private final userService userService;

    public PasswordResetController(userService userService) {
        this.userService = userService;
    }

    @GetMapping("/reset-password")
    public String resetPasswordForm(@RequestParam("token") String token, Model model) {

        model.addAttribute("token", token);
        return "reset-password-form";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam("token") String token, @RequestParam("newPassword") String newPassword) {
        String result = userService.resetPassword(token, newPassword);

        if (result.equals("success")) {
            return "redirect:/login";
        } else {
            return "reset-password-failed";
        }
    }
}
