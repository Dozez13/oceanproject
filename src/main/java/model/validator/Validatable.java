package model.validator;




public interface Validatable<T> {
     void setValidator(Validator<T> validator);
     boolean validate();
}
