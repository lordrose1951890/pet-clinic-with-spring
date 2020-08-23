package org.lordrose.petclinicwithspring.bootstrap;

import org.lordrose.petclinicwithspring.model.Owner;
import org.lordrose.petclinicwithspring.model.Pet;
import org.lordrose.petclinicwithspring.model.PetType;
import org.lordrose.petclinicwithspring.model.Specialty;
import org.lordrose.petclinicwithspring.model.Vet;
import org.lordrose.petclinicwithspring.service.OwnerService;
import org.lordrose.petclinicwithspring.service.PetTypeService;
import org.lordrose.petclinicwithspring.service.SpecialtyService;
import org.lordrose.petclinicwithspring.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;

    public DataLoader(OwnerService ownerService, VetService vetService,
                      PetTypeService petTypeService, SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();

        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDog = petTypeService.save(dog);

        PetType cat = new PetType();
        dog.setName("Cat");
        PetType savedCat = petTypeService.save(cat);

        PetType pig = new PetType();
        pig.setName("Pig");
        PetType savedPig = petTypeService.save(pig);

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty savedRadiology = specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty savedSurgery = specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty savedDentistry = specialtyService.save(dentistry);


        Owner owner1 = new Owner();
        owner1.setFirstName("Mason");
        owner1.setLastName("Dipper Pines");
        owner1.setAddress("123 Wall Street");
        owner1.setCity("New York");
        owner1.setTelephone("123456789");

        Pet dipperPet = new Pet();
        dipperPet.setPetType(savedDog);
        dipperPet.setOwner(owner1);
        dipperPet.setBirthDate(LocalDate.now());
        owner1.getPets().add(dipperPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Mabel");
        owner2.setLastName("Pines");
        owner2.setAddress("22 Jump Street");
        owner2.setCity("Los Santos");
        owner2.setTelephone("244466666");

        Pet mabelPet = new Pet();
        mabelPet.setPetType(savedPig);
        mabelPet.setOwner(owner2);
        mabelPet.setBirthDate(LocalDate.now());
        owner2.getPets().add(mabelPet);

        ownerService.save(owner2);

        Owner owner3 = new Owner();
        owner3.setId(3L);
        owner3.setFirstName("Pacifica");
        owner3.setLastName("Elise Northwest");

        Pet pacificaPet = new Pet();
        pacificaPet.setPetType(savedCat);
        pacificaPet.setOwner(owner3);
        pacificaPet.setBirthDate(LocalDate.now());
        owner3.getPets().add(pacificaPet);

        ownerService.save(owner3);

        System.out.println("## Loaded Owners");

        Vet vet1 = new Vet();
        vet1.setFirstName("Wendy");
        vet1.setLastName("Corduroy");
        vet1.getSpecialties().add(savedRadiology);
        vet1.getSpecialties().add(savedSurgery);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Stan");
        vet2.setLastName("Pines");
        vet2.getSpecialties().add(savedDentistry);

        vetService.save(vet2);

        System.out.println("## Loaded Vets");
    }
}
