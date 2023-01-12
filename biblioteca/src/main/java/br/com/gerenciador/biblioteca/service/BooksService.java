package br.com.gerenciador.biblioteca.service;

import br.com.gerenciador.biblioteca.model.BooksModel;
import br.com.gerenciador.biblioteca.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksService {
    @Autowired
    private BooksRepository booksRepository;

    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public BooksModel registerBook(String name, String genre, Integer quantity) {
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
            booksModel.setQuantity(quantity);
            return booksRepository.save(booksModel);
        }
    }

    public List<BooksModel> listBooks() {
        return booksRepository.findAll();
    }

    public Optional<BooksModel> getBook(Integer id) {
        return booksRepository.findById(id);
    }

    public BooksModel updateBook(Integer id, BooksModel model) {
        Optional<BooksModel> updatedBook = booksRepository.findById(id);
        System.out.println(updatedBook);
        BooksModel booksModel = updatedBook.get();
        booksModel.setName(model.getName());
        booksModel.setGenre(model.getGenre());
        booksModel.setQuantity(model.getQuantity());
        return booksRepository.save(booksModel);
    }

    public void deleteBook(Integer id){
        booksRepository.deleteById(id);
    }
}
