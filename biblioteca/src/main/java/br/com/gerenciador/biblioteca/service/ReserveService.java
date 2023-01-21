package br.com.gerenciador.biblioteca.service;

import br.com.gerenciador.biblioteca.model.BooksModel;
import br.com.gerenciador.biblioteca.repository.BooksRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReserveService {

    private BooksRepository booksRepository;

    public ReserveService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public Object list() {
        List<BooksModel> notReserved = booksRepository.findAllByDisponible(true);
        return notReserved;
    }

    public void reservedBook(Integer user_id, Integer book_id) {
        booksRepository.bookReserved(book_id, user_id);
    }
    public void backReservedBook(Integer user_id, Integer book_id) {
        booksRepository.backReservedBook(book_id, user_id);
    }

}
