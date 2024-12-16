package com.boostrap.landingpage.mappers;

public interface IMapper<T,Y> {
    public Y toDto(T element);
    public T toEntity(Y element);
}
