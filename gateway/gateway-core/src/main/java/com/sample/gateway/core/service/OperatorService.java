package com.sample.gateway.core.service;

import com.sample.gateway.core.event.RegisterOperatorEvent;
import com.sample.gateway.core.event.RegisteredOperatorEvent;
import org.springframework.stereotype.Service;

/**
 * Created by lloydengebretsen on 2/20/14.
 */
@Service
public interface OperatorService {

    public RegisteredOperatorEvent registerOperator(RegisterOperatorEvent operatorRegisterEvent);


}
