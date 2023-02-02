import { PurchaseService } from './../../services/purchase.service';
import { BookService } from './../../services/book.service';
import { AuthenticationService } from './../../services/authentication.service';
import { Component, OnInit } from '@angular/core';
import { faBook } from '@fortawesome/free-solid-svg-icons';
import { Book } from 'src/app/models/book.model';
import { Purchase } from 'src/app/models/purchase.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  bookList: Array<Book> = [];
  faBook = faBook;
  errorMessage: string = "";
  infoMessage: string = "";

  constructor(private authenticationService: AuthenticationService,
              private bookService: BookService,
              private purchaseService: PurchaseService) { }

  ngOnInit(): void {
    this.bookService.getAllBooks().subscribe(data => {
      this.bookList = data;
    });
  }

  purchaseBook(item: Book) {
    if(!this.authenticationService.currentUserValue?.id){
      this.errorMessage = "You should log in to buy a book.";
      return;
    }

    const purchase = new Purchase(this.authenticationService.currentUserValue.id, item.id, item.price);

    this.purchaseService.savePurchase(purchase).subscribe(data => {
      this.infoMessage = "Purchase complete.";
    }, err => {
      this.errorMessage = "An unexpected error occurred.";
      console.log(err);
    });


  }

}
