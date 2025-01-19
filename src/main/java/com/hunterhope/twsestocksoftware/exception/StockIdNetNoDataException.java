/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.exception;

/**
 *
 * @author user
 */
public class StockIdNetNoDataException extends Exception{

    public StockIdNetNoDataException(String stockId) {
        super("網路上沒此股票代號資料:"+stockId);
    }
    
}
