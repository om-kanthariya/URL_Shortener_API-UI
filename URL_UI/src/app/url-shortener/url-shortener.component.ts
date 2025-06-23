import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-url-shortener',
  templateUrl: './url-shortener.component.html',
  styleUrls: ['./url-shortener.component.css'],
  standalone:false,
})
export class UrlShortenerComponent {
  originalUrl = '';
  shortUrl = '';

  constructor(private http: HttpClient) {}

  shortenUrl() {
    this.http.post<any>('http://localhost:8080/api/shorten', { originalUrl: this.originalUrl })
      .subscribe(response => {
        this.shortUrl = `http://localhost:8080/${response.shortCode}`;
      });
  }
}
