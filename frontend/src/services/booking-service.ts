import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class BookingService {
  apiUrl: string = 'http://localhost:8081/api/booking';
  constructor(private http: HttpClient) {}

  createBooking(bookingData: any): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Accept: 'application/json',
    });

    return this.http.post(this.apiUrl, bookingData, {
      headers,
      observe: 'response',
      withCredentials: false,
    });
  }

  getBookings(): Observable<any[]> {
    return this.http.get<any[]>(environment.apiUrl + '/order-service/api/bookings');
  }
}
