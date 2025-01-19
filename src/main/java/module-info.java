module com.hunterhope.twsestocksoftware {
    requires javafx.controls;
    requires TwseStockId;
    requires TwseDB;
    exports com.hunterhope.twsestocksoftware;
    exports com.hunterhope.twsestocksoftware.componet to TwseStockSoftwareTest;
    exports com.hunterhope.twsestocksoftware.viewModel to TwseStockSoftwareTest;
    exports com.hunterhope.twsestocksoftware.repository to TwseStockSoftwareTest;
    exports com.hunterhope.twsestocksoftware.data to TwseStockSoftwareTest;
    exports com.hunterhope.twsestocksoftware.exception to TwseStockSoftwareTest;
    opens com.hunterhope.twsestocksoftware.data;
}
