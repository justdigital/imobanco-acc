package com.imobanco.autenticador.infrastructure.config;

import com.amazonaws.auth.BasicAWSCredentials;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
//@AllArgsConstructor
@PropertySource("classpath:application.properties")
public class AwsCredenciaisSingleton {

    public static volatile BasicAWSCredentials instance = null;
    @Value("${aws.acess.key}")
    private String ACESS_KEY;
   // private final String ACESS_KEY;
    @Value("${aws.secret.key}")
    private String SECRET_KEY;
    //private final String SECRET_KEY;

    public BasicAWSCredentials getInstance() {
        if (instance == null) {
            synchronized (AwsCredenciaisSingleton.class) {
                if (instance == null) {
                    instance = new BasicAWSCredentials(ACESS_KEY, SECRET_KEY);
                }
            }
        }
        return instance;
    }

}
