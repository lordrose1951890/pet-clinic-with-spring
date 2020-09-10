package org.lordrose.petclinicwithspring.controller;

import org.lordrose.petclinicwithspring.model.Owner;
import org.lordrose.petclinicwithspring.model.Pet;
import org.lordrose.petclinicwithspring.model.PetType;
import org.lordrose.petclinicwithspring.service.OwnerService;
import org.lordrose.petclinicwithspring.service.PetService;
import org.lordrose.petclinicwithspring.service.PetTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("/owners/{ownerId}/pets")
public class PetController {

    private static final String VIEWS_PET_CREATE_OR_UPDATE_FORM =
            "pet/createOrUpdatePetForm";

    private final PetService petService;
    private final PetTypeService petTypeService;
    private final OwnerService ownerService;

    public PetController(PetService petService, PetTypeService petTypeService,
                         OwnerService ownerService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
        this.ownerService = ownerService;
    }

    @ModelAttribute("types")
    public Collection<PetType> populatePetTypes() {
        return petTypeService.findAll();
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @InitBinder("owner")
    public void initOwnerBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    @GetMapping("/new")
    public String getCreateForm(Owner owner, Model model) {
        Pet newPet = new Pet();
        owner.addPet(newPet);
        model.addAttribute("pet", newPet);
        return VIEWS_PET_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreateForm(Owner owner, Pet pet,
                                    BindingResult result, Model model) {
        if (!isCreateFormValid(owner, pet)) {
            result.rejectValue(
                    "name", "duplicate", "already exists");
        }
        owner.addPet(pet);
        if (result.hasErrors()) {
            model.addAttribute("pet", pet);
            return VIEWS_PET_CREATE_OR_UPDATE_FORM;
        } else {
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }

    @GetMapping("/{petId}/edit")
    public String getUpdateForm(@PathVariable Long petId, Model model) {
        model.addAttribute("pet", petService.findById(petId));
        return VIEWS_PET_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{petId}/edit")
    public String processUpdateForm(Pet pet, BindingResult result,
                                    Owner owner, Model model) {
        if (result.hasErrors()) {
            pet.setOwner(owner);
            model.addAttribute("pet", pet);
            return VIEWS_PET_CREATE_OR_UPDATE_FORM;
        } else {
            owner.addPet(pet);
            petService.save(pet);
            return "redirect:/owners/" + owner.getId();
        }
    }

    private boolean isCreateFormValid(Owner owner, Pet pet) {
        return !StringUtils.hasLength(pet.getName()) || !pet.isNew() ||
                owner.getPetIgnoreNew(pet.getName()) == null;
    }
}
