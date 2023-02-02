import { AuthGuard } from './guard/auth.guard';
import { NotFoundComponent } from './error/not-found/not-found.component';
import { LoginComponent } from './guest/login/login.component';
import { HomeComponent } from './guest/home/home.component';
import { NgModule } from '@angular/core';
import { Router, RouterModule, Routes, CanActivate } from '@angular/router';
import { RegisterComponent } from './guest/register/register.component';
import { ProfileComponent } from './user/profile/profile.component';
import { AdminComponent } from './admin/admin/admin.component';
import { UnauthorisedComponent } from './error/unauthorised/unauthorised.component';
import { Role } from './models/role.enum';

const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},

  {path: 'home', component: HomeComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'login', component: LoginComponent},

  {path: 'profile', 
  component: ProfileComponent,
  canActivate: [AuthGuard], 
  data: {roles: [Role.ADMIN, Role.USER]}
  },

  {path: 'admin', 
  component: AdminComponent,
  canActivate: [AuthGuard],
  data: {roles: [Role.ADMIN]}
  },

  {path: '404', component: NotFoundComponent},
  {path: '401', component: UnauthorisedComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
  constructor(private router: Router){
    this.router.errorHandler = (error: any) => {
      this.router.navigate(['/404']);
    }
  }
 }