package com.ProductManager.ProjectWebMaster.interfaces;
import com.ProductManager.ProjectWebMaster.models.Products;
import org.springframework.data.repository.CrudRepository;

public interface ProductsInterface extends CrudRepository<Products, String>{
}
