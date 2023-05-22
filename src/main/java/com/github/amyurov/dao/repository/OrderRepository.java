package com.github.amyurov.dao.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional(readOnly = true)
public class OrderRepository {

    private static final String SCRIPT_NAME = "findOrdersByName.sql";
    private static String read(String scriptFileName) {
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public OrderRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String getProductName(String name) {
        String namedQuery = read(SCRIPT_NAME);
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put("name", name);

        List<String> products = jdbcTemplate.queryForList(namedQuery, parameters, String.class);

        if (products.isEmpty()) {
            return String.format("У пользователя %s - нет товаров", name);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Список товаров:<br>");
        for (String product : products) {
            sb.append(product).append("<br>");
        }

        return sb.toString();
    }
}


