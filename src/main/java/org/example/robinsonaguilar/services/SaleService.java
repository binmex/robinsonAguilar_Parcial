package org.example.robinsonaguilar.services;

import org.example.robinsonaguilar.entities.Customer;
import org.example.robinsonaguilar.entities.Product;
import org.example.robinsonaguilar.entities.Sale;
import org.example.robinsonaguilar.repositories.ProductRepository;
import org.example.robinsonaguilar.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;

    @Autowired private ProductRepository productRepository;

     @Autowired
     private  ProductsService productsService;

    public List<Sale> findAll(){
        return saleRepository.findAll();
    }

    public Sale save(Sale sale, Customer customer){
        boolean result = validateProducts(sale);
        if (result) { // No es necesario comparar con false
            sale.setCustomer(customer);
            updateStock(sale.getProducts());
            return saleRepository.save(sale);
        } else {
            return null;
        }
    }


    public Sale findById(Integer id){
        Optional<Sale> optional = saleRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    public boolean updateSale(Integer id,Sale sale){
        Sale findSale = findById(id);
        if (findSale!= null){
            findSale.setDate_of_sale(sale.getDate_of_sale());
            saleRepository.save(findSale);
            return true;
        }else {
            return false;
        }
    }

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    public boolean validateProducts(Sale sale){
        for (int i = 0; i < sale.getProducts().size(); i++) {
            int cantOnStock = saleRepository.stokProduct(sale.getProducts().get(i).getId());
            if (cantOnStock < sale.getProducts().get(i).getStock()){
                return false;
            }
        }
        return true;
    }

    public void updateStock(List<Product> products){
        for (int i = 0; i < products.size(); i++) {
            Product found = productsService.findById(products.get(i).getId());
            if (found != null) {
                int updatedStock = found.getStock() - products.get(i).getStock();
                if (updatedStock >= 0) {
                    found.setStock(updatedStock);
                    productRepository.save(found);
                }
            }
        }
    }


}
