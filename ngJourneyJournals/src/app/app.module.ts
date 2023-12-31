import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeComponent } from './components/home/home.component';
import { LogoutComponent } from './components/logout/logout.component';
import { NotfoundComponent } from './components/notfound/notfound.component';
import { UserHomeComponent } from './components/user-home/user-home.component';
import { CreateAccountComponent } from './components/create-account/create-account.component';
import { BlogComponent } from './components/blog/blog.component';
import { DestinationComponent } from './components/destination/destination.component';
import { GalleryComponent } from './components/gallery/gallery.component';
import { BlogListComponent } from './components/blog-list/blog-list.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { PlaceComponent } from './components/place/place.component';
import { PlaceListComponent } from './components/place-list/place-list.component';
import { DestinationListComponent } from './components/destination-list/destination-list.component';



@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    LogoutComponent,
    NotfoundComponent,
    UserHomeComponent,
    CreateAccountComponent,
    BlogComponent,
    DestinationComponent,
    GalleryComponent,
    BlogListComponent,
    PlaceComponent,
    PlaceListComponent,
    DestinationListComponent,


  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
})
export class AppModule { }
