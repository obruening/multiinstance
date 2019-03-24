package obruening.multiinstance.workflow.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SimpleServiceTask implements JavaDelegate {
	
	private static Logger logger = LoggerFactory.getLogger(SimpleServiceTask.class);

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		logger.info("in simpleServiceTask");
	}

}
