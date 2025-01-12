/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/module-info.java to edit this template
 */

module TwseStockSoftwareTest {
    requires com.hunterhope.twsestocksoftware;
    requires TwseStockId;
    requires javafx.controls;
    requires org.junit.jupiter.api;
    requires org.testfx.junit5;
    requires org.assertj.core;
    requires org.mockito;
    exports com.hunterhope.twsestocksoftware.componet.test;
    exports com.hunterhope.twsestocksoftware.viewmodel.test;
}
