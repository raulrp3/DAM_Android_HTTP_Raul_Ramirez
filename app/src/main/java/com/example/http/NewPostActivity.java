package com.example.http;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewPostActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnSave;
    EditText etId,etUserId,etTitle,etBody;

    private final int CODE_SAVE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        initUI();
    }

    private void initUI(){
        etId = findViewById(R.id.edittext_newId);
        etUserId = findViewById(R.id.edittext_newUserId);
        etTitle = findViewById(R.id.edittext_newTitle);
        etBody = findViewById(R.id.edittext_newBody);

        btnSave = findViewById(R.id.button_save);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String id = etId.getText().toString().trim();
        String userId = etUserId.getText().toString().trim();
        String title = etTitle.getText().toString().trim();
        String body = etBody.getText().toString().trim();

        Intent intent = new Intent();
        intent.putExtra("id", id);
        intent.putExtra("userid", userId);
        intent.putExtra("title", title);
        intent.putExtra("body", body);

        setResult(CODE_SAVE, intent);
        finish();
    }
}
