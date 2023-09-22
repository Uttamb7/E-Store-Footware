import {Component, EventEmitter, Output} from '@angular/core';
import {Shoe} from "../../ShoeInterface";

@Component({
	selector: 'app-filter-menu',
	templateUrl: './filter-menu.component.html',
	styleUrls: ['./filter-menu.component.scss']
})
export class FilterMenuComponent {
	showGenderOptions: boolean = false;
	showBrandOptions: boolean = false;
	showSizingOptions: boolean = false;
	showColorOptions: boolean = false;
	showPriceOptions: boolean = false;

	@Output() filtering = new EventEmitter<{
		property: keyof Shoe,
		value: string
	}>();

	constructor() {
	}

	toggleGenderList() {
		this.showGenderOptions = !this.showGenderOptions;
	}

	toggleBrandList() {
		this.showBrandOptions = !this.showBrandOptions;
	}

	toggleSizingList() {
		this.showSizingOptions = !this.showSizingOptions;
	}

	toggleColorList() {
		this.showColorOptions = !this.showColorOptions;
	}

	togglePriceList() {
		this.showPriceOptions = !this.showPriceOptions;
	}

	applyFilter(property: string | null, value: string) {
		if (property == null) {
			this.showBrandOptions = false;
			this.showColorOptions = false;
			this.showGenderOptions = false;
			this.showPriceOptions = false;
			this.showSizingOptions = false;
		}

		this.filtering.emit({
			property: property as keyof Shoe,
			value: value
		});
	}
}
