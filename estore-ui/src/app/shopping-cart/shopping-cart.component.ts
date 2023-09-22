import {Component, OnInit} from '@angular/core';
import {CartService} from "../services/cart.service";
import {Shoe} from "../ShoeInterface";

@Component({
	selector: 'app-shopping-cart',
	templateUrl: './shopping-cart.component.html',
	styleUrls: ['./shopping-cart.component.scss']
})
export class ShoppingCartComponent implements OnInit {

	totalPrice: number = 0;
	items: Shoe[] = [];

	constructor(public cartService: CartService) {
	}

	ngOnInit() {
		this.cartService.getTotalCost().subscribe((totalPrice) => {
			this.totalPrice = totalPrice;
		});
		this.cartService.getItems().subscribe((items) => {
			this.items = items;
		});
	}

	async removeItem(shoe: Shoe) {
		this.cartService.removeItem(shoe);

		await this.delay(150);

		// re-render the component
		this.ngOnInit();
	}

	private delay(ms: number) {
		return new Promise(resolve => setTimeout(resolve, ms));
	}

}
