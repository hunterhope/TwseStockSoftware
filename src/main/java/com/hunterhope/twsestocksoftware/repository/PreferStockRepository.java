/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.repository;

import com.hunterhope.twsestocksoftware.exception.StockIdNetNoDataException;
import java.util.Map;

/**
 *
 * @author user
 */
public class PreferStockRepository {

    private final StockDayInfoRepository sdir;
    
    public PreferStockRepository(StockDayInfoRepository sdir) {
        this.sdir = sdir;
    }
    public Map<String, String> searchNetData(String stockFullName) throws StockIdNetNoDataException {
        String[] split = stockFullName.split(" ");
        Map<String, String> result=sdir.queryLatestDayInfo(split[0]);
        //存入偏好資料庫
        return Map.of("date", result.get("date"),
                "close", result.get("close"),
                "priceDif", result.get("priceDif"));
    }

    
}
