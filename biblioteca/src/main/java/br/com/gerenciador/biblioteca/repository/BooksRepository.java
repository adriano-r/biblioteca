package br.com.gerenciador.biblioteca.repository;

import br.com.gerenciador.biblioteca.model.BooksModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BooksRepository extends JpaRepository<BooksModel, Integer> {

    Optional<BooksModel> findByName(String name); //editar livro
    List<BooksModel> findAll(); //listar livros

//    @Query("select book.*, count(reserved.book_id) FROM biblioteca.books_table as book left join biblioteca.reserved_book as reserved on book.id = reserved.book_id where disponible = true group by book.id;")

    @Query( nativeQuery = true, value = "select book.*, count(reserved.book_id) as contador FROM biblioteca.books_table as book left join biblioteca.reserved_book as reserved on book.id = reserved.book_id where disponible = true group by book.id;")

    List<BooksModel> findAllNotReserved();


}
