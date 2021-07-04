package model.validator;


import exception.EntityException;


public interface Validatable<T> {
     void setValidator(Validator<T> validator);
     void validate() throws EntityException;
}
