package com.example.springbootthymeleafform.controllers;
import com.example.springbootthymeleafform.Product;
import com.example.springbootthymeleafform.User;
import com.example.springbootthymeleafform.services.ProductService;
import com.example.springbootthymeleafform.ShoppingList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
@Controller
public class MainController {

    private final ProductService productService = new ProductService();

    @GetMapping("/register_form")
    public String showForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        List<String> personList = Arrays.asList("Natural Person", "Legal Person");
        model.addAttribute("personList", personList);
        return "register_form";
    }

    @PostMapping("/register_form")
    public String submitForm(@ModelAttribute("user") User user) {
        System.out.println(user.toString());
        return "register_success";
    }

    @GetMapping("/login_form")
    public String showLogin(Model model){
        User user = new User();
        model.addAttribute("user", user);
        System.out.println("log1");
        return "login_form";
    }

    @PostMapping("/login_form")
    public String submitLogin(@ModelAttribute("user") User user) {
        System.out.println(user.toString());
        System.out.println("log2");
       // return "redirect:/products";
        return "redirect:/shopping_list";
    }

    @GetMapping("/products")
    public String showProducts(Model model) throws SQLException, ClassNotFoundException {
        List<Product> productList = productService.getProducts();
        model.addAttribute("productList", productList);
        for(Product p : productList){
            System.out.println(p.toString());
        }
        System.out.println(productList.size());
        return "products";
    }

    @GetMapping("/shopping_list")
    public String showShoppingList(Model model){
        ShoppingList shopping_list = new ShoppingList();
        model.addAttribute("shopping_list", shopping_list);
        return "shopping_list";
    }

    @PostMapping("/shopping_list")
    public String submitShoppingList(Model model,@ModelAttribute("shoppingList") ShoppingList shopping_list) {
        // return "redirect:/products";
        model.addAttribute("shopping_list", shopping_list);
        return "/order_placed";
    }

}


