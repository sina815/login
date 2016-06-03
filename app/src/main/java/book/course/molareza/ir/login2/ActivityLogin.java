package book.course.molareza.ir.login2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityLogin extends AppCompatActivity {

    private EditText edtUser, edtPass;
    private TextView txtSignUp, txtMessage;
    private Button btnLogin;

    private String username;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUser = (EditText) findViewById(R.id.edtUser);
        edtPass = (EditText) findViewById(R.id.edtPass);
        txtSignUp = (TextView) findViewById(R.id.txtSignUp);
        txtMessage = (TextView) findViewById(R.id.txtMessage);
        btnLogin = (Button) findViewById(R.id.btnLogin);


    }
}
