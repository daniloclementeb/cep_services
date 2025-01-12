package com.desafio.apilocalizacao.ApiLocalizacao.controller;

import com.desafio.apilocalizacao.ApiLocalizacao.model.SolicitacaoCepDTO;
import com.desafio.apilocalizacao.ApiLocalizacao.service.ConsultaCEPServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;

import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.Map;

@Controller
public class SQSController {

    @Autowired
    ConsultaCEPServices service;

    private static final ObjectMapper OBJECT_MAPPER = Jackson2ObjectMapperBuilder.json().build();


    @SqsListener(value ="cep_services", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void receiveMessage(String message) {
        System.out.println(message);

        try {
            SolicitacaoCepDTO solicitacaoCepDTO = OBJECT_MAPPER.readValue((String) OBJECT_MAPPER.readValue(message, Map.class).get("Message"), SolicitacaoCepDTO.class);
            service.buscaCEP(solicitacaoCepDTO);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Cannot process message from SQS", e);
        }


    }


}
