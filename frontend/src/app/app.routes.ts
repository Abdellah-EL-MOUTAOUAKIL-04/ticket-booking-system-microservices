import { Routes } from '@angular/router';
import {Dashboard} from './dashboard/dashboard';
import {Events} from './events/events';
import {Venues} from './venues/venues';
import {Bookings} from './bookings/bookings';
import {Profile} from './profile/profile';

export const routes: Routes = [
  {path:"",component:Dashboard},
  {path:"events",component:Events},
  {path:"venues",component:Venues},
  {path:"bookings",component:Bookings},
  {path:"profile",component:Profile},
];
