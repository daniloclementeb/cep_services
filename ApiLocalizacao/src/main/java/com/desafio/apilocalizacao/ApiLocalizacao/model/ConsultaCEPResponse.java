package com.desafio.apilocalizacao.ApiLocalizacao.model;

import com.desafio.apilocalizacao.ApiLocalizacao.service.CepServices;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaCEPResponse {
    private String id;
    private String status;
    private CepDTO cep;
}
