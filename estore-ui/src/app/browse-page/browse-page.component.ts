import {Component, OnInit} from '@angular/core';
import {ProductService} from "../services/product.service";
import {Shoe} from "../ShoeInterface";
import {Router} from "@angular/router";
import {CartService} from "../services/cart.service";
import {UserService} from "../services/user.service";

@Component({
	selector: 'app-browse-page',
	templateUrl: './browse-page.component.html',
	styleUrls: ['./browse-page.component.scss']
})
export class BrowsePageComponent implements OnInit {
	shoes: Shoe[] = [];
	shoesOriginal: Shoe[] = [];
	filteredProperties: { [key: string]: any } = {};
	query: string = "";

	constructor(private productService: ProductService, private router: Router, private cartService: CartService, private userService: UserService) {
		router.events.subscribe(() => {
			this.ngOnInit();
		})
	}

	ngOnInit(): void {
		// Get query string from url called 'query'
		const urlParams = new URLSearchParams(window.location.search);
		this.query = urlParams.get('query')?.toString() || "";

		if (this.query != "" && this.query != "All") {
			this.productService.searchShoes(this.query).subscribe((data) => {
				this.shoes = data;
				this.shoesOriginal = data;
			});
		} else {
			this.productService.getAllShoes().subscribe((data) => {
				this.shoes = data;
				this.shoesOriginal = data;
			});
		}
	}

	addToCart(event: Event, shoe: Shoe) {
		event.stopPropagation();
		console.log("ADDED TO CART")
		this.cartService.addItem(shoe);
	}

	isLoggedIn() {
		return this.userService.isLoggedIn();
	}

	ingestFilter(filter: { property: keyof Shoe, value: string }) {
		this.applyFilter(filter.property, filter.value);
	}

	applyFilter(property: keyof Shoe | null, value: string) {
		if (property == null) {
			this.shoes = this.shoesOriginal;
			this.filteredProperties = {};
			return;
		}

		this.filteredProperties[property] = value;
		this.shoes = this.shoesOriginal.filter((shoe) => {
			for (const key in this.filteredProperties) {
				// special checking for price
				if (key == "price") {
					let low = this.filteredProperties[key].split("-")[0];
					let high = this.filteredProperties[key].split("-")[1];
					if (shoe[key as keyof Shoe] > high || shoe[key as keyof Shoe] < low) {
						return false;
					}
					continue;
				}

				if (shoe[key as keyof Shoe] != this.filteredProperties[key]) {
					return false;
				}
			}
			return true;
		});
	}

	routeTo(shoeId: number) {
		this.router.navigate(["/product", shoeId]).then();
	}
}
