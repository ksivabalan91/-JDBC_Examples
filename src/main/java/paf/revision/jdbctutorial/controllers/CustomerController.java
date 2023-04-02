package paf.revision.jdbctutorial.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import paf.revision.jdbctutorial.models.Customer;
import paf.revision.jdbctutorial.models.Orders;
import paf.revision.jdbctutorial.repositories.NorthwindRepo;

@Controller
@RequestMapping
public class CustomerController {
    @Autowired
    NorthwindRepo nwRepo;

    @GetMapping("/customers")
    public String findallcustomers(Model model){      
        List<Customer> customers = nwRepo.findall("customers", Customer.class);  
        model.addAttribute("customers", customers);
        return "customersList";
    }
    
    @GetMapping("/orders")
    public String findAllOrders(Model model){
        List<Orders> orders = nwRepo.findall("orders", Orders.class);
        model.addAttribute("orders",orders);
        return "orderList";
    }

    @GetMapping("/order/{employeeId}")
    public String findOrderbyField(@PathVariable String employeeId, Model model){
        List<Orders> orders = nwRepo.findItems("*", "orders", "employee_id",employeeId, Orders.class);
        model.addAttribute("orders",orders);
        return "order";
    }

    @GetMapping("/order/shipcity/{shipcity}")
    public String findOrderbyShipcity(@PathVariable String shipcity, Model model){
        
        List<Orders> orders = nwRepo.findItems("*", "orders","ship_city",shipcity, Orders.class);
        model.addAttribute("orders",orders);
        
        return "order";
    }

}
