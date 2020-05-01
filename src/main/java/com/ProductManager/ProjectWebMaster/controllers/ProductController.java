package com.ProductManager.ProjectWebMaster.controllers;
/** In this controller we will GET list of products **/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import com.ProductManager.ProjectWebMaster.models.Products;
import com.ProductManager.ProjectWebMaster.interfaces.ProductsInterface;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ProductController {

    private List<Products>products = new ArrayList<>();
    @Autowired
    ProductsInterface productsInterface;
    @RequestMapping(method = RequestMethod.GET, value = "/products")
    public Iterable<Products> products(){
        //return all products.
        return productsInterface.findAll();
    }

    @PostMapping(value = "product")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String addProduct(@RequestBody Products p){
        this.products.add(p);
        productsInterface.save(p);
        return p.toString();
    }

    @DeleteMapping("/api/{id}")
    public void deleteProduct(@PathVariable("id") String id ){
        productsInterface.deleteById(id);
    }

}
