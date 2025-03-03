package com.desafio.apilocalizacao.ApiLocalizacao.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.desafio.apilocalizacao.ApiLocalizacao.util.GenericJsonConverter;
import com.desafio.apilocalizacao.ApiLocalizacao.util.LocalDateConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "log_cep")
public class LogCepDTO {

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private String id;
    @DynamoDBTypeConverted(converter = GenericJsonConverter.class)
    @DynamoDBAttribute(attributeName = "request")
    private ConsultaCEPRequest request;
    @DynamoDBTypeConverted(converter = GenericJsonConverter.class)
    @DynamoDBAttribute(attributeName = "response")
    private ConsultaCEPResponse response;

    private LocalDate dataSolicitacao;

    @DynamoDBTypeConverted(converter = LocalDateConverter.class)  // Usando o conversor para LocalDate
    @DynamoDBAttribute(attributeName = "dataSolicitacao")
    public LocalDate getDataSolicitacao() {
        return dataSolicitacao;
    }

}
