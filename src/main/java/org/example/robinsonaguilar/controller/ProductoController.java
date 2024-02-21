package org.example.robinsonaguilar.controller;

import org.example.robinsonaguilar.entities.Product;
import org.example.robinsonaguilar.response.ResponseHandler;
import org.example.robinsonaguilar.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductoController {
    @Autowired
    private ProductsService productsService;

    @GetMapping("/")
    public ResponseEntity<Object> findAll(){
        try {
            List<Product> result = productsService.findAll();
            return ResponseHandler.generateResponse("Success", HttpStatus.OK,result);
        }catch (Exception e){
            return ResponseHandler.generateResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage() );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id){
        try {
            return ResponseHandler.generateResponse("Success",HttpStatus.OK,productsService.findById(id));
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @PostMapping("/")
    public ResponseEntity<Object> saveProduct(@RequestBody Product producto){
        try {
            Product result = productsService.save(producto);
            if (result != null){
                return ResponseHandler.generateResponse("Success",HttpStatus.CREATED,result);
            }
            return ResponseHandler.generateResponse("El stock del producto debe ser mayor a 0",HttpStatus.NOT_ACCEPTABLE,result);
        }catch (Exception e){
            return ResponseHandler.generateResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage() );
        }
    }
}


