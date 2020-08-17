package org.lordrose.petclinicwithspring.bootstrap;

import org.lordrose.petclinicwithspring.model.Owner;
import org.lordrose.petclinicwithspring.model.Vet;
import org.lordrose.petclinicwithspring.service.OwnerService;
import org.lordrose.petclinicwithspring.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setFirstName("Mason");
        owner1.setLastName("Dipper Pines");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Mabel");
        owner2.setLastName("Pines");

        ownerService.save(owner2);

        Owner owner3 = new Owner();
        owner3.setId(3L);
        owner3.setFirstName("Pacifica");
        owner3.setLastName("Elise Northwest");

        ownerService.save(owner3);

        System.out.println("## Loaded Owners");

        Vet vet1 = new Vet();
        vet1.setFirstName("Wendy");
        vet1.setLastName("Corduroy");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Stan");
        vet2.setLastName("Pines");

        vetService.save(vet2);

        System.out.println("## Loaded Vets");
    }
}
