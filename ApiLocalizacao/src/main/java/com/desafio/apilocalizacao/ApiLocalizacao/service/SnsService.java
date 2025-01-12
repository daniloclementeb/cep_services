package com.desafio.apilocalizacao.ApiLocalizacao.service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import com.desafio.apilocalizacao.ApiLocalizacao.model.SolicitacaoCepDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SnsService {

    @Autowired
    private AmazonSNS amazonSNS;

    // Defina o ARN do seu tópico SNS
    private static final String TOPIC_ARN = "arn:aws:sns:us-east-1:888555049247:cep_services";

    // Método para publicar uma mensagem no SNS
    public String publishMessage(String message) {
        // Criação de uma requisição de publicação
        PublishRequest publishRequest = new PublishRequest(TOPIC_ARN, message);

        // Publica a mensagem no SNS
        PublishResult publishResult = amazonSNS.publish(publishRequest);

        // Retorna o ID da mensagem publicada
        return publishResult.getMessageId();
    }

}