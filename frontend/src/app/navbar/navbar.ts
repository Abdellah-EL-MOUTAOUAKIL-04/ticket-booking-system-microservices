import {Component, OnInit} from '@angular/core';
import { MenuItem } from 'primeng/api';
import { BadgeModule } from 'primeng/badge';
import { AvatarModule } from 'primeng/avatar';
import { InputTextModule } from 'primeng/inputtext';
import { CommonModule } from '@angular/common';
import {Menubar} from 'primeng/menubar';
import {ButtonModule} from 'primeng/button';
import {SplitButtonModule} from 'primeng/splitbutton';
import { StyleClassModule } from 'primeng/styleclass';
import {Router, RouterOutlet} from '@angular/router';

@Component({
  selector: 'app-navbar',
  imports: [Menubar, BadgeModule, AvatarModule, InputTextModule, CommonModule, ButtonModule, SplitButtonModule, StyleClassModule, RouterOutlet],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css'
})
export class Navbar implements OnInit{
  items: MenuItem[] | undefined;
  profileItems:MenuItem[] | undefined;

  constructor(private router:Router) {
  }

  ngOnInit() {
    this.items = [
      { label: 'Dashboard', icon: 'pi pi-home',routerLink:'/'},
      { label: 'Events', icon: 'pi pi-calendar', routerLink: 'events' },
      {label:'Venue',icon:'pi pi-warehouse', routerLink: 'venues' },
      {label:'Booking',icon:'pi pi-ticket', routerLink: 'bookings' }
    ];
    this.profileItems=[
      {label:'Profile',icon:'pi pi-user', routerLink: 'profile' },
      {label:'Logout',icon:'pi pi-sign-out', routerLink: 'logout' }
    ];
  }
}
