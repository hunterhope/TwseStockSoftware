/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.db.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

/**
 *
 * @author user
 */
@Entity
public record PreferStock(@Id String stockId,String name,String date,String lateWatchDate,int watchTime,String close,String priceDif){
}
   
    
    
    

    

