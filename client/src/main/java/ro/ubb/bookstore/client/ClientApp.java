package ro.ubb.bookstore.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.ubb.bookstore.client.ui.ClientConsole;

public class ClientApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(
                        "ro.ubb.bookstore.client.config"
                );

        ClientConsole clientConsole = context.getBean(ClientConsole.class);
        clientConsole.runConsole();

        System.out.print("x");
    }
}