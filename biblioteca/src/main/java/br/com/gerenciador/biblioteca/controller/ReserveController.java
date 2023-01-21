package br.com.gerenciador.biblioteca.controller;

import br.com.gerenciador.biblioteca.model.BooksModel;
import br.com.gerenciador.biblioteca.model.UsersModel;
import br.com.gerenciador.biblioteca.service.BooksService;
import br.com.gerenciador.biblioteca.service.ReserveService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ReserveController {
    @Autowired
    private BooksService booksService;
    private ReserveService reserveService;

    public ReserveController(BooksService booksService, ReserveService reserveService) {
        this.booksService = booksService;
        this.reserveService = reserveService;
    }

    int counter = 0;
    boolean userCanReservedBook = true;
    @GetMapping("/reserved")
    public String getReservedBook(Model model, HttpSession httpSession){
        UsersModel usersModel = (UsersModel) httpSession.getAttribute("logged");
        List<BooksModel> reservedBook = (List<BooksModel>) reserveService.list();
        List<BooksModel> userBooks = new ArrayList<>();
        List<BooksModel> disponibles = new ArrayList<>();
        for (BooksModel reserved: reservedBook) {
            reserved.getUsersModels().forEach(user -> {
                counter++;
                if(user.getId() == usersModel.getId()){
                    userCanReservedBook = false;
                    userBooks.add(reserved);
                }
            });

            if(reserved.getQuantity() > counter && userCanReservedBook){
                disponibles.add(reserved);
            }
            counter = 0;
            userCanReservedBook = true;
        }

        model.addAttribute("reserveds", disponibles);
        System.out.println(userBooks);
        model.addAttribute("usersBooks", userBooks);
        return "personal_user_page";
    }

    @GetMapping("/reserved/{id}")
    public String getReservedBook(@PathVariable("id") Integer id, HttpSession httpSession){
        UsersModel usersModel = (UsersModel) httpSession.getAttribute("logged");
        System.out.println(usersModel);
        reserveService.reservedBook(usersModel.getId(), id);
        return "redirect:/reserved";
    }

    @GetMapping("/back/{id}")
    public String getBackReserverdBook(@PathVariable("id") Integer id, HttpSession httpSession){
        UsersModel usersModel = (UsersModel) httpSession.getAttribute("logged");
        System.out.println(usersModel);
        reserveService.backReservedBook(usersModel.getId(), id);
        return "redirect:/reserved";
    }


}
