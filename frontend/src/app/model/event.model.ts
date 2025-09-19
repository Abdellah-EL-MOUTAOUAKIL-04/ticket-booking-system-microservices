import {Venue} from './venue.model';

export interface Event {
  id:           number;
  name:         string;
  leftCapacity: number;
  ticketPrice:  number;
  venue:        Venue;
}
