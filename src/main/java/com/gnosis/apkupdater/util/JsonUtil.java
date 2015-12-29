package com.gnosis.apkupdater.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

/**
 * Created by Firat on 22.9.2014.
 */
public class JsonUtil {
    public static ObjectMapper getObjectMapper() {
     /*    Hibernate4Module hbm = new Hibernate4Module();
       hbm.disable(Hibernate4Module.Feature.FORCE_LAZY_LOADING);
        hbm.disable(Hibernate4Module.Feature.SERIALIZE_IDENTIFIER_FOR_LAZY_NOT_LOADED_OBJECTS);
        hbm.disable(Hibernate4Module.Feature.REQUIRE_EXPLICIT_LAZY_LOADING_MARKER);*/

        return new ObjectMapper()

                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static String toJSon(Object object) throws JsonProcessingException {
        return getObjectMapper().writeValueAsString(object);
        //return JsonWriter.objectToJson(object);
    }

    public static void toJSonAsFile(File file, Object object) throws IOException {
        getObjectMapper().writeValue(file, object);
        // FileUtil.saveFile(file, JsonWriter.objectToJson(object));
    }

    public static <T> T fromJson(String json, Class<T> type) throws IOException {
        return getObjectMapper().readValue(json, type);
        // return (T) JsonReader.jsonToJava(json);
    }

    public static <T> T fromJson(File jsonFile, Class<T> type) throws IOException {

        //    Hibernate.initialize( ((ScenarioTreeNode)HibernateUtil.getSessionFactory().openSession().load(ScenarioTreeNode.class,1)).getChildren());
        try {
            return getObjectMapper().readValue(jsonFile, type);
        } catch (Exception e) {
            try {
                return type.newInstance();
            } catch (InstantiationException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
        return null;

    }
}