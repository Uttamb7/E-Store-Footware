import {ComponentFixture, TestBed} from '@angular/core/testing';

import {AllshoesComponent} from './allshoes.component';

describe('AllshoesComponent', () => {
	let component: AllshoesComponent;
	let fixture: ComponentFixture<AllshoesComponent>;

	beforeEach(async () => {
		await TestBed.configureTestingModule({
			declarations: [AllshoesComponent]
		})
			.compileComponents();

		fixture = TestBed.createComponent(AllshoesComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});
});
