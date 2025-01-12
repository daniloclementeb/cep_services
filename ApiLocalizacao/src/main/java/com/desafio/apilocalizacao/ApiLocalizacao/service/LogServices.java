package com.desafio.apilocalizacao.ApiLocalizacao.service;

import com.desafio.apilocalizacao.ApiLocalizacao.model.ConsultaCEPRequest;
import com.desafio.apilocalizacao.ApiLocalizacao.model.ConsultaCEPResponse;
import com.desafio.apilocalizacao.ApiLocalizacao.model.LogCepDTO;
import com.desafio.apilocalizacao.ApiLocalizacao.repository.LogCepRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LogServices {

    @Autowired
    LogCepRepository repository;

    public void log(ConsultaCEPRequest request, ConsultaCEPResponse response) {
        LogCepDTO dto = LogCepDTO.builder()
                .request(request)
                .response(response)
                .dataSolicitacao(LocalDate.now())
                .build();
        repository.save(dto);
    }
}
