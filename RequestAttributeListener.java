package ex1;

import jakarta.servlet.ServletRequestAttributeEvent;
import jakarta.servlet.ServletRequestAttributeListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class RequestAttributeListener
        implements ServletRequestAttributeListener {

    @Override
    public void attributeAdded(ServletRequestAttributeEvent event) {
        System.out.println(
            "[REQUEST] Attribute added: "
            + event.getName()
        );
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent event) {
        System.out.println(
            "[REQUEST] Attribute replaced: "
            + event.getName()
        );
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent event) {
        System.out.println(
            "[REQUEST] Attribute removed: "
            + event.getName()
        );
    }
}
