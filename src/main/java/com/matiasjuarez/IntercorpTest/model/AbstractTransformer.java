package com.matiasjuarez.IntercorpTest.model;

public abstract class AbstractTransformer<D, E> {
    public abstract D convertToDTO(E entity);
    public abstract E convertToEntity(D dto);
}
