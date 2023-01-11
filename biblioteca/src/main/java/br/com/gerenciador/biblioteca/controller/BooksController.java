package br.com.gerenciador.biblioteca.controller;

import br.com.gerenciador.biblioteca.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class BooksController {

    @Autowired
    private BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }


    @GetMapping("/books")
    public String getLivrosPage(Model model) {
//        model.addAttribute("id", 2);
//        model.addAttribute("name", "Java");
//        model.addAttribute("genre", "Terror");
//        model.addAttribute("quantity", 4);

        model.addAttribute("books", booksService.listBooks());
        return "books_page";
    }

}
