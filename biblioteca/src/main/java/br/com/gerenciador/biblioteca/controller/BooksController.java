package br.com.gerenciador.biblioteca.controller;

import br.com.gerenciador.biblioteca.model.BooksModel;
import br.com.gerenciador.biblioteca.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller

public class BooksController {

    @Autowired
    private BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }


    @GetMapping("/books")
    public String getLivrosPage(Model model) {
        model.addAttribute("books", booksService.listBooks());
        return "books_page";
    }

    @GetMapping("/registerBook")
    public String getBooksRegisterPage(Model model) {
        return "book_register_page";
    }

    @PostMapping("/registerBook")
    public String register(@ModelAttribute BooksModel booksModel){
     //   System.out.println("register request: " + booksModel);
        BooksModel registeredBook = booksService.registerBook(booksModel.getName(), booksModel.getGenre(), booksModel.getQuantity());
        return registeredBook == null ? "error_page" : "redirect:/books";
    }

    @PostMapping("/books/{id}")
    public String putBook(@PathVariable("id") Integer id, @ModelAttribute BooksModel booksModel){
        BooksModel editBook = booksService.updateBook(id, booksModel);
        return "books_page";
    }

    @GetMapping("/books/{id}")
    public String getBooksRegisterPage(@PathVariable("id") Integer id, Model model) {
        Optional<BooksModel> booksModel = booksService.getBook(id);
        model.addAttribute("book", booksModel.get());
        return "book_edit_page";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable("id") Integer id, Model model) {
        booksService.deleteBook(id);
        return "redirect:/books";
    }

}
