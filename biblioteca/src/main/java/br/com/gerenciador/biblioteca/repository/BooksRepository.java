package br.com.gerenciador.biblioteca.repository;

import br.com.gerenciador.biblioteca.model.BooksModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface BooksRepository extends JpaRepository<BooksModel, Integer> {

    Optional<BooksModel> findByName(String name); //editar livro
    List<BooksModel> findAll(); //listar livros

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO biblioteca.reserved_book (book_id, user_id) VALUES (:book_id, :user_id);")
     void bookReserved(@Param("book_id") Integer book_id, @Param("user_id") Integer user_id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "delete from biblioteca.reserved_book where book_id = ? and user_id = ? ;")
    void backReservedBook(Integer book_id, Integer user_id);

    List<BooksModel> findAllByDisponible(Boolean disponible);



}
