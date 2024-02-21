package org.example.robinsonaguilar.controller;

import org.example.robinsonaguilar.entities.Customer;
import org.example.robinsonaguilar.entities.Product;
import org.example.robinsonaguilar.entities.Sale;
import org.example.robinsonaguilar.response.ResponseHandler;
import org.example.robinsonaguilar.services.CustomerService;
import org.example.robinsonaguilar.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/sale")
public class SaleController {
    @Autowired
    private SaleService saleService;
    @Autowired
    private CustomerService customerService;

    @GetMapping("/getProducts/")
    public ResponseEntity<Object> findAllProducts(){
        try {
            List<Product> result = saleService.findAllProducts();
            return ResponseHandler.generateResponse("Success", HttpStatus.OK,result);
        }catch (Exception e){
            return ResponseHandler.generateResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage() );
        }
    }

    @GetMapping("/")
    public ResponseEntity<Object> findAll(){
        try {
            List<Sale> result = saleService.findAll();
            return ResponseHandler.generateResponse("Success", HttpStatus.OK,result);
        }catch (Exception e){
            return ResponseHandler.generateResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage() );
        }
    }

    @PostMapping("/{idCustomer}")
    public ResponseEntity<Object> saveFacture(@PathVariable Integer idCustomer,@RequestBody Sale sale){
        try {
            Customer customer = customerService.findById(idCustomer);
            if (customer != null){
                Sale result = saleService.save(sale,customer);
                if (result != null){
                    return ResponseHandler.generateResponse("Success Sale",HttpStatus.CREATED,result);
                }
                return ResponseHandler.generateResponse("Stock en producto no disponible",HttpStatus.NOT_ACCEPTABLE,result);
            }
            return ResponseHandler.generateResponse("No found Customer",HttpStatus.NOT_FOUND,null);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }
}
