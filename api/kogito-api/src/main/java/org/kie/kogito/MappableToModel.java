package org.kie.kogito;

public interface MappableToModel<T> extends Model {

    T toModel();
}
