import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {ProductService} from "../../services/product.service";
import {Observable} from "rxjs";
import {Shoe} from "../../ShoeInterface";


@Component({
	selector: 'app-searchbar',
	templateUrl: './searchbar.component.html',
	styleUrls: ['./searchbar.component.scss']
})
export class SearchbarComponent {
	searchTerm: string = '';
	searchResults$: Observable<Shoe[]> | undefined;

	constructor(private productService: ProductService, private router: Router) {
	}

	searchShoes(term: string): void {
		this.router.navigate(['/browse'], {queryParams: {query: term}}).then();
	}
}
