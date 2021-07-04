package model.validator;


import exception.EntityException;


public interface Validatable<T> {
    public void setValidator(Validator<T> validator);
    public void validate() throws EntityException;
}
