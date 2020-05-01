import { Component, OnInit } from '@angular/core';

/**These component will display the list of products **/

import { Product } from './product';
import { ProductService } from '../product.service';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.scss']
})
export class ProductsComponent implements OnInit {
  data: Product[] = [];
  displayedColumns: string[] = ['prodName', 'prodDesc', 'prodPrice'];
  isLoadingResults = true;

  constructor(private productService: ProductService, private authService: AuthService, private router: Router) { }
  /** Get product from the data array **/
  getProducts(): void {
    this.productService.getProducts()
      .subscribe(products => {
        this.data = products;
        console.log(this.data);
        this.isLoadingResults = false;
      }, err => {
        console.log(err);
        this.isLoadingResults = false;
      });
  }

  ngOnInit(): void {
    this.getProducts();
  }
  /** Logout from the current session only **/
  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['login']);
  }
}
