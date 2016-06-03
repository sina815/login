package book.course.molareza.ir.login2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivitySignUp extends AppCompatActivity {

    private EditText edtUser, edtPass, edtEmail;
    private TextView txtLogin, txtMessage;
    private Button btnSignUp;

    private String username;
    private String password;
    private String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edtUser = (EditText) findViewById(R.id.edtUser2);
        edtPass = (EditText) findViewById(R.id.edtPass2);
        edtEmail = (EditText) findViewById(R.id.edtEmail2);
        txtLogin = (TextView) findViewById(R.id.txtLogin2);
        txtMessage = (TextView) findViewById(R.id.txtMessage2);
        btnSignUp = (Button) findViewById(R.id.btnSignUp2);


    }
}
