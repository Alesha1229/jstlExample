package com.example.jstlexample.service;

import com.example.jstlexample.model.Product;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;

public class ProductServiceImpl implements ProductService {
    private MongoCollection<Document> collection;


    public ProductServiceImpl() {
        //соеденение бд с монго
        MongoDatabase database = MongoClients
                .create("mongodb://localhost:27017")
                .getDatabase("jsp_ex");
        if (database.getCollection("products") == null) {
            database.createCollection("products");}
        collection = database.getCollection("products");
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();

        FindIterable<Document> documents = collection.find();
        documents.forEach(document -> {
            Product product = new Product();
            product.setName(document.getString("name"));
            product.setPrice(document.getInteger("price"));
            product.setId(document.getObjectId("_id").toHexString());
            products.add(product);
        });
        return products;
    }

    @Override
    public Product findById(String id) {
        Bson filter = Filters.eq("_id", new ObjectId(id));
        Document document = this.collection.find(filter).first();
        Product product = new Product();
        product.setName(document.getString("name"));
        product.setPrice(document.getInteger("price"));
        product.setId(document.getObjectId("_id").toHexString());
        return product;
    }

    @Override
    public void save(Product product) {
        Document document = new Document();
        document.put("name", product.getName());
        document.put("price", product.getPrice());
        if (product.getId()==null){
            collection.insertOne(document);
            product.setId(document.getObjectId("_id").toHexString());
        } else {
            Bson filter = Filters.eq("_id", new ObjectId(product.getId()));
            this.collection.replaceOne(filter,document);

        }

    }

    @Override
    public void delete(String id) {
        Bson filter = Filters.eq("_id", new ObjectId(id));
        this.collection.deleteOne(filter);

    }
}
