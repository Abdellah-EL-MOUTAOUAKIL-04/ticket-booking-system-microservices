import { Component, OnInit } from '@angular/core';
import { Venue } from '../model/venue.model';
import { VenueService } from '../../services/venue-service';
import { finalize } from 'rxjs';
import { DataView } from 'primeng/dataview';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { SelectButtonModule } from 'primeng/selectbutton';
import { NgClass, NgForOf } from '@angular/common';
import { Skeleton } from 'primeng/skeleton';
import { Button } from 'primeng/button';
import { Tag } from 'primeng/tag';
import { Splitter } from 'primeng/splitter';
import { ToggleSwitch } from 'primeng/toggleswitch';
import { ToggleButtonModule } from 'primeng/togglebutton';
import { InputNumber, InputNumberModule } from 'primeng/inputnumber';
import { SelectModule } from 'primeng/select';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService, MessageService } from 'primeng/api';

@Component({
  selector: 'app-venues',
  imports: [
    DataView,
    FormsModule,
    SelectButtonModule,
    NgClass,
    Skeleton,
    Button,
    Tag,
    NgForOf,
    Splitter,
    ToggleSwitch,
    ToggleButtonModule,
    ReactiveFormsModule,
    InputNumber,
    SelectModule,
    DialogModule,
    InputTextModule,
    InputNumberModule,
    ConfirmDialogModule,
  ],
  providers: [ConfirmationService, MessageService],
  templateUrl: './venues.html',
  styleUrl: './venues.css',
})
export class Venues implements OnInit {
  venues: Array<Venue> = [];
  isLoading: boolean = false;
  layout: 'grid' | 'list' = 'grid';
  layoutOptions = ['list', 'grid'];
  filter: { name: string | null; active: boolean | null; minCapacity: number | null } = {
    name: null,
    active: null,
    minCapacity: null,
  };
  newVenueForm: FormGroup;
  showDialog: boolean = false;
  isEditMode: boolean = false;

  constructor(
    public venueService: VenueService,
    private confirmationService: ConfirmationService,
    private messageService: MessageService,
    private fb: FormBuilder
  ) {
    this.newVenueForm = this.fb.group({
      name: ['', Validators.required],
      totalCapacity: [0, Validators.required],
      address: ['', Validators.required],
      active: [true, Validators.required],
    });
  }

  ngOnInit(): void {
    this.getVenues();
  }

  getVenues() {
    this.isLoading = true;
    this.venueService
      .getAllVenues()
      .pipe(finalize(() => (this.isLoading = false)))
      .subscribe({
        next: (data) => {
          this.venues = data;
          console.log(this.venues);
        },
        error: (err) => console.log(err),
      });
  }
  toggle(venue: Venue) {
    let index = this.venues.indexOf(venue);
    this.venueService.toggleVenue(venue, venue.id).subscribe({
      next: (data) => {
        this.venues[index] = data;
      },
      error: (err) => console.log(err),
    });
  }

  editVenue(venue: Venue) {
    this.isEditMode = true;
    this.showDialog = true;
    this.newVenueForm.patchValue({
      name: venue.name,
      totalCapacity: venue.totalCapacity,
      address: venue.address,
      active: venue.active,
    });
  }
  handleUpdateVenue() {
    if (this.newVenueForm.valid) {
      const updatedVenue: Venue = {
        id: 0,
        name: this.newVenueForm.value.name,
        totalCapacity: this.newVenueForm.value.totalCapacity,
        address: this.newVenueForm.value.address,
        active: this.newVenueForm.value.active,
      };
      console.log('updatedVenue', updatedVenue);
      this.venueService.updateVenue(updatedVenue, updatedVenue.id).subscribe({
        next: (data) => {
          const index = this.venues.findIndex((v) => v.id === data.id);
          if (index !== -1) {
            this.venues[index] = data;
          }
          this.showDialog = false;
          this.newVenueForm.reset();
        },
        error: (err) => console.log(err),
      });
    }
  }

  confirmDelete(venue: Venue) {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete this venue?',
      header: 'Confirm Delete',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.venueService.deleteVenue(venue.id).subscribe({
          next: () => {
            this.venues = this.venues.filter((v) => v.id !== venue.id);
            this.messageService.add({
              severity: 'success',
              summary: 'Successful',
              detail: 'Venue Deleted',
              life: 3000,
            });
          },
          error: (err) => console.log(err),
        });
      },
    });
  }

  openNewVenue() {
    this.showDialog = true;
  }
  handleCreateVenue() {
    if (this.newVenueForm.valid) {
      const newVenue: Venue = {
        id: 0, // ID will be set by the backend
        name: this.newVenueForm.value.name,
        totalCapacity: this.newVenueForm.value.totalCapacity,
        address: this.newVenueForm.value.address,
        active: this.newVenueForm.value.active,
      };
      console.log('newVenue', newVenue);
      this.venueService.saveVenue(newVenue).subscribe({
        next: (data) => {
          this.venues.push(data);
          this.showDialog = false;
          this.newVenueForm.reset();
        },
        error: (err) => console.log(err),
      });
    }
  }

  applyFilters() {}

  resetFilters() {}
}
