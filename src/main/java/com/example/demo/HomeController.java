package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    CarRepository carRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @RequestMapping("/")
    public String getIndex(Model model){
        model.addAttribute("cars", carRepository.findAll());

        return "index";
    }

    @GetMapping("/addCar")
    public String addCar(Model model){
        model.addAttribute("car", new Car());
        model.addAttribute("categories", categoryRepository.findAll() );
        return "carform";
    }

    @PostMapping("/processCar")
    public String processCar(@Valid Car car, BindingResult result){
        if(result.hasErrors()){
            return "carform";
        }
        carRepository.save(car);
        return "redirect:/";
    }

    @RequestMapping("/updateCar/{id}")
    public String updateCar(Model model, @PathVariable("id")long id){
        model.addAttribute("car", carRepository.findById(id).get());
        model.addAttribute("categories", categoryRepository.findAll());
        return "carform";
    }

    @RequestMapping("/deleteCar/{id}")
    public String delCar(@PathVariable("id") long id){
        carRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/showCar/{id}")
    public String showCar(@PathVariable("id") long id, Model model){
        model.addAttribute("car", carRepository.findById(id).get());
        return "showcar";
    }

    @RequestMapping("/carlist")
    public String carlist(Model model) {
        model.addAttribute("cars", carRepository.findAll());
        return "carlist";
    }



    @GetMapping("/addCategory")
    public String addCategory(Model model){
        model.addAttribute("category", new Category());
        return "categoryform";
    }

    @PostMapping("/processCategory")
    public String processCategory(@Valid Category category, BindingResult result){
        if(result.hasErrors()){
            return "categoryform";
        }
        categoryRepository.save(category);
        return "redirect:/";
    }

    @RequestMapping("/updateCategory/{id}")
    public String updateCategory(Model model, @PathVariable("id")long id){
        model.addAttribute("category", categoryRepository.findById(id).get());
        return "categoryform";
    }

    @RequestMapping("/deleteCategory/{id}")
    public String delCategory(@PathVariable("id") long id){
        categoryRepository.deleteById(id);
        return "redirect:/";
    }

    @RequestMapping("/showCategory/{id}")
    public String showCategory(@PathVariable("id") long id, Model model){
        model.addAttribute("category", categoryRepository.findById(id).get());
        return "showcategory";
    }

    @RequestMapping("/categorylist")
    public String categorylist(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "categorylist";
    }

}
