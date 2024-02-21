package org.example.robinsonaguilar.services;

import org.example.robinsonaguilar.entities.Product;
import org.example.robinsonaguilar.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product save(Product producto){
        //producto.setFacturas(factura);
        if (producto.getStock() <= 0) {
            return null;
        }else {
            return productRepository.save(producto);
        }
    }

    public Product findById(Integer id){
        Optional<Product> optional = productRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    public boolean updateProduct(Integer id,Product producto){
        Product findProducto = findById(id);
        if (findProducto != null){
            findProducto.setNameProduct(producto.getNameProduct());
            findProducto.setStock(producto.getStock());
            productRepository.save(findProducto);
            return true;
        }else {
            return false;
        }
    }
}
