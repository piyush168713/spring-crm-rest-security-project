package com.luv2code.springdemo.controller;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    // need to inject the customer dao
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/list") // This will only respond to request
    // with GET method, not with POST
    public String listCustomers(Model theModel) {

        // get customers from the dao
        List<Customer> theCustomers = customerService.getCustomers();

        // add the customer to the model
        theModel.addAttribute("customers", theCustomers);

        return "list-customer";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Customer theCustomer = new Customer();

        theModel.addAttribute("customer", theCustomer);

        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {

        // save the customer using our service

        customerService.saveCustomer(theCustomer);

        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int theId,
                                    Model theModel) {
        // get the customer from the database
        Customer theCustomer = customerService.getCustomer(theId);

        // set customer as a model attribute to pre-populate the form
        theModel.addAttribute("customer", theCustomer);

        return "customer-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("customerId") int theId, Model theModel) {

        // deleteCustomer the customer from the database
        customerService.deleteCustomer(theId);

        return "redirect:/customer/list";

    }

    @GetMapping("/search")
    public String searchCustomers(@RequestParam("theSearchName") String theName,
                                  Model theModel) {

        // search customers for the service
        List<Customer> theCustomers = customerService.searchCustomers(theName);

        // add the customers to the model
        theModel.addAttribute("customers", theCustomers);

        return "list-customer";
    }

    @GetMapping("/sortByFirstName")
    public String sortByFirstName(Model theModel) {

        // sort customers by first name
        List<Customer> theCustomers = customerService.sortByFirstName();

        // add customers to model
        theModel.addAttribute("customers", theCustomers);

        return "list-customer";
    }

    @GetMapping("/sortByLastName")
    public String sortByLastName(Model theModel) {

        // sort customers by last name
        List<Customer> theCustomers = customerService.sortByLastName();

        // add customers to model
        theModel.addAttribute("customers", theCustomers);

        return "list-customer";
    }

    @GetMapping("/sortByEmail")
    public String sortByEmail(Model theModel) {

        // sort customers by email
        List<Customer> theCustomers = customerService.sortByEmail();

        // add customers to model
        theModel.addAttribute("customers", theCustomers);

        return "list-customer";
    }

}
