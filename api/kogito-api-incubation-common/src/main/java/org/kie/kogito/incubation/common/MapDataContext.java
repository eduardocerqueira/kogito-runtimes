package org.kie.kogito.incubation.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.kie.kogito.incubation.common.objectmapper.InternalObjectMapper;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A data context that wraps a <code>Map<String, Object></code>.
 */
public class MapDataContext implements MapLikeDataContext, MetaDataContext {

    public static <T> MapDataContext from(T object) {
        return InternalObjectMapper.objectMapper()
                .convertValue(object, MapDataContext.class);
    }

    public static MapDataContext of(Map<String, Object> map) {
        return new MapDataContext(map);
    }

    public static MapDataContext create() {
        return new MapDataContext();
    }

    @JsonIgnore
    private final Map<String, Object> map = new HashMap<>();

    // package-private constructors for Quarkus
    MapDataContext() {
    }

    // package-private constructors for Quarkus
    MapDataContext(Map<String, Object> map) {
        this();
        this.map.putAll(map);
    }

    @Override
    public <T extends DataContext> T as(Class<T> type) {
        if (type.isInstance(this)) { // this short circuit is needed as the below passes `map`, not `this`, to the InternalObjectMapper
            return type.cast(this);
        }
        return InternalObjectMapper.objectMapper().convertValue(map, type);
    }

    // required to unwrap the POJO to the map
    @JsonAnySetter
    @Override
    public void set(String key, Object value) {
        map.put(key, value);
    }

    @Override
    public Object get(String key) {
        return map.get(key);
    }

    @Override
    public <T> T get(String key, Class<T> expectedType) {
        return InternalObjectMapper.objectMapper().convertValue(map.get(key), expectedType);
    }

    // required to unwrap the map to the root of the mapped object
    @JsonAnyGetter
    public Map<String, Object> toMap() {
        return map;
    }

    @Override
    public String toString() {
        return "MapDataContext{" +
                "map=" + map +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        MapDataContext that = (MapDataContext) o;
        return Objects.equals(map, that.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map);
    }
}
