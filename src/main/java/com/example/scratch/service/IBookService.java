package com.example.scratch.service;

import com.example.scratch.model.Book;

import java.util.List;

public interface IBookService {
  Book saveBook(Book book);

  void deleteBook(Long id);

  List<Book> findAllBooks();
}
