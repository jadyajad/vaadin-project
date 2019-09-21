package com.packagename.myapp.spring;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import com.vaadin.flow.component.*;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

import java.util.concurrent.atomic.AtomicInteger;


@Route
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Project Base")
@CssImport("./styles/css.css")
@StyleSheet("css.css")
public class MainView extends VerticalLayout {
@Autowired userRep repo;
int falseAttempt =0;
    public MainView() {

        setSpacing(false);
        Header header = new Header(new H1("Welcome to the Store"));
        header.setClassName("color");
        add(header);
        TextField username = new TextField("username:");
        PasswordField password = new PasswordField("password");
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        Anchor a = new Anchor(UI.getCurrent().getRouter().getUrl(signIn.class),"sign up");
        Button signIn = new Button("Sign In", event -> {
        if(repo.count()>0){
            User u = repo.findByUserName(username.getValue());
            if(falseAttempt>=2)
                UI.getCurrent().getPage().setLocation("https://www.google.com");
            else if(u!=null && u.getUserName().equals(username.getValue())&& u.getPassword().equals(password.getValue()))
                UI.getCurrent().getPage().setLocation("home");
            else{
                Notification.show("Wrong username or password",2000, Notification.Position.TOP_START);
                falseAttempt++;
            }
        }else
            Notification.show("No users yet please sign up",2000, Notification.Position.TOP_END);
            username.setValue("");
            password.setValue("");
        });
        signIn.addClickShortcut(Key.ENTER);
        horizontalLayout.add(a,signIn);
        add(username,password,horizontalLayout);

}



}
