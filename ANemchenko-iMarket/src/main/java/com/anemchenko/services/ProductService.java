package com.anemchenko.services;


import com.anemchenko.model.Customer_x_Product;
import com.anemchenko.model.Product;
import com.anemchenko.repositories.Customer_x_ProductRepository;
import com.anemchenko.repositories.ProductRepository;
import com.anemchenko.soap.products.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final Customer_x_ProductRepository customer_x_productRepository;


    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }
    public Product save(Product newProduct){
        return productRepository.save(newProduct);
    }


    public List<Customer_x_Product> findAllCustomersByProductId(Long id){
       return customer_x_productRepository.findByProduct_productId(id);
    }

    public List<Product> findAllProductsByPriceGreaterOrEqualThanMinPrice(int minPrice){
        return productRepository.findAllByPriceGreaterThanEqual(minPrice);
    }
    public List<Product> findAllProductsByPriceLessOrEqualThanMaxPrice(int maxPrice){
        return productRepository.findAllByPriceLessThanEqual(maxPrice);
    }
    public List<Product> findAllProductsByPriceBetweenMinAndMaxPrice(int minPrice, int maxPrice){
        return productRepository.findAllByPriceBetween(minPrice, maxPrice);
    }

    public Page<Product> findPage(int pageIndex, int pageSize){
        return productRepository.findAll(PageRequest.of(pageIndex, pageSize));
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public static final Function<Product, ProductDTO> functionEntityToSoap = pe -> {
        ProductDTO p = new ProductDTO();
        p.setId(pe.getProductId());
        p.setProductTitle(pe.getTitle());
        p.setPrice(pe.getPrice());
        p.setCategoryId(pe.getCategory().getId());
        p.setCreatedAt(pe.getCreatedAt());
        p.setModifiedAt(pe.getModifiedAt());
        return p;
    };

    public List<ProductDTO> findAllSoap(){
        return productRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }

}
