package com.example.mycomponent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class MyComponent extends LinearLayout {

    private View rootView;
    private EditText editText;
    private Button button;

    // Este es el que va a crear el componente
    public MyComponent(Context context) {
        super(context);
        init(context);
    }

    // Este es el que va a dar atributos al componente
    public MyComponent(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        rootView = inflate(context,R.layout.erasable_component,this);
        editText = rootView.findViewById(R.id.editText);
        button = rootView.findViewById(R.id.button);
        button.setText("Borrar");
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });
    }
}
