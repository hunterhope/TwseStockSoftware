/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.data;

import java.util.Objects;

/**
 *
 * @author user
 */
public class PreferStock {
    private String id;
    private String name;
    private int concernedTime;
    private String lastWatchDate;

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

    public int getConcernedTime() {
        return concernedTime;
    }

    public void setConcernedTime(int concernedTime) {
        this.concernedTime = concernedTime;
    }

    public String getLastWatchDate() {
        return lastWatchDate;
    }

    public void setLastWatchDate(String lastWatchDate) {
        this.lastWatchDate = lastWatchDate;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.id);
        hash = 11 * hash + Objects.hashCode(this.name);
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
        final PreferStock other = (PreferStock) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        return "PreferStock{" + "id=" + id + ", name=" + name + ", concernedTime=" + concernedTime + ", lastWatchDate=" + lastWatchDate + '}';
    }
    
}
