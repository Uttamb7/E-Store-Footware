import {Component, OnInit} from '@angular/core';
import {Shoe} from "../ShoeInterface";
import {CartService} from "../services/cart.service";
import {ProductService} from "../services/product.service";
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../services/user.service";

@Component({
	selector: 'app-product-page',
	templateUrl: './product-page.component.html',
	styleUrls: ['./product-page.component.scss']
})
export class ProductPageComponent implements OnInit {

	shoe: Shoe = new Shoe();
	query: string = "";

	constructor(
		public productService: ProductService,
		public cartService: CartService,
		public route: ActivatedRoute,
		private userService: UserService
	) {
	}

	addToCart(item: Shoe) {
		this.cartService.addItem(item);
	}

	ngOnInit(): void {
		console.log("ng OnInit")
		const shoeId = Number(this.route.snapshot.paramMap.get('id'));
		this.productService.getShoeByID(shoeId).subscribe((data) => {
			this.shoe = data;
		});
	}

	isLoggedIn() {
		return this.userService.isLoggedIn();
	}
}
