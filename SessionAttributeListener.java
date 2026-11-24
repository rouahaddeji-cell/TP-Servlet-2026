package ex1;

import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class SessionAttributeListener
        implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.println(
            "[SESSION] Attribute added: "
            + event.getName()
            + " = "
            + event.getValue()
        );
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        System.out.println(
            "[SESSION] Attribute replaced: "
            + event.getName()
        );
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        System.out.println(
            "[SESSION] Attribute removed: "
            + event.getName()
        );
    }
}
