module com.hunterhope.twsestocksoftware {
    requires javafx.controls;
    requires TwseStockId;
    requires TwseDB;
    requires jakarta.persistence;
    requires spring.data.jpa;
    requires spring.core;
    requires spring.beans;
    requires spring.context;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires java.instrument;
    exports com.hunterhope.twsestocksoftware;
    exports com.hunterhope.twsestocksoftware.componet to TwseStockSoftwareTest;
    exports com.hunterhope.twsestocksoftware.viewModel to TwseStockSoftwareTest;
    exports com.hunterhope.twsestocksoftware.repository to TwseStockSoftwareTest;
    exports com.hunterhope.twsestocksoftware.data to TwseStockSoftwareTest;
    exports com.hunterhope.twsestocksoftware.exception to TwseStockSoftwareTest;
    opens com.hunterhope.twsestocksoftware.data;
    opens com.hunterhope.twsestocksoftware;
    opens com.hunterhope.twsestocksoftware.db.entity;
}
