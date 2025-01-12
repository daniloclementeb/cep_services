package com.desafio.apilocalizacao.ApiLocalizacao.service;

import com.desafio.apilocalizacao.ApiLocalizacao.model.CepDTO;
import com.desafio.apilocalizacao.ApiLocalizacao.model.ConsultaCEPRequest;
import com.desafio.apilocalizacao.ApiLocalizacao.model.ConsultaCEPResponse;
import com.desafio.apilocalizacao.ApiLocalizacao.model.SolicitacaoCepDTO;
import com.desafio.apilocalizacao.ApiLocalizacao.repository.CepRepository;
import com.desafio.apilocalizacao.ApiLocalizacao.repository.SolicitacaoCepCacheRepository;
import com.desafio.apilocalizacao.ApiLocalizacao.repository.SolicitacaoCepRepository;
import com.desafio.apilocalizacao.ApiLocalizacao.util.GenericJsonConverter;
import com.desafio.apilocalizacao.ApiLocalizacao.util.JSONConverter;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CepServices {

    @Autowired
    SolicitacaoCepRepository solicitacaoCepRepository;

    @Autowired
    SolicitacaoCepCacheRepository solicitacaoCepCacheRepository;

    @Autowired
    CepRepository repository;

    @Autowired
    SnsService sns;

    @Autowired
    GenericJsonConverter json;

    public ConsultaCEPResponse consultaCEP(ConsultaCEPRequest request){
        //busca no cache/banco o cep
        SolicitacaoCepDTO solicitacao= solicitacaoCepCacheRepository.findByCep(request.getCep()).orElse(null);
        if (solicitacao == null) {
            solicitacao= solicitacaoCepRepository.findByCep(request.getCep()).orElse(null);
        }

        if (solicitacao != null) {
            if (solicitacao.getStatus().equalsIgnoreCase("pendente")) {
                //achou mas ta processando
                return ConsultaCEPResponse.builder()
                        .id(solicitacao.getIdentificador())
                        .status(solicitacao.getStatus())
                        .build();

            } else if (solicitacao.getStatus().equalsIgnoreCase("erro")) {
                return ConsultaCEPResponse.builder()
                        .id(solicitacao.getIdentificador())
                        .status(solicitacao.getStatus())
                        .build();
            } else {
                // aproveita consulta previa
                CepDTO cep = repository.findById(solicitacao.getCep()).orElse(null);
                return ConsultaCEPResponse.builder()
                        .id(solicitacao.getIdentificador())
                        .status(solicitacao.getStatus())
                        .cep(cep)
                        .build();

            }
        } else {
            //nao tem solicitacao na base
            //cria solicitacao
            SolicitacaoCepDTO entity = SolicitacaoCepDTO.builder()
                    .cep(request.getCep())
                    .status("PENDENTE")
                    .dataSolicitacao(LocalDate.now())
                    .build();

            SolicitacaoCepDTO save = solicitacaoCepRepository.save(entity);
            sns.publishMessage(json.convert(save));
            return ConsultaCEPResponse.builder()
                    .id(save.getIdentificador())
                    .status(save.getStatus())
                    .build();
        }
    }

    public ConsultaCEPResponse consultaSolicitacao(String identificador) {

        //dada uma solicitao busca no cache/banco ums status
        SolicitacaoCepDTO optSolicitacao = solicitacaoCepRepository.findByIdentificador(identificador).orElse(null);
        if (optSolicitacao != null) {
            CepDTO cep = repository.findById(optSolicitacao.getCep()).orElse(null);
            return ConsultaCEPResponse.builder()
                    .id(optSolicitacao.getIdentificador())
                    .status(optSolicitacao.getStatus())
                    .cep(cep)
                    .build();
        }
        return null;
    }
}
