package com.desafio.apilocalizacao.ApiLocalizacao.service.feign;

import com.desafio.apilocalizacao.ApiLocalizacao.Config.FeignConfig;
import com.desafio.apilocalizacao.ApiLocalizacao.model.BrasilAPIResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Wiremock", url="http://localhost:8081/", configuration = FeignConfig.class)
public interface WiremockAPI {

    @GetMapping("cep/{cep}")
    BrasilAPIResponse getCep(@PathVariable("cep") String cep);
    default void logResponse(String cep, BrasilAPIResponse response) {
        System.out.println("CEP: " + cep);
        System.out.println("Response: " + response);
    }
}
