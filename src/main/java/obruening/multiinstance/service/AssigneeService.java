package obruening.multiinstance.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service("assigneeService")
public class AssigneeService {
    
    public List<String> getAssignees() {
        return Arrays.asList("Peter", "Paul", "Mary");
    }

}
