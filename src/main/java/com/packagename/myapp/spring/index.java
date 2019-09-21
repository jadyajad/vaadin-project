package com.packagename.myapp.spring;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;
import com.vaadin.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.hene.popupbutton.PopupButton;

@Route("home")
@CssImport("./styles/css.css")
@StyleSheet("css.css")
public class index extends VerticalLayout {
    int wrongAtt = 0;
    public index( @Autowired ItemRep i,@Autowired userRep r) {
        getStyle().set("padding","0px");
        HorizontalLayout hr = new HorizontalLayout();
        Paragraph p = new Paragraph();
        Image image = new Image("http://content.draftexpress.com/bundles/draftexpresspublic/img/logo_red_square.png",
                "express logo");
        image.setHeight("80px");
        image.setWidth("100px");
        H1 welcome = new H1();
//        welcome.add(image);
        welcome.setText("ProjectExpress");
        welcome.setClassName("welcome");
        Header h = new Header();
        h.setWidth("100%");
        Anchor a = new Anchor("home","Home"),a2 = new Anchor("","Sign Out");
        Nav n = new Nav(a,a2);
        p.add(image,welcome);
        h.add(p,n);
        h.getStyle().set("background-image","linear-gradient( white, silver)");
//        add(h);


        ListBox<Item> grid = new ListBox<>();
        grid.setWidth("100%");

//        grid.removeColumnByKey("id");
//        grid.setColumns("name", "category","price");
        if(i!=null) {
            grid.setItems(i.findAll());
            grid.setRenderer(new ComponentRenderer<>(item -> {
                    Image product = new Image(item.getImage()!=null? item.getImage():"","");
                    Label name = new Label("Item: " + item.getName());
                    Label category = new Label("Category: " + item.getCategory());
                    Label seller = new Label("Seller username: "+item.getUser(r).getUserName());
                    Label description = new Label("About Item: "+item.getDescription());
//                    Button button = new Button("update", event -> {
//                        grid.getDataProvider().refreshItem(item);
//                    });
                    product.setWidth("100px");
                    product.setHeight("100px");
//                    setHorizontalComponentAlignment(Alignment.END,button);
                    Div labels = new Div(name, category,seller);
                    Div layout = new Div(product,labels, description);

                    labels.getStyle().set("display", "flex")
                            .set("flexDirection", "column").set("marginRight", "10px").set("padding","4px");
                    layout.getStyle().set("display", "flex")
                            .set("alignItems", "center").set("background","linear-gradient(to right, gray, silver, white)");
                    layout.setWidth("100%");

                    return layout;
                }));
        }
        Button addItem = new Button();
        addItem.setIcon(new Icon(VaadinIcon.ADD_DOCK));
        addItem.getStyle().set("margin","8px");
        setHorizontalComponentAlignment(Alignment.START,addItem);
        addItem.getStyle().set("border-radius","50%");
//        addItem.setWidth("25px");


        TextField itemName = new TextField("item name"), category = new TextField("category"),
                imageLink = new TextField("short image url"), sellerName = new TextField("enter username"),
                aboutItem = new TextField("about Item");
        PasswordField passWord = new PasswordField("password:");
        NumberField price = new NumberField("price");
        Button submit = new Button("submit");
        setHorizontalComponentAlignment(Alignment.END,submit);
//        submit.setWidth("50%");
        HorizontalLayout addI = new HorizontalLayout(itemName,category), add2 = new HorizontalLayout(imageLink,aboutItem),
                add3 = new HorizontalLayout(sellerName,passWord), add4 = new HorizontalLayout(price,submit);
        Div d = new Div(addI,add2,add3,add4);
        addItem.addClickListener(event -> {
            d.setVisible(true);
        });
        d.getStyle().set("border","1px gray solid").set("border-radius","25px").set("margin","10px").set("padding","8px");
            hide(d);
        submit.addClickListener(event -> {
            hide(d);
            if(r.findByUserName(sellerName.getValue())!=null &&
                    r.findByUserName(sellerName.getValue()).getPassword().equals(passWord.getValue())){
                if(itemName.isEmpty() || category.isEmpty()|| aboutItem.isEmpty()|| imageLink.isEmpty()|| price.isEmpty())
                    Notification.show("please fill all fields");
                else{
                    Item newI = new Item(itemName.getValue(),category.getValue(),aboutItem.getValue(),imageLink.getValue(),
                    Double.parseDouble(price.getValue()+""),r.findByUserName(sellerName.getValue()));
                    i.save(newI);
                    grid.setItems(i.findAll());
                }}
            else if(wrongAtt>=2){
                UI.getCurrent().getPage().setLocation("SignUp");
            }
            else {Notification.show("invalid username please sign up");
            wrongAtt++;
            }
            itemName.clear(); category.clear(); aboutItem.clear(); imageLink.clear(); price.clear(); sellerName.clear();
        });
        add(h,addItem,grid,d);




    }
//    public Component popup(){
//        PopupButton popupButton = new PopupButton("Action");
//
//        HorizontalLayout popupLayout = new HorizontalLayout();
//
////        popupButton.add(popupLayout);
//        Button modifyButton = new Button("Modify");
//        popupLayout.add(modifyButton);
//        Button addButton = new Button("Add");
//        popupLayout.add(addButton);
//        Button deleteButton = new Button("Delete");
//        popupLayout.add(deleteButton);
//return popupLayout;
//    }
public void hide(Div d){
    d.setVisible(false);

}


}
