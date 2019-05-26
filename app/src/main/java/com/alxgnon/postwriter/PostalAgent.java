package com.alxgnon.postwriter;

import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

class PostalAgent {

    private static final int VIBRATE_MILLISECONDS = 15;
    private static final int VIBRATE_AMPLITUDE = VibrationEffect.DEFAULT_AMPLITUDE;

    private final MainInterface mContext;
    private final Vibrator mVibrator;

    private final View.OnClickListener mListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String content = trimEditorContent();
            if (content.isEmpty()) return;

            submitPost(content);
        }
    };

    PostalAgent(MainInterface context, Vibrator vibrator) {
        mContext = context;
        mVibrator = vibrator;
    }

    void submitOnPress() {
        mContext.getSubmitButton().setOnClickListener(mListener);
    }

    private void submitPost(String content) {
        String filename = PostFilesystem.saveFile(content);

        if (filename == null) {
            alertSavingError();

        } else {
            triggerHapticEffect();
            clearEditor();
        }
    }

    private String trimEditorContent() {
        return mContext.getEditor().getText().toString().trim();
    }

    private void alertSavingError() {
        Snackbar.make(mContext.getContainer(),
                "Could not save file to external storage!", Snackbar.LENGTH_LONG).show();
    }

    private void triggerHapticEffect() {
        mVibrator.vibrate(
                VibrationEffect.createOneShot(VIBRATE_MILLISECONDS, VIBRATE_AMPLITUDE));
    }

    private void clearEditor() {
        mContext.getEditor().setText("");
    }
}
