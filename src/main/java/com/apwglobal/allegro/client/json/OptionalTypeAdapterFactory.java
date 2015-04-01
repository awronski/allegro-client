package com.apwglobal.allegro.client.json;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;

public class OptionalTypeAdapterFactory implements TypeAdapterFactory {

    /*
    * https://github.com/jclouds/jclouds/blob/master/core/src/main/java/org/jclouds/json/internal/OptionalTypeAdapterFactory.java
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Type type = typeToken.getType();
        if (typeToken.getRawType() != Optional.class || !(type instanceof ParameterizedType)) {
            return null;
        }

        Type elementType = ((ParameterizedType) type).getActualTypeArguments()[0];
        TypeAdapter<?> elementAdapter = gson.getAdapter(TypeToken.get(elementType));
        return (TypeAdapter<T>) newOptionalAdapter(elementAdapter);
    }

    protected <E> TypeAdapter<Optional<E>> newOptionalAdapter(final TypeAdapter<E> elementAdapter) {

        return new TypeAdapter<Optional<E>>() {
            public void write(JsonWriter out, Optional<E> value) throws IOException {
                if (value == null || !value.isPresent()) {
                    out.nullValue();
                    return;
                }
                elementAdapter.write(out, value.get());
            }

            public Optional<E> read(JsonReader in) throws IOException {
                Optional<E> result = Optional.empty();
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                } else {
                    E element = elementAdapter.read(in);
                    if (element != null) {
                        result = Optional.of(element);
                    }
                }
                return result;
            }
        };
    }
}
