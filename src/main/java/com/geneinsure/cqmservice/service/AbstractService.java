package com.geneinsure.cqmservice.service;

import com.geneinsure.cqmservice.exception.ResourceNotFoundException;
import java.util.Collections;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Chirinda Nyasha 27/11/2021
 */

public abstract class AbstractService<T> {

    protected abstract JpaRepository<T, String> getRepository();

    public Optional<T> find(String id) {
        return getRepository().findById(id);
    }

    public List<T> findAll() {
        return getRepository().findAll();
    }

    public long count() {
        return getRepository().count();
    }

    public void delete(String id) {
        if (!getRepository().existsById(id)) throw new ResourceNotFoundException("Resource", "id", id);

        getRepository().deleteById(id);
    }

    public T save(T t) {
        if (t == null) return null;

        return getRepository().save(t);
    }

    public List<T> saveAll(List<T> t) {
        if (t.isEmpty()) return Collections.emptyList();

        return getRepository().saveAll(t);
    }

    public T update(T t, String id) {
        if (t == null) return null;

        if (!getRepository().existsById(id)) throw new ResourceNotFoundException("Resource", "id", id);

        return getRepository().save(t);
    }
}
