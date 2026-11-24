package ex1;

import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ApplicationAttributeListener
        implements ServletContextAttributeListener {

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        System.out.println(
            "[APPLICATION] Attribute added: "
            + event.getName()
        );
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        System.out.println(
            "[APPLICATION] Attribute replaced: "
            + event.getName()
        );
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        System.out.println(
            "[APPLICATION] Attribute removed: "
            + event.getName()
        );
    }
}
