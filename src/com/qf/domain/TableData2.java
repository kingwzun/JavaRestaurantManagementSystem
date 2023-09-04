package com.qf.domain;

import javafx.beans.property.SimpleStringProperty;

public class TableData2 {
    private SimpleStringProperty id;//序号
    private SimpleStringProperty dish1;
    private SimpleStringProperty dish2;
    private SimpleStringProperty dish3;
    private SimpleStringProperty dish4;
    private SimpleStringProperty dish5;
    private SimpleStringProperty dish6;
    private SimpleStringProperty dish7;
    private SimpleStringProperty dish8;

    public TableData2() {

    }
    public TableData2(String id, String dish1, String dish2, String dish3, String dish4,String dish5,String dish6,String dish7,String dish8) {
        this.id = new SimpleStringProperty(id);
        this.dish1 = new SimpleStringProperty(dish1);
        this.dish2 = new SimpleStringProperty(dish2);
        this.dish3 = new SimpleStringProperty(dish3);
        this.dish4 = new SimpleStringProperty(dish4);
        this.dish5 = new SimpleStringProperty(dish5);
        this.dish6 = new SimpleStringProperty(dish6);
        this.dish7= new SimpleStringProperty(dish7);
        this.dish8 = new SimpleStringProperty(dish8);
    }



    public void setId(String id) {
        this.id = new SimpleStringProperty(id);
    }

    public void setDish1(String dish1) {
        this.dish1 = new SimpleStringProperty(dish1);
    }

    public void setDish2(String dish2) {
        this.dish2 = new SimpleStringProperty(dish2);
    }

    public void setDish3(String dish3) {
        this.dish3 = new SimpleStringProperty(dish3);
    }

    public void setDish4(String dish4) {
        this.dish4 = new SimpleStringProperty(dish4);
    }

    public void setDish5(String dish5) {
        this.dish5 = new SimpleStringProperty(dish5);
    }

    public void setDish6(String dish6) {
        this.dish6 = new SimpleStringProperty(dish6);
    }

    public void setDish7(String dish7) { this.dish7= new SimpleStringProperty(dish7);
    }

    public void setDish8(String dish8) {
        this.dish8 = new SimpleStringProperty(dish8);
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public String getDish1() {
        return dish1.get();
    }

    public SimpleStringProperty dish1Property() {
        return dish1;
    }

    public String getDish2() {
        return dish2.get();
    }

    public SimpleStringProperty dish2Property() {
        return dish2;
    }

    public String getDish3() {
        return dish3.get();
    }

    public SimpleStringProperty dish3Property() {
        return dish3;
    }

    public String getDish4() {
        return dish4.get();
    }

    public SimpleStringProperty dish4Property() {
        return dish4;
    }

    public String getDish5() {
        return dish5.get();
    }

    public SimpleStringProperty dish5Property() {
        return dish5;
    }

    public String getDish6() {
        return dish6.get();
    }

    public SimpleStringProperty dish6Property() {
        return dish6;
    }

    public String getDish7() {
        return dish7.get();
    }

    public SimpleStringProperty dish7Property() {
        return dish7;
    }

    public String getDish8() {
        return dish8.get();
    }

    public SimpleStringProperty dish8Property() {
        return dish8;
    }
}
