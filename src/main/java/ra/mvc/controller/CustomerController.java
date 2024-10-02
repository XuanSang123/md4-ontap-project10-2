package ra.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ra.mvc.model.entity.Customer;
import ra.mvc.service.customer.ICustomerService;

@Controller
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("customers", customerService.findAll());
        return "list-customer";
    }

    ;

    @GetMapping("/add")
    public String add() {
        return "add-customer";
    }


    @PostMapping("/add")
    public String doAdd(@ModelAttribute Customer customer) {
        customerService.save(customer);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", customerService.findById(id));
        return "edit-customer";
    }

    @PostMapping("/update")
    public String doUpdate(@ModelAttribute Customer customer) {
        customerService.save(customer);
        return "redirect:/";
    }

    @GetMapping("/detail/{id}")
    public String detailCus(@PathVariable Integer id, Model model) {
        model.addAttribute("cus", customerService.findById(id));
        return "detail-customer";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, Model model) {
        customerService.delete(id);
        return "redirect:/";

    }


}
