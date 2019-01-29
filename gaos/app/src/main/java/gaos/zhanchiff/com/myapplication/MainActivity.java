package gaos.zhanchiff.com.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.test1).setOnClickListener(this);
        findViewById(R.id.test2).setOnClickListener(this);
        findViewById(R.id.test3).setOnClickListener(this);
        findViewById(R.id.test4).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.test1:
                BlurActivity.launchForResult(this,"1.jpg");
                break;
            case R.id.test2:
                BlurActivity.launchForResult(this,"2.jpg");
                break;
            case R.id.test3:
                BlurActivity.launchForResult(this,"3.jpg");
                break;

            case R.id.test4:
                BlurActivity.launchForResult(this,"4.jpg");
                break;

        }
    }
}
