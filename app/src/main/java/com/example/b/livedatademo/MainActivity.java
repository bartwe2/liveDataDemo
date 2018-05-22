package com.example.b.livedatademo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private myViewModel mModel;
    private TextView nameView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Ustawienie layoutu
        setContentView(R.layout.activity_main);
        nameView = findViewById(R.id.name);
        editText = findViewById(R.id.editText);

        // Pobranie ViewModel
        mModel = ViewModelProviders.of(this).get(myViewModel.class);

        // Utworzenie obserwatora który aktualizuje UI
        final Observer<String> nameObserver = new Observer<String>() {
            @Override
            public void onChanged(String newName) {
                // Automatyczna aktualizacja textView
                nameView.setText(newName);
            }};

        // Obserwuj LiveData przekazując aktualne activity (jako LifecycleOwner) i obserwatora
        mModel.getCurrentName().observe(this, nameObserver);
    }

    // przypisanie akcji przyciskowi
    public void buttonClick(View v){
        mModel.getCurrentName().setValue(editText.getText().toString());
    }
}
