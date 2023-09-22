import {Component} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {CheckoutService} from "../../services/checkout.service";
import {Router} from "@angular/router";
import {CartService} from "../../services/cart.service";
import {Creditcard} from "../../creditcard";

@Component({
	selector: 'app-checkout',
	templateUrl: './checkout.component.html',
	styleUrls: ['./checkout.component.scss']
})
export class CheckoutComponent {
	totalPrice: number = 0;

	checkoutForm = new FormGroup({
		cardHolderName: new FormControl(),
		cardNumber: new FormControl(),
		cardExpiration: new FormControl(),
		cardSecurityCode: new FormControl()
	});

	constructor(public checkoutService: CheckoutService, public cartService: CartService, private router: Router) {
	}

	ngOnInit() {
		let cart = this.cartService.getTotalCost().subscribe((totalPrice) => this.totalPrice = totalPrice);
	}

	onSubmit() {
		if (this.checkoutForm.valid) {
			let card = new Creditcard();
			card.cardHolder = this.checkoutForm.value.cardHolderName;
			card.cardNumber = this.checkoutForm.value.cardNumber;
			card.cardExpiration = this.checkoutForm.value.cardExpiration;
			card.cardSecurityCode = this.checkoutForm.value.cardSecurityCode;
		}
		this.checkoutService.checkout();

	}
}
