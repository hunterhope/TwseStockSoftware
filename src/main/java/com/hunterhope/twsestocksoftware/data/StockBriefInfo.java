/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.data;

import java.util.Objects;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 *
 * @author user
 */
public class StockBriefInfo {

    private String id;
    private String name;
    private String date;
    private String close;
    private String price_dif;
    private int concernedTime;
    
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
        return price_dif.contains("+") ? 0 : price_dif.contains("-") ? 1 : 2;
    }

    
    public Text getPriceText() {
        Text text = new Text();
        switch (upDownEq()) {
            case 0:
                text.setText(close + " ▲ " + price_dif);
                text.setFill(Color.RED);
                break;
            case 1:
                text.setText(close + " ▼ " + price_dif);
                text.setFill(Color.GREEN);
                break;
            default:
                text.setText(close + "   " + price_dif);
                text.setFill(Color.BLACK);
                break;
        }
        return text;
    }

    public Text getFullNameText() {
        return new Text(id + " " + name);
    }

    public Text getConceredTimeText() {
        return new Text("👓 " + concernedTime);
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
