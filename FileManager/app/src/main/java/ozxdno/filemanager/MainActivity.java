package ozxdno.filemanager;

/*
 * 1 - 播放开始动画
 * 2 - 加载本地配置文件
 * 3 - 跳转登陆界面（若自动登陆，则跳转显示界面）
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.*;

import ozxdno.filemanager.Communication.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView text = (TextView)findViewById(R.id.testText);
        Button btn = (Button)findViewById(R.id.testButton);

        btn.setOnClickListener(new btnListener());
    }

    private class btnListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Client_TCP.start();
            ((Button)findViewById(R.id.testButton)).setText("clicked");
            Client_TCP.send("clicked");
        }
    }
}
