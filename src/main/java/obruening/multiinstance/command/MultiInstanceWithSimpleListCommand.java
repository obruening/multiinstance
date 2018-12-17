package obruening.multiinstance.command;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Order(1)
public class MultiInstanceWithSimpleListCommand implements Command {

    @Autowired
    private RuntimeService runtimeService;

    @Override
    public String getName() {

        return "Simple List";
    }

    @Override
    @Transactional("primaryTransactionManager")
    public void execute() {
        
        List<String> assigneeList = Arrays.asList("Peter", "Paul", "Mary");
        
        Map<String, Object> map = new TreeMap<>();
        map.put("assigneeList", assigneeList);
        
        runtimeService.startProcessInstanceByKey("multiInstanceWithSimpleList", map);
    }
}
