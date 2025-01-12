package com.desafio.apilocalizacao.ApiLocalizacao.controller;

import com.desafio.apilocalizacao.ApiLocalizacao.model.ConsultaCEPRequest;
import com.desafio.apilocalizacao.ApiLocalizacao.model.ConsultaCEPResponse;
import com.desafio.apilocalizacao.ApiLocalizacao.service.CepServices;
import com.desafio.apilocalizacao.ApiLocalizacao.service.LogServices;
import jakarta.validation.Valid;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/cep")
@Log
public class CEPController {

    @Autowired
    private CepServices service;

    @Autowired
    private LogServices logServices;

    @PostMapping("")
    public ResponseEntity<ConsultaCEPResponse> consultaCep(@RequestBody @Valid ConsultaCEPRequest request) {
        // Simula uma resposta

        ConsultaCEPResponse response = service.consultaCEP(request);
        logServices.log(request, response);
        if (response.getStatus().equalsIgnoreCase("erro")) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaCEPResponse> consultaBusca(@PathVariable("id") String id) {
        ConsultaCEPResponse resp = service.consultaSolicitacao(id);
        if (resp == null) {
            ResponseEntity.status(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(resp);
    }
}
