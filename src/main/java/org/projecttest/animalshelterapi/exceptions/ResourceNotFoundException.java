package org.projecttest.animalshelterapi.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class ResourceNotFoundException extends EntityNotFoundException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
