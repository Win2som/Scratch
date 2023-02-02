import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../models/user.model';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export abstract class RequestBaseService {

  protected currentUser: User = new User();

  constructor(protected authenticationService: AuthenticationService, protected http: HttpClient) { 
    this.authenticationService.currentUser.subscribe(data =>{
      this.currentUser = data
    });
  }

  get getHeaders() {
    return new HttpHeaders(
      {
        authorization: 'Bearer ' + this.currentUser?.token,
        "Content-Type" : "application/json; charset=UTF-8"
      }
    );
  }
}
