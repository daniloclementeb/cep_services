package com.desafio.apilocalizacao.ApiLocalizacao.util;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;

public class GenericJsonConverter implements DynamoDBTypeConverter<String, Object> {

    private static final ObjectMapper OBJECT_MAPPER = Jackson2ObjectMapperBuilder.json().build(); // Jackson ObjectMapper para conversão de objetos

    @Override
    public String convert(Object object) {
        try {
            // Converte o objeto para JSON
            return object != null ? OBJECT_MAPPER.writeValueAsString(object) : null;
        } catch (IOException e) {
            throw new RuntimeException("Error converting object to JSON", e);
        }
    }

    @Override
    public Object unconvert(String json) {
        try {
            // Converte o JSON de volta para um objeto da classe fornecida
            return json != null ? OBJECT_MAPPER.readValue(json, Object.class) : null;
        } catch (IOException e) {
            throw new RuntimeException("Error converting JSON to object", e);
        }
    }
}
