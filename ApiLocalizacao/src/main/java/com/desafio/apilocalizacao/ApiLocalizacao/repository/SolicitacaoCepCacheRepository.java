package com.desafio.apilocalizacao.ApiLocalizacao.repository;

import com.desafio.apilocalizacao.ApiLocalizacao.model.SolicitacaoCepDTO;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableScan
public interface SolicitacaoCepCacheRepository extends CrudRepository<SolicitacaoCepDTO, String> {
    @org.springframework.cache.annotation.Cacheable(value = "SolicitacaoCep")
    Optional<SolicitacaoCepDTO> findByCep(String cep);

    Optional<SolicitacaoCepDTO> findByIdentificador(String identificador);
}
