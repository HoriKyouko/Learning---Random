import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { AuthService } from '../../auth.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {

  constructor(private router: Router, private authService: AuthService) { }

  ngOnInit(): void {
  }

  onLoginButtonClicked(email: string, password: string){
    this.authService.login(email, password).subscribe((res: HttpResponse<any>) => {

      if(res.status == 200){
        // we have logged in successfully
        // we want it to navigate to our option of either create or search
        // drink/food item.

        this.router.navigate(['/menu']);
      }
      //console.log(res);
    });
  }

}
