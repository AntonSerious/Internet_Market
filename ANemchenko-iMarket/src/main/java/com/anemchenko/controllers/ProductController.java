package com.anemchenko.controllers;

import com.anemchenko.model.Product;
import com.anemchenko.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //http://localhost:8189/iMarket
    @GetMapping
    public String showMainPage(Model model){
        model.addAttribute("items", productService.findAll());
        return "index";
    }

    @GetMapping("/products/add")
    public String showAddProductForm(){
        return "add_product_form";
    }

    @PostMapping("/products/add")
    public String saveNewProduct(@RequestParam Long id,@RequestParam String title, @RequestParam int price){
        productService.saveNewProduct(id, title, price);

        return "redirect:/";
    }
    @GetMapping("/products/{id}")
    public String showProductInfo(Model model, @PathVariable Long id){
        model.addAttribute("product", productService.findById(id));
        model.addAttribute("customersInfo", productService.findAllCustomersByProductId(id));
        return "product_info";
    }

    @GetMapping("/products/delete")
    public String showDeleteProductForm(){
        return "delete_product_form";
    }

    @PostMapping("/products/delete")
    public String DeleteProduct(@RequestParam Long id){
        productService.deleteProductById(id);
        return "redirect:/";
    }
    @GetMapping("/products/find_by_min_price")
    @ResponseBody
    public List<Product> findByMinPrice(@RequestParam(name = "min") int min){
        return productService.findAllProductsByPriceGreaterOrEqualThanMinPrice(min);
    }

    @GetMapping("/products/find_by_max_price")
    @ResponseBody
    public List<Product> findByMaxPrice(@RequestParam(name = "max") int max){
        return productService.findAllProductsByPriceLessOrEqualThanMaxPrice(max);
    }

    @GetMapping("/products/find_by_price_between_min_and_max")
    @ResponseBody
    public List<Product> findByPriceBetweenMinAndMax(@RequestParam(name = "min") int min, @RequestParam(name = "max") int max ){
        return productService.findAllProductsByPriceBetweenMinAndMaxPrice(min, max);
    }



}
