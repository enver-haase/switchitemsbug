package com.example.application.views.helloworld;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.PageTitle;
import com.example.application.views.MainLayout;
import com.vaadin.flow.router.RouteAlias;

@PageTitle("Hello World")
@Route(value = "hello", layout = MainLayout.class)
@RouteAlias(value = "", layout = MainLayout.class)
public class HelloWorldView extends HorizontalLayout {

    private TextField name;
    private Button sayHello;

    private boolean sw = false;

    public HelloWorldView() {
        setMargin(true);
        name = new TextField("Your name");
        sayHello = new Button("Say hello");

        Select<String> sel = new Select<>();
        sel.setItems("eins", "zwei");

        Button b = new Button("Switch select's items", e -> {
            if (sw) {
                sel.setItems("one", "two", "three");
            }
            else{
                sel.setItems("rouge", "maron");
            }
           sw = !sw;
        });

        add(name, sayHello, sel, b);
        setVerticalComponentAlignment(Alignment.END, name, sayHello);
        sayHello.addClickListener(e -> {
            Notification.show("Hello " + name.getValue());
        });

    }

}
