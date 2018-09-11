package abhishekint.com.rxjavatest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Maininterface{

    TextView textView;
    Button button;
    PresenterInterface presenterInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=(TextView)findViewById(R.id.tv);
        button=(Button)findViewById(R.id.btn);
        presenterInterface=new Presenter(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("inside onclikc","inside onclic");
                presenterInterface.simpleRx();
            }
        });
    }

    @Override
    public void updateText(String s) {
        textView.setText(s);
    }

    @Override
    public void showToast(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}
