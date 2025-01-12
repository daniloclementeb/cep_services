package com.desafio.apilocalizacao.ApiLocalizacao.util;

import com.desafio.apilocalizacao.ApiLocalizacao.model.SolicitacaoCepDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;

@Component
public class JSONConverter {
    private static final ObjectMapper OBJECT_MAPPER = Jackson2ObjectMapperBuilder.json().build();

    public String toJSON(SolicitacaoCepDTO message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Convertendo o objeto para uma string JSON
            String json = OBJECT_MAPPER.writeValueAsString(message);

            return json;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
