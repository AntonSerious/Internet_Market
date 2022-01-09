package com.anemchenko.controllers;

import com.anemchenko.dto.OrderDto;
import com.anemchenko.dto.ProductCommentDto;
import com.anemchenko.dto.ProductDto;
import com.anemchenko.dto.ProductInfoDto;
import com.anemchenko.model.Product;
import com.anemchenko.model.ProductComment;
import com.anemchenko.services.CategoryService;
import com.anemchenko.services.ProductService;
import com.anemchenko.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

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
    @GetMapping("/{id}/info")
    public ProductInfoDto findInfoById(@PathVariable Long id){
        Product p = productService.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product not found: " + id));
        return new ProductInfoDto(p);
    }

    @GetMapping
    public Page<Product> findAll (
            @RequestParam(name = "p", defaultValue = "1") int pageIndex,
            @RequestParam MultiValueMap<String, String> params
    ){
        return productService.findAll(pageIndex - 1, 5, params);
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

    @GetMapping("/{id}/comments")
    public List<ProductCommentDto> findCommentsById(@PathVariable Long id){
        return productService.findAllComments(id).stream().map(ProductCommentDto::new).collect(Collectors.toList());
    }
    @PostMapping("/{id}/comments")
    public void createNewComment(@RequestBody ProductCommentDto productCommentDto, Principal principal, @PathVariable Long id ){
       productService.createProductComment(productCommentDto, principal, id);
    }
    @GetMapping("/{id}/isCommentSent")
    public boolean isCommentSent(Principal principal, @PathVariable Long id){
        return productService.isCommentSent(principal.getName(), id);
    }
}
