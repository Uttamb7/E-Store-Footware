import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './main-app/app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatIconModule} from "@angular/material/icon";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatButtonModule} from "@angular/material/button";
import {HomeComponent} from './home/home.component';
import {NavbarComponent} from './navbar/navbar.component';
import {MatInputModule} from "@angular/material/input";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {SearchbarComponent} from './navbar/searchbar/searchbar.component';
import {MatButtonToggleModule} from "@angular/material/button-toggle";
import {BrandBarComponent} from './home/brand-bar/brand-bar.component';
import {FeaturedComponent} from './home/featured/featured.component';
import {BrowsePageComponent} from './browse-page/browse-page.component';
import {FilterMenuComponent} from './browse-page/filter-menu/filter-menu.component';
import {ProductPageComponent} from './product-page/product-page.component';
import {LoginComponent} from './login/login.component';
import {InventoryPageComponent} from './inventory-page/inventory-page.component';
import {HttpClientModule} from "@angular/common/http";
import {MatListModule} from "@angular/material/list";
import {MatSelectModule} from "@angular/material/select";
import {ShoppingCartComponent} from './shopping-cart/shopping-cart.component';
import {MatGridListModule} from "@angular/material/grid-list";
import {MatTooltipModule} from "@angular/material/tooltip";
import {CheckoutComponent} from './shopping-cart/checkout/checkout.component';
import {ToastrModule} from 'ngx-toastr';
import {MatRadioModule} from "@angular/material/radio";
import {AllshoesComponent} from './home/allshoes/allshoes.component';
import {SignupComponent} from './login/signup/signup.component';
import {OrdersComponent} from './orders/orders.component';


@NgModule({
	declarations: [
		AppComponent,
		HomeComponent,
		NavbarComponent,
		SearchbarComponent,
		BrandBarComponent,
		FeaturedComponent,
		BrowsePageComponent,
		FilterMenuComponent,
		ProductPageComponent,
		LoginComponent,
		InventoryPageComponent,
		ShoppingCartComponent,
		CheckoutComponent,
		AllshoesComponent,
		SignupComponent,
		OrdersComponent,
	],

	imports: [
		BrowserModule,
		AppRoutingModule,
		BrowserAnimationsModule,
		MatIconModule,
		MatToolbarModule,
		MatButtonModule,
		MatInputModule,
		FormsModule,
		MatButtonToggleModule,
		MatIconModule,
		HttpClientModule,
		MatListModule,
		MatSelectModule,
		ReactiveFormsModule,
		MatGridListModule,
		MatTooltipModule,
		ToastrModule.forRoot({
			positionClass: 'toast-bottom-right',
		}),
		MatRadioModule
	],
	providers: [],
	bootstrap: [AppComponent]
})
export class AppModule {
}
