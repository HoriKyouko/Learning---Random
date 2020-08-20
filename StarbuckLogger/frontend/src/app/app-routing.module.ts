import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { SignupPageComponent } from './pages/signup-page/signup-page.component';
import { MenuViewComponent } from './pages/menu-view/menu-view.component';
import { EntryPageComponent } from './pages/entry-page/entry-page.component';

const routes: Routes = [
  {path: "", redirectTo: 'login', pathMatch: 'full'},
  {path: 'login', component: LoginPageComponent},
  {path: 'signup', component: SignupPageComponent},
  {path: 'menu', component: MenuViewComponent},
  {path: 'entry', component: EntryPageComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
