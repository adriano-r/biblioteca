package br.com.gerenciador.biblioteca.controller;

import br.com.gerenciador.biblioteca.service.BooksService;
import br.com.gerenciador.biblioteca.service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReserveController {
    @Autowired
    private BooksService booksService;
    private ReserveService reserveService;

    public ReserveController(BooksService booksService, ReserveService reserveService) {
        this.booksService = booksService;
        this.reserveService = reserveService;
    }

    @GetMapping("/reserved")
    public String getReservedBook(Model model){
        Object reservedBook = reserveService.list();
        model.addAttribute("reserveds", reservedBook);
        System.out.println(reservedBook);
        return "personal_user_page";
    }

}
