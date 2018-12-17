package obruening.multiinstance.config;

import org.springframework.context.ApplicationEvent;

public class UpdateEvent extends ApplicationEvent {

	private static final long serialVersionUID = -5012479680239243911L;
	
    public UpdateEvent(Object source) {
        super(source);
    }
}
