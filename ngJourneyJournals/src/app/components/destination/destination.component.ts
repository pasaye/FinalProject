import { Country } from './../../models/country';
import { CountryService } from 'src/app/services/country.service';
import { ContinentService } from './../../services/continent.service';
import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { Continent } from 'src/app/models/continent';
import { Destination } from 'src/app/models/destination';
import { DestinationService } from 'src/app/services/destination.service';
import { Place } from 'src/app/models/place';
import { PlaceService } from 'src/app/services/place.service';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-destination',
  templateUrl: './destination.component.html',
  styleUrls: ['./destination.component.css']
})
export class DestinationComponent implements OnInit {

  newDes: Destination = new Destination();
  editDes: Destination | null = null;
  countries: Country[] = [];
  continents: Continent[] = [];
  destinations: Destination[] = [];
  places: Place[] = [];
  countryId: any;
  selected: Destination | null = null;
  selectedCountry: Country | null = null;
  showingForm: boolean = false;
  user: User | null = null;

  constructor(
    private continentServ: ContinentService,
    private countryServ: CountryService,
    private auth: AuthService,
    private destinationServ: DestinationService,
    private placeServ: PlaceService,
  ) { }

  getUserName() {
    this.auth.getLoggedInUser().subscribe({
      next: (user) => {
        this.user = user;
      },
      error: (err) => {
        console.error(err);
      },
    });
  }

  ngOnInit() {
    this.loadContinents();
    this.loadCountries();
    this.loadDestinations();
    this.loadPlaces();
    this.getUserName();
  }

  collapsedStates: { [key: string]: boolean } = {};

  generateContinentId(continent: Continent): string {
    return continent.id.toString();
  }

  isCollapsed(continentId: string): boolean {
    return this.collapsedStates[continentId];
  }

  toggleCollapse(continentId: string): void {
    this.collapsedStates[continentId] = !this.isCollapsed(continentId);
  }



  checkedLogin() {
    return this.auth.checkLogin();
  }

  loadContinents() {
    this.continentServ.indexAll().subscribe({
      next: (continentList) => {
        console.log(this.continents)
        this.continents = continentList;
      },
      error: (err) => {
        console.log(err);
      }
    })
  }

  loadCountries() {
    this.countryServ.indexAll().subscribe({
      next: (countryList) => {
        this.countries = countryList;
      },
      error: (err) => {
        console.log(err);
      }
    })

  }

  loadDestinations() {
    this.destinationServ.indexByUser().subscribe({
      next: (destinationList) => {
        this.destinations = destinationList;
      },
      error: (err) => {
        console.log(err);
      }
    })

  }
  loadPlaces() {
    this.placeServ.indexByUser().subscribe({
      next: (placeList) => {
        this.places = placeList;
      },
      error: (err) => {
        console.log(err);
      }
    })

  }

  showAddForm(id: number) {
    this.countryId = id;
    this.showingForm = !this.showingForm;
  }

  diplayCountry(country: Country): void {
    this.selectedCountry = country;
  }

  displayList(): void {
    this.selectedCountry = null;
  }

  displayUpdateForm(destination: Destination): void {
    this.selected = destination;
  }

  opactiyGetter() {
    if (this.selected) {
      return 'low'
    }
    else {
      return 'full'
    }
  }
  addDestination(destination: Destination) {
    return this.destinationServ.create(destination, this.countryId).subscribe({
      next: (createdDes) => {
        this.newDes = new Destination();
        this.ngOnInit();
      },
      error: (err) => {
        console.log(err);
      }
    });
  }

  updateDestination(id: number, editedDes: Destination) {
    return this.destinationServ.update(id, editedDes).subscribe({
      next: (updatedDes) => {
        this.editDes = updatedDes;
      },
      error: (err) => {
        console.error(err);
      }
    })

  }

  deleteDestination(id: number) {
    return this.destinationServ.destroy(id).subscribe({
      next: () => {
        this.ngOnInit();
      },
      error: (err) => {
        console.log(err);
      }
    })
  }

  destinationsCreatedByUser(destination: Destination) {
    return this.user?.role === 'admin' || this.user?.destinationCreated.includes(destination);

  }

}
