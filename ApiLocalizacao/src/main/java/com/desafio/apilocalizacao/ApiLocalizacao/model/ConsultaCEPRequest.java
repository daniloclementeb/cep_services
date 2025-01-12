package com.desafio.apilocalizacao.ApiLocalizacao.model;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaCEPRequest {
    @NotNull
    @NotBlank(message = "O Campo CEP não pode ser vazio")
    @Size(max = 8, message = "O CEP deve ter no máximo 8 caracteres.")
    @Size(min = 8, message = "O CEP deve ter 8 caracteres.")
    @Pattern(regexp = "^[0-9]+$", message = "O CEP deve conter apenas números.")
    private String cep;
}
