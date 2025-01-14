/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hunterhope.twsestocksoftware.other;

import java.util.function.Consumer;
import javafx.concurrent.Task;

/**
 *
 * @author user
 */
public abstract class HasErrorHandelTask<T> extends Task<T> {
    private final Consumer<String> errorMsgAction;
    public HasErrorHandelTask(Consumer<String> errorMsgAction) {
        this.errorMsgAction=errorMsgAction;
    }
    @Override
    protected void failed() {
        super.failed();
        //將失敗原因通知使用者
        String[] msgs = getException().getMessage().split(" ");
        errorMsgAction.accept(msgs.length > 1 ? msgs[1] : msgs[0]);
    }
}
