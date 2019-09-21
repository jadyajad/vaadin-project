package com.packagename.myapp.spring;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import javafx.scene.control.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.vaadin.flow.component.*;
import java.awt.*;
import java.util.List;

@Route("SignUp")
@CssImport("./styles/css.css")
@StyleSheet("css.css")
public class signIn extends Div {

    @Autowired userRep repo;
    public signIn(){
        Paragraph p = new Paragraph();
        com.vaadin.flow.component.html.Image image = new Image("http://content.draftexpress.com/bundles/draftexpresspublic/img/logo_red_square.png",
                "express logo");
        image.setHeight("80px");
        image.setWidth("100px");
        H1 welcome = new H1();
//        welcome.add(image);
        welcome.setText("ProjectExpress");
        welcome.setClassName("welcome");
        Header h = new Header();
        h.setWidth("100%");
        Anchor a = new Anchor("SignUp","Home"),a2 = new Anchor("","Sign Out");
        Nav n = new Nav(a,a2);
        p.add(image,welcome);
        h.add(p,n);
        h.getStyle().set("background-image","linear-gradient( white, silver)");
        add(h);

        setClassName("signin");
        setHeight("100%");
        Div d = new Div();
        d.setClassName("container");
        VerticalLayout vr = new VerticalLayout();
        vr.setClassName("vr");
        vr.setSpacing(false);
        TextField firstName = new TextField("first Name"), lastName = new TextField("last name"),
                username = new TextField("username");
        PasswordField password= new PasswordField("password");
        password.setMinLength(8);
        Button button = new Button("sign up");
        button.setWidth("100%");
        button.addClickShortcut(Key.ENTER);
        button.addClickListener(event -> {
            String user = username.getValue();
            if(firstName.isEmpty() || lastName.isEmpty() || user.isEmpty() || password.isEmpty()) {
                 Notification.show("please fill all fields", 3000, Notification.Position.TOP_CENTER);
            }
            else if(repo.count()==0 || repo.findByUserName(user)!=null){
                Notification.show("username already exist",3000, Notification.Position.TOP_CENTER);
                username.setValue("");
                password.setValue("");
            }
            else{

            repo.save(new User(firstName.getValue(), lastName.getValue(), username.getValue(), password.getValue()));
            firstName.setValue("");
            lastName.setValue("");
            username.setValue("");
            password.setValue("");
            UI.getCurrent().getPage().setLocation("home");
            }
        });

//        firstName.setWidth("50%");
//        lastName.setWidth("50%");
//        username.setWidth("50%");
//        password.setWidth("50%");
        HorizontalLayout first =new HorizontalLayout(firstName,lastName), sec=new HorizontalLayout(username, password);
//        first.setWidth("100%");
//        sec.setWidth("100%");
        vr.add(first,sec,button);
        d.add(vr);

        this.add(d);

    }

}
