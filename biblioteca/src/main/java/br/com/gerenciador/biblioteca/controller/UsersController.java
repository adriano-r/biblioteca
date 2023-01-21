package br.com.gerenciador.biblioteca.controller;

import br.com.gerenciador.biblioteca.model.UsersModel;
import br.com.gerenciador.biblioteca.service.UsersService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerRequest", new UsersModel());
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("loginRequest", new UsersModel());
        return "login_page";
    }

    @GetMapping("/users")
    public String getUsersPage(Model model) {
        model.addAttribute("users", usersService.listUsers());
        return "users_page";
    }

    @PostMapping("/users/edit/{id}")
    public String editUsers(@PathVariable("id") Integer id, @ModelAttribute UsersModel usersModel){
        UsersModel editUser = usersService.updateUser(id, usersModel);
        return "users_page";
    }

    @GetMapping("/users/{id}")
    public String getUsersRegisteredPage(@PathVariable("id") Integer id, Model model) {
        Optional<UsersModel> usersModel = usersService.getUser(id);
        model.addAttribute("user", usersModel.get());
        return "personal_user_page";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute UsersModel usersModel){
        System.out.println("register request: " + usersModel);
        UsersModel registeredUser = usersService.registerUser(usersModel.getLogin(), usersModel.getPassword(), usersModel.getEmail());
        return registeredUser == null ? "error_page" : "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute UsersModel usersModel, Model model, HttpSession httpSession){
        System.out.println("login request: " + usersModel);
        UsersModel authenticated = usersService.authenticate(usersModel.getLogin(), usersModel.getPassword());
        if(authenticated != null){
            model.addAttribute("userLogin", authenticated.getLogin());
            httpSession.setAttribute("logged", authenticated);
            return "redirect:/userPersonal";
        }else{
            return "error_page";
        }
    }

    @GetMapping("/users/edit/{id}")
    public String getUserRegisterPage(@PathVariable("id") Integer id, Model model) {
        Optional<UsersModel> usersModel = usersService.getUser(id);
        model.addAttribute("user", usersModel.get());
        return "user_edit_page";
    }

    @GetMapping("/userPersonal")
    public String getPersonalPage() { return "personal_user_page";}

    @GetMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        usersService.deleteUser(id);
        return "redirect:/users";
    }
}
