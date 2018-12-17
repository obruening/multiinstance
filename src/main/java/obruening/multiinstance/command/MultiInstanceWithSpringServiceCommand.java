package obruening.multiinstance.command;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Order(4)
public class MultiInstanceWithSpringServiceCommand implements Command {
    
    @Autowired
    private RuntimeService runtimeService;

    @Override
    public String getName() {

        return "Spring Service";
    }

    @Override
    @Transactional("primaryTransactionManager")
    public void execute() {
        
        runtimeService.startProcessInstanceByKey("multiInstanceWithSpringService");
    }
}
