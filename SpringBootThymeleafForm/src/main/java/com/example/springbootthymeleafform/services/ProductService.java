package com.example.springbootthymeleafform.services;

import com.example.springbootthymeleafform.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    public List<Product> getProducts() throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/lab2";
        Connection con = DriverManager.getConnection(url, "root",  "root");
        Statement instr = con.createStatement();
        String sql = "SELECT * FROM products";
        ResultSet rs = instr.executeQuery(sql);
        List<Product> products = new ArrayList<>();
        while (rs.next()) {
            Product p = new Product();
            p.setName(rs.getString("name"));
            p.setSize(rs.getInt("size"));
            p.setDescription(rs.getString("description"));
            p.setPrice(rs.getFloat("price"));
            products.add(p);
        }

        instr.close();
        con.close();
        return products;
    }
}