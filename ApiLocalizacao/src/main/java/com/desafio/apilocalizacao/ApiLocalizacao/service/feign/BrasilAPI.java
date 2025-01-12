package com.desafio.apilocalizacao.ApiLocalizacao.service.feign;

import com.desafio.apilocalizacao.ApiLocalizacao.Config.FeignConfig;
import com.desafio.apilocalizacao.ApiLocalizacao.model.BrasilAPIResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "BrasilApi", url="https://brasilapi.com.br/api/", configuration = FeignConfig.class)
public interface BrasilAPI {

    @GetMapping("cep/v2/{cep}")
    BrasilAPIResponse getCep(@PathVariable("cep") String cep);
    default void logResponse(String cep, BrasilAPIResponse response) {
        System.out.println("CEP: " + cep);
        System.out.println("Response: " + response);
    }
}
