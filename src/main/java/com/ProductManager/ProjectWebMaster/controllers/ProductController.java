package com.ProductManager.ProjectWebMaster.controllers;
/** In this controller we will GET list of products **/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ProductManager.ProjectWebMaster.models.Products;
import com.ProductManager.ProjectWebMaster.interfaces.ProductsInterface;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    ProductsInterface productsInterface;

    @RequestMapping(method = RequestMethod.GET, value = "/products")
    public Iterable<Products> products(){

        //return all products.
        return productsInterface.findAll();
    }
}
