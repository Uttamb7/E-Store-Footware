import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {BrowsePageComponent} from "./browse-page/browse-page.component";
import {ProductPageComponent} from "./product-page/product-page.component";
import {LoginComponent} from "./login/login.component";
import {InventoryPageComponent} from "./inventory-page/inventory-page.component";
import {ShoppingCartComponent} from "./shopping-cart/shopping-cart.component";
import {CheckoutComponent} from "./shopping-cart/checkout/checkout.component";
import {SignupComponent} from "./login/signup/signup.component";
import {OrdersComponent} from "./orders/orders.component";

const routes: Routes = [
	{path: '', redirectTo: '/home', pathMatch: 'full'},
	{path: 'home', component: HomeComponent},
	{path: 'browse', component: BrowsePageComponent},
	{path: 'product/:id', component: ProductPageComponent},
	{path: 'login', component: LoginComponent},
	{path: 'inventory', component: InventoryPageComponent},
	{path: 'cart', component: ShoppingCartComponent},
	{path: 'checkout', component: CheckoutComponent},
	{path: 'signup', component: SignupComponent},
	{path: 'orders', component: OrdersComponent}
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})
export class AppRoutingModule {
}
