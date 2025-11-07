import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../environments/environment';
import { Venue } from '../app/model/venue.model';

@Injectable({
  providedIn: 'root',
})
export class VenueService {
  apiUrl: string = environment.apiUrl + '/inventory-service/api/inventory/venue';
  constructor(private http: HttpClient) {}

  public getAllVenues(): Observable<Array<Venue>> {
    return this.http.get<Array<Venue>>(this.apiUrl);
  }
  public getVenue(venueId: number): Observable<Venue> {
    return this.http.get<Venue>(this.apiUrl + '/' + venueId);
  }
  public saveVenue(venue: Venue): Observable<Venue> {
    return this.http.post<Venue>(this.apiUrl, venue);
  }
  public updateVenue(venue: Venue, venueId: number): Observable<Venue> {
    return this.http.put<Venue>(this.apiUrl + '/' + venueId, venue);
  }
  public deleteVenue(venueId: number): Observable<any> {
    return this.http.delete(this.apiUrl + '/' + venueId);
  }
  public toggleVenue(venue: Venue, venueId: number): Observable<Venue> {
    return this.http.patch<Venue>(this.apiUrl + '/' + venueId + '/toggle', venue);
  }
}
