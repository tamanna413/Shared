package com.example.user.shared;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText name, mail, pswd;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        mail = findViewById(R.id.mail);
        pswd = findViewById(R.id.pswd);
        btn = findViewById(R.id.btn);



        btn .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//button click when filled
                if(TextUtils.isEmpty(name.getText().toString())){
                    name.setError("Field is  empty");
                    return;
                }

                if(TextUtils.isEmpty(mail.getText().toString())){
                    mail.setError("Field is empty");
                    return;
                }

                if(TextUtils.isEmpty(pswd.getText().toString())){
                    pswd.setError("Field is empty");
                    return;
                }

                String userName, userpswd, usermail = null;

                userName = name.getText().toString();
                usermail = mail.getText().toString();
                userpswd = pswd.getText().toString();

//                entering data

//                1SHaredpref strt here
                SharedPreferences sp = getSharedPreferences("detail", MODE_PRIVATE);

//                2 get editor
                SharedPreferences.Editor editor = sp.edit();

                editor.putString("name", userName);
                editor.putString("class", usermail);
                editor.putString("age", userpswd);

//                3close editor
                editor.commit();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("Submit Conformation");
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });

                builder.show();

            }
        });

    }
}

