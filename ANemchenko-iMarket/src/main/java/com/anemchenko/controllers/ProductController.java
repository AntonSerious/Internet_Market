package com.anemchenko.controllers;

import com.anemchenko.dto.ProductDto;
import com.anemchenko.model.Product;
import com.anemchenko.services.CategoryService;
import com.anemchenko.services.ProductService;
import com.anemchenko.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id){
        Product p = productService.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product not found: " + id));
        return new ProductDto(p);
    }


    @GetMapping
    public Page<Product> findAll (@RequestParam(name = "p", defaultValue = "1") int pageIndex){
        return productService.findPage(pageIndex - 1, 5);
    }

    @PostMapping
    public ProductDto createNewProduct(@RequestBody ProductDto newProduct){
        Product product = new Product();
        product.setPrice(newProduct.getPrice());
        product.setTitle(newProduct.getTitle());
        product.setCategory(categoryService.findByTitle(newProduct.getCategoryTitle()));
        return new ProductDto(productService.save(product));
    }
    @DeleteMapping
    public void deleteById(@RequestParam (name = "p") Long id){
        productService.deleteById(id);
    }

}
