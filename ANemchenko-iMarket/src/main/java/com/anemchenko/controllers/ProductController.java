package com.anemchenko.controllers;

import com.anemchenko.model.Product;
import com.anemchenko.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

//    @Autowired
//    public ProductController(ProductService productService) {
//        this.productService = productService;
//    }
//
//    //http://localhost:8189/iMarket
//    @GetMapping
//    public String showMainPage(Model model){
//        model.addAttribute("items", productService.findAll());
//        return "index";
//    }
//
//    @GetMapping("/products/add")
//    public String showAddProductForm(){
//        return "add_product_form";
//    }
//
//    @PostMapping("/products/add")
//    public String saveNewProduct(@RequestParam Long id,@RequestParam String title, @RequestParam int price){
//        productService.saveNewProduct(id, title, price);
//
//        return "redirect:/";
//    }
//    @GetMapping("/products/{id}")
//    public String showProductInfo(Model model, @PathVariable Long id){
//        model.addAttribute("product", productService.findById(id));
//        model.addAttribute("customersInfo", productService.findAllCustomersByProductId(id));
//        return "product_info";
//    }
//

    @GetMapping("/products/delete")
    public void showDeleteProductForm(@RequestParam(name = "p") Long productId){
         productService.deleteProductById(productId);
    }
//
//    @PostMapping("/products/delete")
//    public String DeleteProduct(@RequestParam Long id){
//        productService.deleteProductById(id);
//        return "redirect:/";
//    }
//    @GetMapping("/products/find_by_min_price")
//    @ResponseBody
//    public List<Product> findByMinPrice(@RequestParam(name = "min") int min){
//        return productService.findAllProductsByPriceGreaterOrEqualThanMinPrice(min);
//    }
//
//    @GetMapping("/products/find_by_max_price")
//    @ResponseBody
//    public List<Product> findByMaxPrice(@RequestParam(name = "max") int max){
//        return productService.findAllProductsByPriceLessOrEqualThanMaxPrice(max);
//    }
//
//    @GetMapping("/products/find_by_price_between_min_and_max")
//    @ResponseBody
//    public List<Product> findByPriceBetweenMinAndMax(@RequestParam(name = "min") int min, @RequestParam(name = "max") int max ){
//        return productService.findAllProductsByPriceBetweenMinAndMaxPrice(min, max);
//    }
    @GetMapping("/products/{id}")
    public Product findById(@PathVariable Long id){
        return productService.findById(id);
    }

    @GetMapping("/products")
    public List<Product> findAll (){
        return productService.findAll();
    }

    @GetMapping("/products_page")
    public Page<Product> findAll (@RequestParam(name = "p") int pageIndex){
        return productService.findPage(pageIndex - 1, 5);
    }

}
