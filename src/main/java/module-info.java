module com.hunterhope.twsestocksoftware {
    requires javafx.controls;
    requires TwseStockId;
    exports com.hunterhope.twsestocksoftware;
    exports com.hunterhope.twsestocksoftware.componet to TwseStockSoftwareTest;
    exports com.hunterhope.twsestocksoftware.viewModel to TwseStockSoftwareTest;
    exports com.hunterhope.twsestocksoftware.repository to TwseStockSoftwareTest;
    exports com.hunterhope.twsestocksoftware.data to TwseStockSoftwareTest;
}
