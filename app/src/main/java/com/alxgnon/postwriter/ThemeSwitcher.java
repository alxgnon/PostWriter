package com.alxgnon.postwriter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;

class ThemeSwitcher {

    private static final String KEY_DARK_MODE = "com.alxgnon.postwriter.DARK_MODE";

    private final MainActivity mContext;
    private boolean mDarkMode;

    private final View.OnLongClickListener mListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View view) {
            setDarkMode(!mDarkMode);
            mContext.recreate();
            return true;
        }
    };

    ThemeSwitcher(MainActivity context) {
        mContext = context;
        mDarkMode = getDarkModePreference();
        applyTheme();
    }

    void toggleOnLongPress() {
        mContext.getSubmitButton().setOnLongClickListener(mListener);
    }

    private void applyTheme() {
        if (mDarkMode) {
            mContext.setTheme(R.style.DarkTheme);
        }
    }

    @SuppressLint("ApplySharedPref")
    private void setDarkMode(boolean darkMode) {
        mDarkMode = darkMode;
        mContext.getPreferences(Context.MODE_PRIVATE)
                .edit()
                .putBoolean(KEY_DARK_MODE, darkMode)
                .commit();
    }

    private boolean getDarkModePreference() {
        return mContext.getPreferences(Context.MODE_PRIVATE)
                .getBoolean(KEY_DARK_MODE, false);
    }
}
