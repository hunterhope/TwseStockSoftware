/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.repository.test;

import com.hunterhope.twsestocksoftware.repository.PreferStockRepository;
import com.hunterhope.twsestocksoftware.repository.StockDayInfoRepository;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

/**
 *
 * @author user
 */
public class PreferStockRepositoryTest {
    
    @Test
    public void searchNetData_2330_successed()throws Exception{
        StockDayInfoRepository sdir = Mockito.mock(StockDayInfoRepository.class);
        Mockito.when(sdir.queryLatestDayInfo(Mockito.any())).thenReturn(Map.of("date", "114/03/14","close", "959.00","priceDif", "-6.00"));
        //測試物件
        PreferStockRepository psr = new PreferStockRepository(sdir);
        //跑起來
        Map<String, String> result = psr.searchNetData("2330 台積電");
        //驗證 114/03/14	42,394,920	40,803,705,615	965.00	969.00	955.00	959.00	-6.00	163,735
        Map<String, String> exp = new HashMap<>();
        exp.put("date", "114/03/14");
        exp.put("close", "959.00");
        exp.put("priceDif", "-6.00");
        Assertions.assertEquals(exp,result);
    }
}
