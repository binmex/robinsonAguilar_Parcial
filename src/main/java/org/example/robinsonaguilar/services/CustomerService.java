package org.example.robinsonaguilar.services;

import org.example.robinsonaguilar.entities.Customer;
import org.example.robinsonaguilar.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }
    public Customer findById(Integer id){
        Optional<Customer> optional = customerRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }
    public Customer saveClient(Customer newClient){
        customerRepository.save(newClient);
        return newClient;
    }

    public boolean removeClient(Integer id){
        customerRepository.delete(findById(id));
        return true;
    }
    public boolean updateClient(Integer id,Customer client){
        Customer findCustomer = findById(id);
        if (findCustomer != null){
            findCustomer.setAge(client.getAge());
            findCustomer.setName(client.getName());
            findCustomer.setLastName(client.getLastName());
            customerRepository.save(findCustomer);
            return true;
        }else {
            return false;
        }
    }
}
