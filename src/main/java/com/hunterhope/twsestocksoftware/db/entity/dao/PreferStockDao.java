/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.hunterhope.twsestocksoftware.db.entity.dao;

import com.hunterhope.twsestocksoftware.db.entity.PreferStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author user
 */
@Repository
public interface PreferStockDao extends JpaRepository<PreferStock, String>{
}
