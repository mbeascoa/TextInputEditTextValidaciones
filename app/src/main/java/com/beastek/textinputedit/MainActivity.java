package com.beastek.textinputedit;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    TextInputLayout pass, mail;
    TextInputEditText password, emailedittext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button submit = findViewById(R.id.submit);
        pass = (TextInputLayout)findViewById(R.id.pass);
        mail = (TextInputLayout)findViewById(R.id.mail);
        password = (TextInputEditText) findViewById(R.id.password);
        emailedittext = (TextInputEditText) findViewById(R.id.email);
        password.addTextChangedListener(new ValidationTextWatcher(password));
        emailedittext.addTextChangedListener(new ValidationTextWatcher(emailedittext));
        // Capture button clicks
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                if (!validatePassword()) {
                return;
            }
                if (!validateEmail()) {
                    return;
                }
            }
        });
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private boolean validatePassword() {
        if (password.getText().toString().trim().isEmpty()) {
            pass.setError("Password is required");
            requestFocus(password);
            return false;
        }else if(password.getText().toString().length() < 6){
            pass.setError("Password can't be less than 6 digit");
            requestFocus(password);
            return false;
        }
        else {
            pass.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateEmail() {
        if (emailedittext.getText().toString().trim().isEmpty()) {
            mail.setErrorEnabled(false);
        } else {
            String emailId = emailedittext.getText().toString();
            Boolean  isValid = android.util.Patterns.EMAIL_ADDRESS.matcher(emailId).matches();
            if (!isValid) {
                mail.setError("Invalid Email address, ex: abc@example.com");
                requestFocus(emailedittext);
                return false;
            } else {
                mail.setErrorEnabled(false);
            }
        }
        return true;
    }

    private class ValidationTextWatcher implements TextWatcher {
        private View view;
        private ValidationTextWatcher(View view) {
        this.view = view;
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }
    public void afterTextChanged(Editable editable) {
        switch (view.getId()) {
            case R.id.password:
                validatePassword();
                break;
            case R.id.email:
                validateEmail();
                break;
        }
    }
    }
}