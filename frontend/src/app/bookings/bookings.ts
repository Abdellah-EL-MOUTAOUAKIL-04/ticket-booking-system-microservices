import { Component, OnInit, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { SkeletonModule } from 'primeng/skeleton';
import { RouterModule } from '@angular/router';
import { Table, TableModule } from 'primeng/table';
import { TagModule } from 'primeng/tag';
import { InputTextModule } from 'primeng/inputtext';
import { BookingService } from '../../services/booking-service';
import { finalize } from 'rxjs';

@Component({
  selector: 'app-bookings',
  standalone: true,
  imports: [
    CommonModule,
    CardModule,
    ButtonModule,
    SkeletonModule,
    RouterModule,
    TableModule,
    TagModule,
    InputTextModule,
  ],
  templateUrl: './bookings.html',
  styleUrl: './bookings.css',
})
export class Bookings implements OnInit {
  @ViewChild('dt') table!: Table;

  bookings: any[] = [];
  isLoading: boolean = false;

  constructor(private bookingService: BookingService) {}

  ngOnInit(): void {
    this.loadBookings();
  }

  loadBookings() {
    this.isLoading = true;
    this.bookingService
      .getBookings()
      .pipe(finalize(() => (this.isLoading = false)))
      .subscribe({
        next: (data) => {
          this.bookings = data;
          console.log('Loaded bookings:', data);
        },
        error: (err) => {
          console.error('Error loading bookings:', err);
        },
      });
  }
}
