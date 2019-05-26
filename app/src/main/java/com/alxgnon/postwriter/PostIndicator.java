package com.alxgnon.postwriter;

import android.text.Editable;
import android.text.TextWatcher;

class PostIndicator {

    private final MainInterface mContext;

    private final TextWatcher mWatcher = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            updateIndicator(s.toString());
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start,
                                      int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start,
                                  int before, int count) {
        }
    };

    PostIndicator(MainInterface context) {
        mContext = context;
    }

    void watchContent() {
        mContext.getEditor().addTextChangedListener(mWatcher);
    }

    private void updateIndicator(String content) {
        if (content.trim().isEmpty()) {
            mContext.getSubmitButton().setAlpha(0.2f);
        } else {
            mContext.getSubmitButton().setAlpha(0.8f);
        }
    }
}
