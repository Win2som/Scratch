import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Book } from 'src/app/models/book.model';
import { BookService } from 'src/app/services/book.service';

declare var $: any;

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})

export class BookComponent {

  errorMessage: string = "";
  //gets an input -book from parent
  @Input() book: Book =  new Book();
  //sends an event called save to parent
  @Output() save = new EventEmitter<any>;

  constructor(private bookService: BookService) {}

  //saves book and emits saved data to parent
  saveBook() {
    this.bookService.saveBook(this.book).subscribe(data =>{
      this.save.emit(data);
      $('#bookModal').modal('hide');
      console.log(data);

    }, err => {
      this.errorMessage = "An Unexpected Error Occurred";
      console.log(err);
    });
  }

  showBookModal() {
    $('#bookModal').modal('show');
  }
}
