package obruening.multiinstance.command;

import java.io.Serializable;
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
@Order(2)
public class MultiInstanceWithComplexListCommand implements Command {

    @Autowired
    private RuntimeService runtimeService;

    @Override
    public String getName() {

        return "Complex List";
    }

    @Override
    @Transactional("primaryTransactionManager")
    public void execute() {

        List<Item> itemList = Arrays.asList(
            new Item("Peter", "do something 1"), 
            new Item("Paul", "do something 2"),
            new Item("Mary", "do something 3"));

        Map<String, Object> map = new TreeMap<>();
        map.put("itemList", itemList);

        runtimeService.startProcessInstanceByKey("multiInstanceWithComplexList", map);

    }

    public static class Item implements Serializable {

        private static final long serialVersionUID = -323165878299720508L;

        public Item() {
        }

        public Item(String assignee, String payload) {

            this.assignee = assignee;
            this.setPayload(payload);
        }

        private String assignee;

        private String payload;

        public String getAssignee() {
            return assignee;
        }

        public void setAssignee(String assignee) {
            this.assignee = assignee;
        }

        public String getPayload() {
            return payload;
        }

        public void setPayload(String payload) {
            this.payload = payload;
        }
    }
}
