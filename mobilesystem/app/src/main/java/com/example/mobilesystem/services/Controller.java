package com.example.mobilesystem.services;

import com.example.mobilesystem.services.util.AsyncRequest;

public class Controller {
    private static Controller controller;
    private AsyncRequest asyncRequest=new AsyncRequest();

    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

    public AsyncRequest getAsyncRequest() {
        return asyncRequest;
    }
}
