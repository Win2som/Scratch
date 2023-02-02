import { BookComponent } from './../book/book.component';
import { BookService } from './../../services/book.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Book } from 'src/app/models/book.model';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  bookList: Array<Book> = [];
  selectedBook: Book = new Book();
  errorMessage: string = "";

  @ViewChild(BookComponent) child: BookComponent|undefined;

  constructor(private bookService: BookService) { }

  ngOnInit(): void {
    this.bookService.getAllBooks().subscribe(data =>{
      this.bookList = data;
    });
  }

//sends to child a new item
  createBookRequest() {
    this.selectedBook = new Book();
    this.child?.showBookModal();
  }


  //sends to child a copy of the selected item
  editBookRequest(item: Book) {
    this.selectedBook = Object.assign({}, item);
    this.child?.showBookModal();
  }

  //checks to see if the emitted book is in the list then updates it, else adds it.
  saveBookWatcher(book: Book) {
    let itemIndex = this.bookList.findIndex(item => item.id === book.id);
    if(itemIndex !== -1){
      this.bookList[itemIndex] = book;
    }else{
      this.bookList.push(book);
    }
  }


  deleteBook(item: Book, index: number) {
    this.bookService.deleteBook(item).subscribe(data => {
      this.bookList.splice(index, 1);
    }, err => {
      this.errorMessage = "An unexpected error occurred."
      console.log(err);
    });

  }

}
