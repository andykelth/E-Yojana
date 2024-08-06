package com.example.myapplication;

public interface ResponseCallback {
    void onResponse(String response);
    void onError(Throwable throwable);
}
