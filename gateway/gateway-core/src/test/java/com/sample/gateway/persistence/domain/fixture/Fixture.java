package com.sample.gateway.persistence.domain.fixture;

import com.sample.gateway.core.domain.Operator;
import com.sample.gateway.persistence.domain.ApplicationEntity;
import com.sample.gateway.persistence.domain.OperatorEntity;

import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/14/14
 * Time: 3:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class Fixture {

    public static ApplicationEntity firstApplication() {
        return null;
    }

    public static OperatorEntity buildOperatorEntity(String operatorName)
    {
        String createdBy = "Some Guy";
        OperatorEntity operatorEntity = new OperatorEntity(createdBy);
        operatorEntity.setOperatorName(operatorName);
        operatorEntity.setEnabled(true);
        Calendar cal = Calendar.getInstance();
        operatorEntity.setContractStartOn(cal.getTime());
        cal.add(Calendar.YEAR, 1 );
        operatorEntity.setContractEndOn(cal.getTime());

        String hypehnatedName = operatorName.replaceAll(" ", "-");
        operatorEntity.setConnectorUri("http://"+hypehnatedName+".com/connector");
        operatorEntity.setApiUri("http://"+hypehnatedName+".com/api");

        return operatorEntity;
    }

    public static Operator buildOperator(String name) {
        Operator operator = new Operator();
        operator.setOperatorName(name);
        operator.setEnabled(true);

        operator.setOperatorName(name);
        operator.setEnabled(true);
        Calendar cal = Calendar.getInstance();
        operator.setContractStartOn(cal.getTime());
        cal.add(Calendar.YEAR, 1 );
        operator.setContractEndOn(cal.getTime());

        String hypehnatedName = name.replaceAll(" ", "-");
        operator.setConnectorUri("http://"+hypehnatedName+".com/connector");
        operator.setApiUri("http://"+hypehnatedName+".com/api");

        return operator;
    }


}
