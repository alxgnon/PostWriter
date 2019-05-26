package com.alxgnon.postwriter;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;

class PostPermissions {

    private static final String[] sRequired =
            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private static final int sTheAnswer = 4242;

    private final Activity mContext;

    PostPermissions(Activity context) {
        mContext = context;
    }

    void requestAccess() {
        if (isAccessDenied()) {
            mContext.requestPermissions(sRequired, sTheAnswer);
        }
    }

    private boolean isAccessDenied() {
        return mContext.checkSelfPermission(sRequired[0])
                != PackageManager.PERMISSION_GRANTED;
    }
}
