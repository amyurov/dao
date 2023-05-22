package com.github.amyurov.dao.controllers;

import com.github.amyurov.dao.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private final OrderRepository orderRepository;

    @Autowired
    public ProductsController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping("/fetch-product")
    public String fetchProduct(@RequestParam("name") String name) {
        return orderRepository.getProductName(name);
    }
}
