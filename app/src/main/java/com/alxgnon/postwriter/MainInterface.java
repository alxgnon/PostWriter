package com.alxgnon.postwriter;

import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

interface MainInterface {

    View getContainer();

    EditText getEditor();

    FloatingActionButton getSubmitButton();
}