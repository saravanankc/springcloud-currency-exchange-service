package com.kc.mylearnings.springcloud.microservices.currencyexchangeservice;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private ExchangeValueRepository exchangeValueRepository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public  ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to){

        //ExchangeValue exchangeValue = new ExchangeValue(1000L, from, to, BigDecimal.valueOf(65));

        ExchangeValue exchangeValue = exchangeValueRepository.findByFromAndTo(from, to);

        if(StringUtils.isNotEmpty(environment.getProperty("server.port"))){
            //Use "local.server.port" or simply "server.port"
            exchangeValue.setInstancePort(Integer.parseInt(environment.getProperty("server.port")));
        }
        return exchangeValue;

    }
}
