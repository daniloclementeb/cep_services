package com.desafio.apilocalizacao.ApiLocalizacao.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BrasilAPIResponse {
    private String cep;
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private String service;
}
