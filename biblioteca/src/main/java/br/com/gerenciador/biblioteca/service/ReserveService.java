package br.com.gerenciador.biblioteca.service;

import br.com.gerenciador.biblioteca.repository.BooksRepository;
import org.springframework.stereotype.Service;

@Service
public class ReserveService {

    private BooksRepository booksRepository;

//    public List<BooksModel> listBooks() {
//        List<BooksModel> searchResults = booksRepository
//                .findAll(Specification
//                        .where(specA)
//                        .and(Specification
//                                .not(specB)));
//        return booksRepository.findAll();
//    }

}
