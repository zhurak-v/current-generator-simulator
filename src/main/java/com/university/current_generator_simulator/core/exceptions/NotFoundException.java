package com.university.current_generator_simulator.core.exceptions;

public final class NotFoundException extends AppException {

    public NotFoundException() {
        super(RequestStatus.NOT_FOUND);
    }

}
