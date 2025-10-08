import { Component, OnInit } from '@angular/core';
import { Event } from '../model/event.model';
import { EventService } from '../../services/event-service';
import { finalize } from 'rxjs';
import { DataView } from 'primeng/dataview';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { CommonModule, NgForOf } from '@angular/common';
import { Skeleton } from 'primeng/skeleton';
import { Button } from 'primeng/button';
import { Tag } from 'primeng/tag';
import { ToggleButtonModule } from 'primeng/togglebutton';
import { InputNumber, InputNumberModule } from 'primeng/inputnumber';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService, MessageService } from 'primeng/api';
import { CardModule } from 'primeng/card';
import { SelectButtonModule } from 'primeng/selectbutton';
import { SelectModule } from 'primeng/select';
import { TooltipModule } from 'primeng/tooltip';

@Component({
  selector: 'app-events',
  standalone: true,
  imports: [
    CommonModule,
    DataView,
    CardModule,
    FormsModule,
    Skeleton,
    Button,
    Tag,
    NgForOf,
    ToggleButtonModule,
    ReactiveFormsModule,
    DialogModule,
    InputTextModule,
    InputNumberModule,
    ConfirmDialogModule,
    SelectButtonModule,
    SelectModule,
    TooltipModule,
  ],
  providers: [ConfirmationService, MessageService],
  templateUrl: './events.html',
  styleUrl: './events.css',
})
export class Events implements OnInit {
  events: Array<Event> = [];
  isLoading: boolean = false;
  newEventForm: FormGroup;
  showDialog: boolean = false;
  isEditMode: boolean = false;

  constructor(
    private eventService: EventService,
    private confirmationService: ConfirmationService,
    private messageService: MessageService,
    private fb: FormBuilder
  ) {
    this.newEventForm = this.fb.group({
      id: [''],
      name: ['', Validators.required],
      leftCapacity: [0, Validators.required],
      ticketPrice: [0, Validators.required],
      venue: [null, Validators.required],
    });
  }

  ngOnInit(): void {
    this.getEvents();
  }

  getEvents() {
    this.isLoading = true;
    this.eventService
      .getAllEvents()
      .pipe(finalize(() => (this.isLoading = false)))
      .subscribe({
        next: (data) => {
          this.events = data;
          console.log(data);
        },
        error: (err) => console.log(err),
      });
  }

  editEvent(event: Event) {
    this.isEditMode = true;
    this.showDialog = true;
    this.newEventForm.patchValue({
      id: event.id,
      name: event.name,
      leftCapacity: event.leftCapacity,
      ticketPrice: event.ticketPrice,
      venue: event.venue,
    });
  }

  handleUpdateEvent() {
    if (this.newEventForm.valid) {
      const updatedEvent: Event = this.newEventForm.value;
      this.eventService.updateEvent(updatedEvent, updatedEvent.id).subscribe({
        next: (data) => {
          const index = this.events.findIndex((v) => v.id === data.id);
          if (index !== -1) {
            this.events[index] = data;
          }
          this.showDialog = false;
          this.newEventForm.reset();
        },
        error: (err) => console.log(err),
      });
    }
  }

  confirmDelete(event: Event) {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete this event?',
      header: 'Confirm Delete',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.eventService.deleteEvent(event.id).subscribe({
          next: () => {
            this.events = this.events.filter((v) => v.id !== event.id);
            this.messageService.add({
              severity: 'success',
              summary: 'Successful',
              detail: 'Event Deleted',
              life: 3000,
            });
          },
          error: (err) => console.log(err),
        });
      },
    });
  }

  openNewEvent() {
    this.isEditMode = false;
    this.showDialog = true;
    this.newEventForm.reset();
  }

  handleCreateEvent() {
    if (this.newEventForm.valid) {
      const newEvent: Event = this.newEventForm.value;
      this.eventService.saveEvent(newEvent).subscribe({
        next: (data) => {
          this.events.push(data);
          this.showDialog = false;
          this.newEventForm.reset();
        },
        error: (err) => console.log(err),
      });
    }
  }

  handleForm() {
    this.isEditMode ? this.handleUpdateEvent() : this.handleCreateEvent();
  }

  handleCloseFormDialog() {
    if (this.isEditMode) {
      this.isEditMode = false;
    }
    this.showDialog = false;
    this.newEventForm.reset();
  }

  bookEvent(event: Event) {
    if (event.leftCapacity > 0) {
      this.confirmationService.confirm({
        message: 'Are you sure you want to book a ticket for this event?',
        header: 'Confirm Booking',
        icon: 'pi pi-ticket',
        accept: () => {
          // Here you would typically call a booking service
          // For now we'll just show a success message
          this.messageService.add({
            severity: 'success',
            summary: 'Booking Confirmed',
            detail: `Successfully booked a ticket for ${event.name}`,
            life: 3000,
          });
        },
      });
    }
  }
}
