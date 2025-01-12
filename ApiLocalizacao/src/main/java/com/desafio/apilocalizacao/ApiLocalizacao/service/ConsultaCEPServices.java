package com.desafio.apilocalizacao.ApiLocalizacao.service;

import com.desafio.apilocalizacao.ApiLocalizacao.model.BrasilAPIResponse;
import com.desafio.apilocalizacao.ApiLocalizacao.model.CepDTO;
import com.desafio.apilocalizacao.ApiLocalizacao.model.SolicitacaoCepDTO;
import com.desafio.apilocalizacao.ApiLocalizacao.repository.CepRepository;
import com.desafio.apilocalizacao.ApiLocalizacao.repository.SolicitacaoCepRepository;
import com.desafio.apilocalizacao.ApiLocalizacao.service.feign.BrasilAPI;
import com.desafio.apilocalizacao.ApiLocalizacao.service.feign.WiremockAPI;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsultaCEPServices {

    //@Autowired
    //BrasilAPI api;

    @Autowired
    WiremockAPI api;

    @Autowired
    CepRepository repository;

    @Autowired
    SolicitacaoCepRepository solicitacaoCepRepository;

    public void buscaCEP(SolicitacaoCepDTO solicitacaoCepDTO) {
        try {
            BrasilAPIResponse body = api.getCep(solicitacaoCepDTO.getCep());

            if (body != null) {
                trataSucesso(solicitacaoCepDTO, body);
            } else {
                trataErro(solicitacaoCepDTO);
            }

        } catch (FeignException ex) {
            trataErro(solicitacaoCepDTO);
        }catch (Exception ex) {
            throw new RuntimeException("Cannot process API Request", ex);
        }

    }

    private void trataErro(SolicitacaoCepDTO solicitacaoCepDTO) {
        SolicitacaoCepDTO solicitacao = solicitacaoCepRepository.findByIdentificador(solicitacaoCepDTO.getIdentificador()).orElse(null);
        if (solicitacao != null) {
            solicitacao.setStatus("ERRO");
            solicitacaoCepRepository.save(solicitacao);
        }
    }

    private void trataSucesso(SolicitacaoCepDTO solicitacaoCepDTO, BrasilAPIResponse body) {
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
    }
}
