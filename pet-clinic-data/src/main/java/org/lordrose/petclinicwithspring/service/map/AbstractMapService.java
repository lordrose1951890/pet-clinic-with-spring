package org.lordrose.petclinicwithspring.service.map;

import org.lordrose.petclinicwithspring.model.BaseEntity;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {

    protected Map<Long, T> map = new HashMap<>();
    private Long indexCounter = null;

    Set<T> findAll() {
        return new HashSet<>(map.values());
    }

    T findById(ID id) {
        return map.get(id);
    }

    T save(T object) {
        setID(object);

        map.put(object.getId(), object);

        return object;
    }

    void deleteById(ID id) {
        map.remove(id);
    }

    void delete(T object) {
        map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    private Long getNextId() {
        if (isUninitialized(indexCounter))
            initIndexCounter();
        return indexCounter++;
    }

    private void initIndexCounter() {
        try {
            indexCounter = Collections.max(map.keySet()) + 1;
        } catch (NoSuchElementException e) {
            indexCounter = 1L;
        }
    }
    
    private boolean isUninitialized(Long indexCounter) {
        return indexCounter == null;
    }

    private boolean checkValid(T object) {
        return object != null;
    }

    private void setID(T object) {
        if (checkValid(object)) {
            object.setId(getNextId());
        } else {
            throw new RuntimeException("Object cannot be null");
        }
    }
}
