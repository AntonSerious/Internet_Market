package com.anemchenko;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {
    //root: [http://localhost:8189/iMarket]
    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/showMeDemo")
    @ResponseBody
    public String demoMethod(){
        return "DEMO";
    }

    @GetMapping("/page")
    public String showIndexPage(){
        return "index";
    }

    //GET http://localhost:8189/iMarket/add?a=10&b=20
    @GetMapping("/add")
    public String addTwoNumbersAndShowResult(Model model, @RequestParam(name = "a") int a, @RequestParam(name = "b") int b){
        int result = a + b;
        model.addAttribute("a", a);
        model.addAttribute("b", b);

        model.addAttribute("addResult", result);
        return "result";
    }

//    //GET http://localhost:8189/iMarket/users/1/profile
//    @GetMapping("/users/{id}/profile")
//    public String showProfile(Model model, @PathVariable Long id){
//        User user = new User(id, "Bob", 30);
//        model.addAttribute("user", user);
//        return "profile";
//    }
//

    //GET http://localhost:8189/iMarket/add_product?a=10&b=20
    @GetMapping("/all_products")
    public String ShowAllProducts(Model model){
        model.addAttribute("productRepository", productRepository.getProductList());
        return "all_products";
    }

    //GET http://localhost:8189/iMarket/add_product?a=10&b=20
    @GetMapping("/add_product_form")
    public String ShowAddProductForm(){
        return "add_product_form";
    }
    //GET http://localhost:8189/iMarket/add_product?id=1&title=butter&cost=20
    @PostMapping("/add_product")
    public String AddProduct(@RequestParam (name = "id") int id, @RequestParam (name = "title") String title, @RequestParam (name = "cost") double cost ){
        productRepository.addProduct(id, title, cost);
        return "redirect:/all_products";
    }
}
