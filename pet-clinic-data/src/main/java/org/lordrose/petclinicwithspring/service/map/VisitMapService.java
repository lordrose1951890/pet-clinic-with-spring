package org.lordrose.petclinicwithspring.service.map;

import org.lordrose.petclinicwithspring.model.Visit;
import org.lordrose.petclinicwithspring.service.VisitService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VisitMapService extends AbstractMapService<Visit, Long>
        implements VisitService {

    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Visit save(Visit visit) {
        if (isInvalid(visit))
            throw new RuntimeException("Invalid Visit");
        return super.save(visit);
    }

    private boolean isInvalid(Visit visit) {
        if (visit.getPet() == null) {
            return true;
        }
        if (visit.getPet().getOwner() == null) {
            return true;
        }
        if (visit.getPet().getId() == null) {
            return true;
        }
        if (visit.getPet().getOwner().getId() == null) {
            return true;
        }
        return false;
    }

    @Override
    public void delete(Visit object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
