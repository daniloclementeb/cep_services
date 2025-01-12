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

    private static final String TOPIC_ARN = "arn:aws:sns:us-east-1:888555049247:cep_services";

    public String publishMessage(String message) {
        PublishRequest publishRequest = new PublishRequest(TOPIC_ARN, message);

        PublishResult publishResult = amazonSNS.publish(publishRequest);

        return publishResult.getMessageId();
    }

}