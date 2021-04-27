import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Subscription, Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { HttpClient, HttpParams } from '@angular/common/http';

import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/auth/account.model';
import { ApplicationConfigService } from 'app/core/config/application-config.service';

export interface FizzBuzzDTO {
  fecha: string;
  codigo: string;
  descripcion: string;
  list: string[];
}

const defalut: FizzBuzzDTO = {
  fecha: '',
  codigo: '',
  descripcion: '',
  list: [],
};

@Component({
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit, OnDestroy {
  account: Account | null = null;
  authSubscription?: Subscription;
  min = 0;
  max = 0;
  resultado: FizzBuzzDTO;

  constructor(
    private accountService: AccountService,
    private router: Router,
    private http: HttpClient,
    private applicationConfigService: ApplicationConfigService
  ) {
    this.resultado = defalut;
  }

  ngOnInit(): void {
    this.authSubscription = this.accountService.getAuthenticationState().subscribe(account => (this.account = account));
  }

  isAuthenticated(): boolean {
    return this.accountService.isAuthenticated();
  }

  login(): void {
    this.router.navigate(['/login']);
  }

  ngOnDestroy(): void {
    if (this.authSubscription) {
      this.authSubscription.unsubscribe();
    }
  }

  onResultado(): void {
    this.getResulados()
      .pipe(tap((x: FizzBuzzDTO) => (this.resultado = x)))
      .subscribe();
  }

  getResulados(): Observable<FizzBuzzDTO> {
    return this.http.get<FizzBuzzDTO>(
      this.applicationConfigService.getEndpointFor(`api/intraway/fizzbuzz?min=${this.min}&max=${this.max}`)
    );
  }

  onGuardar(): void {
    const cuerpo: FizzBuzzDTO = this.resultado;
    this.http
      .post<FizzBuzzDTO>(this.applicationConfigService.getEndpointFor(`api/intraway/fizzbuzz`), cuerpo)
      .pipe(tap(x => this.onLimpiar()))
      .subscribe();
  }

  onLimpiar(): void {
    this.min = 0;
    this.max = 0;
    this.resultado = defalut;
  }
}
