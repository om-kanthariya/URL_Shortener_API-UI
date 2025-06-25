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
  originalUrlMaker = '';
  shortCodenew = '';

  constructor(private http: HttpClient) {}

  shortenUrl() {
    this.http.post<any>('http://localhost:8080/api/shorten', { originalUrl: this.originalUrl })
      .subscribe(response => {
        this.shortUrl = `http://localhost:8080/${response.shortCode}`;
        this.shortCodenew = response.shortCode;
      });
  }
 getOriginalURL() {
  // Extract the short code from full URL (e.g., http://localhost:8080/abc123 → abc123)
  const shortCode = this.originalUrl.split('/').pop();

  this.http.get(`http://localhost:8080/api/${this.shortCodenew}`, {
    responseType: 'text',
    observe: 'response'
  }).subscribe({
   next: (res) => {
    const originalUrl = res.body; // ✅ safe
    if (originalUrl) {
     window.open(originalUrl, '_blank');
    }
    },
    error: err => {
      console.error(err);
      alert('URL not found or redirection failed');
    }
  });
}

}
