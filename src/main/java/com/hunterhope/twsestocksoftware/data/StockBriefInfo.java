/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.data;

import java.util.Objects;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

/**
 *
 * @author user
 */
public class StockBriefInfo {

    private String id;
    private String name="";
    private String date;
    private String close;
    private String price_dif;
    private int concernedTime;
    private final StringProperty fullNameProperty=new SimpleStringProperty();
    private final StringProperty dateProperty = new SimpleStringProperty();
    private final StringProperty priceProperty = new SimpleStringProperty();
    private final ObjectProperty<Paint> colorProperty = new SimpleObjectProperty<>();
    private final BooleanProperty updateProperty = new SimpleBooleanProperty(true);

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getPrice_dif() {
        return price_dif;
    }

    public void setPrice_dif(String price_dif) {
        this.price_dif = price_dif;
    }

    public int getConcernedTime() {
        return concernedTime;
    }

    public void setConcernedTime(int concernedTime) {
        this.concernedTime = concernedTime;
    }

    private int upDownEq() {
        return price_dif==null?-1:price_dif.contains("+") ? 0 : price_dif.contains("-") ? 1 : 2;
    }

    public Text priceText() {
        Text priceText = new Text();
        updatePricePropertyHelper();
        priceText.textProperty().bind(priceProperty);
        priceText.fillProperty().bind(colorProperty);
        return priceText;
    }

    private void updatePricePropertyHelper() {
        switch (upDownEq()) {
            case 0:
                priceProperty.set(close + " ▲ " + price_dif);
                colorProperty.set(Color.RED);
                break;
            case 1:
                priceProperty.set(close + " ▼ " + price_dif);
                colorProperty.set(Color.GREEN);
                break;
            case 2:
                priceProperty.set(close + "   " + price_dif);
                colorProperty.set(Color.BLACK);
                break;
            default:
                break;
        }
    }

    public void updatePriceProperty(String close, String price_dif) {
        this.close = close;
        this.price_dif = price_dif;
        updatePricePropertyHelper();
    }

    public Text fullNameText() {
        Text fullNametext = new Text();
        fullNameProperty.set(id+" "+name);
        fullNametext.textProperty().bind(fullNameProperty);
        return fullNametext;
    }
    public void updateFullNameProperty(String name){
        this.name = name;
        fullNameProperty.set(id+" "+name);
    }
    public Text conceredTimeText() {
        return new Text("👓 " + concernedTime);
    }

    public Text dateText() {
        Text dateText = new Text();
        dateProperty.set(date);
        dateText.textProperty().bind(dateProperty);
        return dateText;
    }

    public void updateDateProperty(String date) {
        this.date = date;
        dateProperty.set(date);
    }

    public void updateUpdateProperty(boolean update) {
        updateProperty.set(update);
    }

    public ProgressBar updateProgressBar() {
        ProgressBar pb = new ProgressBar();
        pb.visibleProperty().bind(updateProperty);
        return pb;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.date);
        hash = 67 * hash + Objects.hashCode(this.close);
        hash = 67 * hash + Objects.hashCode(this.price_dif);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StockBriefInfo other = (StockBriefInfo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.close, other.close)) {
            return false;
        }
        return Objects.equals(this.price_dif, other.price_dif);
    }

    @Override
    public String toString() {
        return "StockBriefInfo{" + "id=" + id + ", name=" + name + ", date=" + date + ", close=" + close + ", price_dif=" + price_dif + ", concernedTime=" + concernedTime + '}';
    }
}
