package br.com.gerenciador.biblioteca.controller;

import br.com.gerenciador.biblioteca.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReserveController {
    @Autowired
    private BooksService booksService;

    public ReserveController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/personal")
    public String getReservaPage(Model model) {
        model.addAttribute("books", booksService.listBooks());
        return "personal_user_page";
    }

}
