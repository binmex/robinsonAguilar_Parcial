package org.example.robinsonaguilar.controller;

import org.example.robinsonaguilar.entities.Customer;
import org.example.robinsonaguilar.response.ResponseHandler;
import org.example.robinsonaguilar.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public ResponseEntity<Object> findAll(){
        try {
            List<Customer> result = customerService.findAll();
            return ResponseHandler.generateResponse("Success", HttpStatus.OK,result);
        }catch (Exception e){
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null );
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Integer id){
        try {
            return ResponseHandler.generateResponse("Success", HttpStatus.OK,customerService.findById(id));
        }catch (Exception e){
            return ResponseHandler.generateResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR,e);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Object> saveCustomer(@RequestBody Customer cliente){
        try {
            return ResponseHandler.generateResponse("Succes",HttpStatus.OK,customerService.saveClient(cliente));
        }catch (Exception e){
            return ResponseHandler.generateResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCustomer(@PathVariable Integer id, @RequestBody Customer cliente){
        try {
            return ResponseHandler.generateResponse("Succes",HttpStatus.OK,customerService.updateClient(id,cliente));
        }catch (Exception e){
            return ResponseHandler.generateResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR,e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable Integer id){
        try {
            return ResponseHandler.generateResponse("Succes",HttpStatus.OK,customerService.removeClient(id));
        }catch (Exception e){
            return ResponseHandler.generateResponse("Error", HttpStatus.INTERNAL_SERVER_ERROR,e);
        }
    }
}
