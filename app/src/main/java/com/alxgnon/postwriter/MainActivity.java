package com.alxgnon.postwriter;

import android.app.Service;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends PostMemory {

    @Override
    public View getContainer() {
        return findViewById(R.id.container);
    }

    @Override
    public EditText getEditor() {
        return findViewById(R.id.editor);
    }

    @Override
    public FloatingActionButton getSubmitButton() {
        return findViewById(R.id.fab);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ThemeSwitcher themeSwitcher = new ThemeSwitcher(this);
        setContentView(R.layout.activity_main);
        themeSwitcher.toggleOnLongPress();

        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        new PostalAgent(this, vibrator).submitOnPress();
        new PostIndicator(this).watchContent();

        new PostPermissions(this).requestAccess();
    }
}
