package com.desafio.apilocalizacao.ApiLocalizacao.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBVersionAttribute;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "cep")
public class CepDTO {
    private String cep;
    private String estado;
    private String cidade;
    private String bairro;
    private String rua;
    @DynamoDBVersionAttribute
    private Long version; // O campo deve ser do tipo Long
    @DynamoDBHashKey
    public String getCep() {
        return cep;
    }

}
