import {Component, OnInit} from '@angular/core';
import {Shoe} from "../ShoeInterface";
import {ProductService} from "../services/product.service";
import {ToastrService} from "ngx-toastr";

@Component({
	selector: 'app-inventory-page',
	templateUrl: './inventory-page.component.html',
	styleUrls: ['./inventory-page.component.scss']
})
export class InventoryPageComponent implements OnInit {
	shoes: Shoe[] = [];
	editorMode: "HIDDEN" | "NEW" | "UPDATING" = "HIDDEN";
	shoeInEditor: Shoe = new Shoe();

	constructor(private productService: ProductService, private toastr: ToastrService) {
	}

	trackByIndex(index: number, obj: any): any {
		return index;
	}

	ngOnInit(): void {
		this.getAllShoes();
		this.shoeInEditor = new Shoe();
		this.editorMode = "HIDDEN";
	}

	getAllShoes(): void {
		this.productService.getAllShoes().subscribe(shoes => this.shoes = shoes);
	}

	openEditor(shoe: Shoe | null = null): void {
		if (!shoe) {
			this.editorMode = "NEW";
			this.shoeInEditor = new Shoe();
			return;
		}
		this.editorMode = "UPDATING";
		this.shoeInEditor = shoe;
	}

	submitEditor(): void {
		if (this.editorMode === "UPDATING") {
			this.updateShoe(this.shoeInEditor.id, this.shoeInEditor.brand, this.shoeInEditor.style, this.shoeInEditor.sizing, this.shoeInEditor.size, this.shoeInEditor.price, this.shoeInEditor.material, this.shoeInEditor.color);
			return;
		} else {
			this.createShoe(this.shoeInEditor.id, this.shoeInEditor.brand, this.shoeInEditor.style, this.shoeInEditor.sizing, this.shoeInEditor.size, this.shoeInEditor.price, this.shoeInEditor.material, this.shoeInEditor.color);
		}
		this.editorMode = "HIDDEN";
	}

	createShoe(id: number, brand: string, style: string, sizing: string, size: number, price: number, material: string, color: string): void {
		brand = brand.trim();
		style = style.trim();
		material = material.trim();
		color = color.trim();
		if (!brand || !style || !material || !color) {
			return;
		}
		this.productService.addShoe({id, brand, style, sizing, size, price, material, color} as Shoe)
			.subscribe(shoe => {
				this.shoeInEditor = new Shoe();
				this.ngOnInit();
			});
	}

	updateShoe(id: number, brand: string, style: string, sizing: string, size: number, price: number, material: string, color: string): void {
		brand = brand.trim();
		style = style.trim();
		material = material.trim();
		color = color.trim();
		if (!brand || !style || !material || !color) {
			return;
		}
		this.productService.updateShoe({id, brand, style, sizing, size, price, material, color} as Shoe)
			.subscribe(() => {
				this.shoeInEditor = new Shoe();
				this.ngOnInit();
			});
	}

	deleteShoe(shoe: Shoe): void {
		this.productService.deleteShoe(shoe.id).subscribe(() => {
			this.ngOnInit();
		});
	}
}
