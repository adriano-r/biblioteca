package br.com.gerenciador.biblioteca.service;

import br.com.gerenciador.biblioteca.model.BooksModel;
import br.com.gerenciador.biblioteca.repository.BooksRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReserveService {

    private BooksRepository booksRepository;

    public ReserveService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public Object list() {
        Optional<List<BooksModel>> notReserved = Optional.ofNullable(booksRepository.findAllNotReserved());
        return notReserved.get();
    }

}
