package com.geneinsure.cqmservice.controller;

import com.geneinsure.cqmservice.config.DBConfigInitializer;
import com.geneinsure.cqmservice.exception.ResourceNotFoundException;
import com.geneinsure.cqmservice.model.entity.AbstractEntity;
import com.geneinsure.cqmservice.service.AbstractService;
import com.geneinsure.cqmservice.util.Formatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * @author Chirinda Nyasha 27/11/2021
 */

@RequestMapping(consumes = "application/json", produces = "application/json")
public abstract class AbstractController<T extends AbstractEntity> {

    private static final Logger log = LoggerFactory.getLogger(AbstractController.class);

    protected abstract AbstractService<T> getService();

    // Get All Resources
    @GetMapping
    public List<T> getAll() {
        return getService().findAll();
    }

    // Create a new Resource
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody T newResource) {
        log.info("Creating new Resource - {}", Formatter.toJson(newResource));

        T createdResource = getService().save(newResource);

        URI resourceLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdResource.getId())
                .toUri();

        return ResponseEntity.created(resourceLocation).build();
    }

    // Get a Single Resource
    @GetMapping("/{id}")
    public ResponseEntity<T> get(@PathVariable String id) {
        T t = getService().find(id).orElseThrow(() -> new ResourceNotFoundException("Resource", "id", id));
        return ResponseEntity.ok(t);
    }

    // Get Total number of Resources
    @RequestMapping("/count")
    public ResponseEntity<Long> count() {
        long resourcesCount = getService().count();
        return ResponseEntity.ok(resourcesCount);
    }

    // Update a Resource
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody T resourceDetails, @PathVariable String id) {
        log.info("Updating Resource ID: {} - {}", id, Formatter.toJson(resourceDetails));

        T updatedResource = getService().update(resourceDetails, id);
        return ResponseEntity.ok(updatedResource);
    }

    // Delete a Resource
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable String id) {
        getService().delete(id);
        return ResponseEntity.noContent().build();
    }
}
