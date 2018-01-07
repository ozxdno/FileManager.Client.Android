package ozxdno.filemanager;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.*;

import ozxdno.filemanager.Activities.LoginActivity;
import ozxdno.filemanager.Activities.PictureActivity;
import ozxdno.filemanager.Communication.*;
import ozxdno.filemanager.Local.Manager;
import ozxdno.filemanager.Resource.Enums;
import ozxdno.filemanager.Resource.Users;

/**
 * 1 - 播放开始动画
 * 2 - 加载本地配置文件
 * 3 - 开启服务
 * 4 - 跳转登陆界面（若自动登陆，则跳转显示界面）
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView image = (ImageView)findViewById(R.id.bootAnimation);
        image.setBackgroundColor(Color.BLUE);
    }

    @Override
    protected void onResume(){
        super.onResume();
        // show pic or gif
        jumpto.start();
    }

    private Thread jumpto = new Thread() {
        public void run() {
            if(Users.getCurrentUser() != null && Users.getCurrentUser().getState() == Enums.UserState_Online) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, PictureActivity.class);
                startActivity(intent);
                MainActivity.super.finish();
                return;
            }

            try {
                Manager.synchroLocal();
                Thread.sleep(1000);
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                MainActivity.super.finish();
            } catch (Exception e) {
                ;
            }

        }
    };
}
