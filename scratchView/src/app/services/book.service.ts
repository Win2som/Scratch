import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { RequestBaseService } from './request-base.service';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Book } from '../models/book.model';
import { Router } from '@angular/router';

const API_URL = environment.BASE_URL +'/api/book'

@Injectable({
  providedIn: 'root'
})
export class BookService extends RequestBaseService{

  constructor(authenticationService: AuthenticationService, http: HttpClient) {
    super(authenticationService, http);
   }


saveBook (book: Book): Observable<any> {
  return this.http.post(API_URL, book, {headers : this.getHeaders});
}

getAllBooks ():Observable<any> {
  return this.http.get(API_URL);
}

deleteBook(book: Book): Observable<any> {
  return this.http.delete(`${API_URL}/${book.id}`, {headers: this.getHeaders});
}
}
