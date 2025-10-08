import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Event } from '../app/model/event.model';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class EventService {
  constructor(private http: HttpClient) {}
  public getAllEvents(): Observable<Array<Event>> {
    return this.http.get<Array<Event>>(environment.apiUrl + '/events');
  }
  public getEvent(eventId: number): Observable<Array<Event>> {
    return this.http.get<Array<Event>>(environment.apiUrl + '/events/' + eventId);
  }
  public saveEvent(event: Event): Observable<Event> {
    return this.http.post<Event>(environment.apiUrl + '/events', event);
  }
  public updateEvent(event: Event, eventId: number): Observable<Event> {
    return this.http.put<Event>(environment.apiUrl + '/events/' + eventId, event);
  }
  public deleteEvent(eventId: number): Observable<any> {
    return this.http.delete(environment.apiUrl + '/events/' + eventId);
  }
}
