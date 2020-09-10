package org.lordrose.petclinicwithspring.formatters;

import org.lordrose.petclinicwithspring.model.PetType;
import org.lordrose.petclinicwithspring.service.PetTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Set;

@Component
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        Set<PetType> types = petTypeService.findAll();
        return types.stream()
                .filter(type -> type.getName().equals(text))
                .findFirst()
                .orElseThrow(() -> new ParseException("Type not found: " + text, 0));
    }

    @Override
    public String print(PetType type, Locale locale) {
        return type.getName();
    }
}
