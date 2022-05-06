package thietnn.springboot_customermanager.controller.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import thietnn.springboot_customermanager.model.Customer;
import thietnn.springboot_customermanager.service.customer.CustomerService;

import java.util.Optional;

@Controller
public class CustomerController {
//    @Autowired
//    private ICustomerService customerService;
    private CustomerService customerService;

    @GetMapping("/create")
    public ModelAndView showCreateForm(){
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }
    @PostMapping("/create")
    public ModelAndView saveCustomer(@ModelAttribute("customer") Customer customer){
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView(("/customer/create"));
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("message","New Customer was created successfully");
        return modelAndView;
    }

    //list bị lỗi null pointer
    @GetMapping("/customer")
    public ModelAndView customerList(){
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customerList", customerService.findAll());
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id){
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()){
            ModelAndView modelAndView = new ModelAndView("/customer/edit");
            modelAndView.addObject("customer", customer.get());
            return modelAndView;
        }else
            return new ModelAndView("/customer/error-404");
    }

    @PostMapping("/edit")
    public ModelAndView editCustomer(@ModelAttribute("customer") Customer customer){
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("message", "Customer updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("/customer/delete");
            modelAndView.addObject("customer", customer.get());
            return modelAndView;

        } else {
            return new ModelAndView("/customer/error-404");
        }
    }

    @PostMapping("/delete")
    public String deleteCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.remove(customer.getId());
        return "redirect:customer";
    }

}
