package book.course.molareza.ir.login2;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;

public class G extends Application {

    public static Context context;
    public static LayoutInflater inflater;

    public static final String URL_LOGIN = "http://192.168.1.35/login/login.php";
    public static final String URL_REG = "http://192.168.1.35/login/register.php";
    public static final String URL_IMAGE = "http://192.168.1.35/login/image.php";


    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
}
