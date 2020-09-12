package org.lordrose.petclinicwithspring.controller;

import org.lordrose.petclinicwithspring.model.Vet;
import org.lordrose.petclinicwithspring.service.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping({"/vets", "/vets/index", "/vets/index.html", "/vets.html"})
    public String listVets(Model model) {
        model.addAttribute("vets", vetService.findAll());
        return "vet/index";
    }

    @GetMapping("/api/vets")
    public @ResponseBody List<Vet> getVetsAsJson() {
        return new ArrayList<>(vetService.findAll());
    }
}
