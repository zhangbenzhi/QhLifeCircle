package com.dankegongyu.lib.share;

public interface DKShareCallback {

    void onStart(String platform);

    void onResult(String platform, boolean success);
}