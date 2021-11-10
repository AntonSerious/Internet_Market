package com.anemchenko;

import Dto.ProductDto;
import Exceptions.ResourceNotFoundException;
import com.anemchenko.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.core.SpringVersion;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public Page<Product> findAll (@RequestParam(name = "p", defaultValue = "1") int pageIndex){
        return productService.findPage(pageIndex - 1, 5);
    }
    @GetMapping("/{id}")
    public ProductDto findById (@PathVariable Long id){
        Product p = productService.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product not found: " + id));
        ProductDto pd = new ProductDto();
        pd.setId(p.getProductId());
        pd.setTitle(p.getTitle());
        pd.setPrice(p.getPrice());
        return pd;
    }


    @GetMapping("/greeting")
    public String greeting (){
        return "Hello";
    }

}
