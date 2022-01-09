package com.anemchenko.services;


import com.anemchenko.dto.ProductCommentDto;
import com.anemchenko.exceptions.ResourceNotFoundException;
import com.anemchenko.model.Customer_x_Product;
import com.anemchenko.model.Product;
import com.anemchenko.model.ProductComment;
import com.anemchenko.model.User;
import com.anemchenko.repositories.Customer_x_ProductRepository;
import com.anemchenko.repositories.ProductCommentRepository;
import com.anemchenko.repositories.ProductRepository;
import com.anemchenko.repositories.specifications.ProductSpecifications;
import com.anemchenko.soap.products.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductCommentRepository productCommentRepository;
    private final Customer_x_ProductRepository customer_x_productRepository;
    private final UserService userService;

    private static final String FILTER_MIN_PRICE = "min_price";
    private static final String FILTER_MAX_PRICE = "max_price";
    private static final String FILTER_TITLE = "title";
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

    public Page<Product> findAll(int pageIndex, int pageSize, MultiValueMap<String, String> rqParams){
        return productRepository.findAll(constructSpecification(rqParams), PageRequest.of(pageIndex, pageSize));
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

    @Transactional
    public List<ProductDTO> findAllSoap(){
        List<Product> products = productRepository.findAll();
        for (Product p: products
             ) {
            System.out.println(p.getTitle());
           // System.out.println(p.getCategory());

        }
        return productRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());


    }
    private Specification<Product> constructSpecification(MultiValueMap<String, String> params){
        Specification<Product> spec = Specification.where(null);
        if(params.containsKey(FILTER_MIN_PRICE) && !params.getFirst(FILTER_MAX_PRICE).isEmpty()){
            int minPrice = Integer.parseInt(params.getFirst(FILTER_MIN_PRICE));
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (params.containsKey(FILTER_MAX_PRICE) && !params.getFirst(FILTER_MAX_PRICE).isEmpty()) {
            int maxPrice = Integer.parseInt(params.getFirst(FILTER_MAX_PRICE));
            spec = spec.and(ProductSpecifications.priceLesserOrEqualsThan(maxPrice));
        }
        if (params.containsKey(FILTER_TITLE) && !params.getFirst(FILTER_TITLE).isEmpty()) {
            String title = params.getFirst(FILTER_TITLE);
            spec = spec.and(ProductSpecifications.titleLike(title));
        }
        return spec;
    }

    public List<ProductComment> findAllComments(Long productId){
        return productRepository.findAllComments(productId);
    }

    public boolean isCommentSent(String name, Long id) {
        if(productRepository.totalCommentSent(name, id) > 0){
            return true;
        }
        else{
            return false;
        }
    }
    @Transactional
    public void createProductComment(ProductCommentDto productCommentDto, Principal principal, Long productId) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Не удалось найти пользователя при создании комментария. Имя пользователя: " + principal.getName()));

        ProductComment productComment = new ProductComment();
        productComment.setUser(user);
        productComment.setProduct(findById(productId).orElseThrow(()-> new ResourceNotFoundException("Product not found")));
        productComment.setComment(productCommentDto.getComment());
        productCommentRepository.save(productComment);
    }
}
