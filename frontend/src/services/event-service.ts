import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Event } from '../app/model/event.model';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class EventService {
  apiUrl: string = environment.apiUrl + '/inventory-service/api/inventory/events';
  constructor(private http: HttpClient) {}
  public getAllEvents(): Observable<Array<Event>> {
    return this.http.get<Array<Event>>(this.apiUrl);
  }
  public getEvent(eventId: number): Observable<Array<Event>> {
    return this.http.get<Array<Event>>(this.apiUrl + '/' + eventId);
  }
  public saveEvent(event: Event): Observable<Event> {
    return this.http.post<Event>(this.apiUrl, event);
  }
  public updateEvent(event: Event, eventId: number): Observable<Event> {
    return this.http.put<Event>(this.apiUrl + '/' + eventId, event);
  }
  public deleteEvent(eventId: number): Observable<any> {
    return this.http.delete(this.apiUrl + '/' + eventId);
  }
}
