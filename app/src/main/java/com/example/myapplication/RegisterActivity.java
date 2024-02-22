package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.myapplication.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.rbMale.setSelected(true);
        String ageArray[] = {"18","19","20","21","22","23"};
        ArrayAdapter adapter = new ArrayAdapter<>(getApplication(), android.R.layout.simple_spinner_item,ageArray);
        binding.spAge.setAdapter(adapter);

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = binding.etFirstName.getText().toString();
                String lastName = binding.etLastName.getText().toString();
                String gender = "";
                String password = binding.etPassword.getText().toString();
                String age = "";
                age = binding.spAge.getSelectedItem().toString();
                if(binding.rbMale.isSelected())
                {
                    gender = "male";
                }
                else
                {
                    gender = "female";
                }
                if(firstName.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this, "First name cannot be empty!", Toast.LENGTH_SHORT).show();
                }
                else if(lastName.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this, "Last name cannot be empty!", Toast.LENGTH_SHORT).show();
                }
                else if(password.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this, "Password cannot be empty!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    SharedPreferences sp = getSharedPreferences("DB",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("FirstName",firstName);
                    editor.putString("LastName",lastName);
                    editor.putString("Gender",gender);
                    editor.putString("Password",password);

                    editor.apply();
                    Toast.makeText(RegisterActivity.this, "You have been registered successfully!", Toast.LENGTH_SHORT).show();

                }
            }
        });
        binding.tvHyperlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });


    }
}