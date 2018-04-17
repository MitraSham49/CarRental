package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @Autowired
    CarRepository carRepo;

    @RequestMapping("/")
    public String showIndex(Model model) {
        model.addAttribute("inventory", carRepo.findAll());
        return"index";
    }
    @RequestMapping("/add")
    public String addBook(Model model) {
        model.addAttribute("aCar" , new Car());
        return"addcar";
    }
    @RequestMapping("/savecar")
    public String saveBook(@ModelAttribute("aCar") Car toSave, BindingResult result){

        if (result.hasErrors()) {
            return "addcar";
        }
       carRepo.save(toSave);
        return "redirect:/";
    }

    @RequestMapping("/changestatus/{id}")
    public String borrowReturn(@PathVariable("id") long id) {
       Car thisCar = carRepo.findById(id).get();
       thisCar.setBorrowed(!thisCar.isBorrowed());
       carRepo.save(thisCar);
        return "redirect:/";
    }
    @RequestMapping("/update/{id}")

    public String updateBook(@PathVariable ("id") long id, Model model){

        model.addAttribute("aCar",carRepo.findById(id).get());

        return "addcar";
    }

    @RequestMapping("/detail/{id}")
    public String showBook(@PathVariable ("id") long id, Model model) {
        model.addAttribute("aCar",carRepo.findById(id).get());
        return "redirect:/";

    }
// car image
    //https://webiconspng.com/wp-content/uploads/2017/09/Opel-PNG-Image-11729.png
}
