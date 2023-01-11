package br.com.gerenciador.biblioteca.repository;

import br.com.gerenciador.biblioteca.model.BooksModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BooksRepository extends JpaRepository<BooksModel, Integer> {

    Optional<BooksModel> findByName(String name); //editar livro
    List<BooksModel> findAll(); //listar livros

}
