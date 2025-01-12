package com.desafio.apilocalizacao.ApiLocalizacao.repository;

import com.desafio.apilocalizacao.ApiLocalizacao.model.CepDTO;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Cacheable(value = "Cep")
@EnableScan
public interface CepRepository extends CrudRepository<CepDTO, String> {
}
