package com.example.jstlexample.servlet;

import com.example.jstlexample.model.Product;
import com.example.jstlexample.service.ProductService;
import com.example.jstlexample.service.ProductServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/products")
public class ProductServlet extends HttpServlet
{
    private ProductService service;

    @Override
    public void init() throws ServletException {
        service = new ProductServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Product first = new Product();
        first.setName("Apples");
        first.setPrice(25);

        Product second = new Product();
        second.setName("Potatoes");
        second.setPrice(10);

        service.save(first);
        System.out.println("First: " + service.findById(first.getId()));
        service.save(second);
        System.out.println("All products" + service.findAll());
        second.setPrice(50);
        second.setName("Thing");
        service.save(second);
        System.out.println("All products" + service.findAll());
        service.delete(first.getId());
        System.out.println("All products" + service.findAll());
        req.getRequestDispatcher("success.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
