package model.validator;

import exception.EntityException;

public interface Validator <T>{
    void validate(T entity) throws EntityException;
}
