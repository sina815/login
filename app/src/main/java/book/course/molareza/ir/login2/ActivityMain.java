package book.course.molareza.ir.login2;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class ActivityMain extends AppCompatActivity {

    private EditText edtName;
    private ImageView imgPic;
    private Button btnSelect, btnSend;

    private int mRequestCode = 1;
    private Bitmap mBitmap;
    private String finalImageString;

    private String name;

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = (EditText) findViewById(R.id.edtName);
        imgPic = (ImageView) findViewById(R.id.imgPic);
        btnSelect = (Button) findViewById(R.id.btnSelect);
        btnSend = (Button) findViewById(R.id.btnSend);

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picCamera();
            }
        });

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = edtName.getText().toString();


                if (imgPic != null && name.length() > 0) {

                    send();

                }
                else {
                    Toast.makeText(ActivityMain.this, "items is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void send() {

        requestQueue = Volley.newRequestQueue(G.context);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, G.URL_IMAGE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject object = new JSONObject(response);
                    String state = object.getString("state");
                    Toast.makeText(ActivityMain.this, "" + state, Toast.LENGTH_SHORT).show();


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ActivityMain.this, "can not connect to server", Toast.LENGTH_SHORT).show();
            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<>();

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                byte[] bImage = outputStream.toByteArray();

                String encodeImage = Base64.encodeToString(bImage, Base64.DEFAULT);

                byte[] bName = name.getBytes();
                String encodeName = Base64.encodeToString(bName, Base64.DEFAULT);

                params.put("name", encodeName);
                params.put("image", encodeImage);

                return params;
            }
        };

        requestQueue.add(stringRequest);
    }

    private void picCamera() {
        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {

            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, mRequestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == mRequestCode && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            mBitmap = (Bitmap) bundle.get("data");

            showImage();

        }
    }

    private void showImage() {

        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityMain.this);
        View view = G.inflater.inflate(R.layout.full_image_dialog, (ViewGroup) findViewById(R.id.layoutRoot), false);
        builder.setView(view);

        ImageView imgFullSize = (ImageView) view.findViewById(R.id.imgFullSizePic);
        imgFullSize.setImageBitmap(mBitmap);

        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                imgPic.setImageBitmap(mBitmap);
            }
        });

        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setNeutralButton("again", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                picCamera();
            }
        });

        builder.create().show();

    }
}
