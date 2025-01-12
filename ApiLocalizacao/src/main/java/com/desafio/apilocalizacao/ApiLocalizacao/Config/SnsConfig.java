package com.desafio.apilocalizacao.ApiLocalizacao.Config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SnsConfig {
    @Bean
    public AmazonSNS amazonSNS() {
        BasicAWSCredentials awsCredentials = new BasicAWSCredentials("", "");
        return AmazonSNSClientBuilder.standard()
                .withRegion("us-east-1") // Substitua pela sua região
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
