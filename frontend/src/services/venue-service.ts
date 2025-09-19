import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../environments/environment';
import {Venue} from '../app/model/venue.model';

@Injectable({
  providedIn: 'root'
})
export class VenueService {
  constructor(private http:HttpClient) {
  }

  public getAllVenues():Observable<Array<Venue>>{
    return this.http.get<Array<Venue>>(environment.apiUrl+'/venue');
  }
  public getVenue(venueId:number):Observable<Venue>{
    return this.http.get<Venue>(environment.apiUrl+'/venue/'+venueId);
  }
  public saveVenue(venue:Venue):Observable<Venue>{
    return this.http.post<Venue>(environment.apiUrl+"/venue",venue);
  }
  public updateVenue(venue:Venue,venueId:number):Observable<Venue>{
    return this.http.put<Venue>(environment.apiUrl+"/venue/"+venueId,venue);
  }
  public deleteVenue(venueId:number):Observable<any>{
    return this.http.delete(environment.apiUrl+"/venue/"+venueId);
  }
  public toggleVenue(venue:Venue,venueId:number):Observable<Venue>{
    return this.http.patch<Venue>(environment.apiUrl+"/venue/"+venueId+"/toggle",venue);
  }
}
