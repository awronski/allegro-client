package com.apwglobal.allegro.client.json;

import com.apwglobal.nice.exception.AllegroException;
import com.apwglobal.nice.exception.UnknownAllegroException;
import com.google.gson.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

public class AllegroExceptionTypeAdapter implements JsonDeserializer<AllegroException> {
    @Override
    public AllegroException deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String message = jsonObject.getAsJsonPrimitive("message").getAsString();
        String path = jsonObject.getAsJsonPrimitive("path").getAsString();
        String ex = jsonObject.getAsJsonPrimitive("exception").getAsString();

        return getAllegroException(message, path, ex);
    }

    private AllegroException getAllegroException(String message, String path, String ex) {
        Class<?> clazz;
        try {
            clazz = Class.forName(ex);
            Constructor<?> ctor = clazz.getConstructor(String.class);
            return (AllegroException) ctor.newInstance(path + "\n" + message);
        } catch (Exception e) {
            throw new UnknownAllegroException(path + "\n" + message, e);
        }
    }
}
