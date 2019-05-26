package com.alxgnon.postwriter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

abstract class PostMemory extends Activity implements MainInterface {

    private static final String KEY_DRAFT = "com.alxgnon.postwriter.DRAFT";

    private String getEditorContent() {
        return getEditor().getText().toString();
    }

    private void backupContent(String content) {
        getPreferences(Context.MODE_PRIVATE)
                .edit()
                .putString(KEY_DRAFT, content)
                .apply();
    }

    private String restoreContent() {
        return getPreferences(Context.MODE_PRIVATE)
                .getString(KEY_DRAFT, "");
    }

    @Override
    protected void onStop() {
        backupContent(getEditorContent());
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getEditor().setText(restoreContent());
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        bundle.putString(KEY_DRAFT, getEditorContent());
        super.onSaveInstanceState(bundle);
    }

    @Override
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        getEditor().setText(bundle.getString(KEY_DRAFT));
    }
}
