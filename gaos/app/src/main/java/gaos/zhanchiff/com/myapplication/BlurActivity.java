package gaos.zhanchiff.com.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class BlurActivity extends AppCompatActivity {
    public static void launchForResult(Activity activity,String which) {
        Intent intent = new Intent(activity, BlurActivity.class);
        intent.putExtra("from",which);
        activity.startActivity(intent);
    }

    private Bitmap bitmap;
    private ImageView mImg;
    private TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blur);
        mText = findViewById(R.id.info);
        mText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder sb  = new StringBuilder();
                mImg.setImageBitmap(BlurUtil.blurBitmap(BlurActivity.this,bitmap,20f,0.2f,sb));
                mText.setOnClickListener(null);
                mText.setText(sb.toString());
            }
        });
        mImg = findViewById(R.id.img);
        bitmap = getImageFromAssetsFile();
        mImg.setImageBitmap(bitmap);
    }

    private Bitmap getImageFromAssetsFile() {
        String fileName = getIntent().getStringExtra("from");
        Bitmap image = null;
        AssetManager am = getResources().getAssets();
        try
        {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return image;

    }
}
