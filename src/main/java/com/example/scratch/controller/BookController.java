package com.example.scratch.controller;

import com.example.scratch.model.Book;
import com.example.scratch.service.IBookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/book")
public class BookController {

  @Autowired
  private IBookService mBookService;

  @PostMapping
  public ResponseEntity<?> saveBook(@RequestBody Book book){

    return new ResponseEntity<>(mBookService.saveBook(book), HttpStatus.CREATED);
  }


  @DeleteMapping("{bookId}")
  public ResponseEntity<?> deleteBook(@PathVariable Long bookId){

    mBookService.deleteBook(bookId);
    return new ResponseEntity<>(HttpStatus.OK);
  }


  @GetMapping
  public ResponseEntity<?> getAllBooks(){

    return new ResponseEntity<>(mBookService.findAllBooks(), HttpStatus.OK);
  }
}
