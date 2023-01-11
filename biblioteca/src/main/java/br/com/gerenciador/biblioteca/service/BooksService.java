package br.com.gerenciador.biblioteca.service;

import br.com.gerenciador.biblioteca.model.BooksModel;
import br.com.gerenciador.biblioteca.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksService {
    @Autowired
    private BooksRepository booksRepository;

    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public BooksModel registerBook(String name, String genre, String quantity) {
        if(name == null || genre == null || quantity == null ) {
            return null;
        } else {
            if (booksRepository.findByName(name).isPresent()){
                System.out.println("Nome duplicado!");
                return null;
            }
            BooksModel booksModel = new BooksModel();
            booksModel.setName(name);
            booksModel.setGenre(genre);
            booksModel.setQuantity(Integer.valueOf(quantity));
            return booksModel;
        }
    }

    public List<BooksModel> listBooks() {
        return booksRepository.findAll();
    }
}
