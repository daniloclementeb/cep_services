package com.desafio.apilocalizacao.ApiLocalizacao.service;

import com.desafio.apilocalizacao.ApiLocalizacao.model.BrasilAPIResponse;
import com.desafio.apilocalizacao.ApiLocalizacao.model.CepDTO;
import com.desafio.apilocalizacao.ApiLocalizacao.model.SolicitacaoCepDTO;
import com.desafio.apilocalizacao.ApiLocalizacao.repository.CepRepository;
import com.desafio.apilocalizacao.ApiLocalizacao.repository.SolicitacaoCepRepository;
import com.desafio.apilocalizacao.ApiLocalizacao.service.feign.BrasilAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsultaCEPServices {

    @Autowired
    BrasilAPI api;

    @Autowired
    CepRepository repository;

    @Autowired
    SolicitacaoCepRepository solicitacaoCepRepository;

    public void buscaCEP(SolicitacaoCepDTO solicitacaoCepDTO) {
        try {
            BrasilAPIResponse body = api.getCep(solicitacaoCepDTO.getCep());
                //BrasilAPIResponse body = response.getBody();
            if (body != null) {
                CepDTO cep = CepDTO.builder()
                        .cep(body.getCep())
                        .bairro(body.getNeighborhood())
                        .cidade(body.getCity())
                        .estado(body.getState())
                        .rua(body.getStreet())
                        .build();
                repository.save(cep);
                SolicitacaoCepDTO solicitacao = solicitacaoCepRepository.findByIdentificador(solicitacaoCepDTO.getIdentificador()).orElse(null);
                if (solicitacao != null) {
                    solicitacao.setStatus("CONCLUIDO");
                    solicitacaoCepRepository.save(solicitacao);
                }
            } else {
                //erro no processo
                SolicitacaoCepDTO solicitacao = solicitacaoCepRepository.findByIdentificador(solicitacaoCepDTO.getIdentificador()).orElse(null);
                if (solicitacao != null) {
                    solicitacao.setStatus("ERRO");
                    solicitacaoCepRepository.save(solicitacao);
                }
            }

        } catch (Exception ex) {
            throw new RuntimeException("Cannot process API Request", ex);
        }

    }
}
