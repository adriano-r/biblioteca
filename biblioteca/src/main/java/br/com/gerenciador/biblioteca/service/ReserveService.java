package br.com.gerenciador.biblioteca.service;

import br.com.gerenciador.biblioteca.repository.BooksRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReserveService {

    private BooksRepository booksRepository;

    public Object list() {
        Optional<Object> notReserved = Optional.ofNullable(booksRepository.findNotReserved());
        return notReserved.get();
    }

}
