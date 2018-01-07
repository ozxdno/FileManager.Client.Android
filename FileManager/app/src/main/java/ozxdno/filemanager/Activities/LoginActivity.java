package ozxdno.filemanager.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ozxdno.filemanager.Communication.Client_CMD;
import ozxdno.filemanager.Model.UserModel;
import ozxdno.filemanager.R;
import ozxdno.filemanager.Resource.Users;

/**
 * Created by ozxdn on 2018/01/07.
 *
 * 01 - 登陆
 * 02 - 同步远程数据
 */

public class LoginActivity extends Activity {

    private boolean updata = true;
    private EditText name;
    private EditText password;
    /**
     * -2 - 未登陆
     * -1 - 正在登陆
     * 00 - 登陆成功
     * XX - 错误代码
     */
    private int loginState = -2;
    private Button login;
    private Button offline;
    private Client_CMD cmd;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name = (EditText)findViewById(R.id.loginName);
        password = (EditText)findViewById(R.id.loginPassword);
        login = (Button)findViewById(R.id.loginBtn);
        offline = (Button)findViewById(R.id.offlineBtn);

        login.setOnClickListener(Click_LoginBtn);
        offline.setOnClickListener(Click_OfflineBtn);
    }

    public void onResume() {
        super.onResume();

        handler.post(timer);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(loginState == -2) {
                login.setText("Login");
            }
            if(loginState == -1) {
                login.setText("Waiting");
            }
            if(loginState == 0) {
                login.setText("Successed");
                Users.setCurrentUser(new UserModel(name.getText().toString(),password.getText().toString()));
                nextActivity();
            }
            if(loginState > 0) {
                login.setText("Failed");
            }

            handler.post(timer);
        }
    };
    private Runnable timer = new Runnable() {
        public void run() {
            Message msg = handler.obtainMessage();
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(updata) {
                handler.sendMessage(msg);
            }
        }
    };

    private View.OnClickListener Click_LoginBtn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(loginState != -1 && loginState != 0) {
                loginning.start();
            }
        }
    };

    private Thread loginning = new Thread() {
        public void run() {
            cmd = new Client_CMD();
            loginState = -1;
            loginState = cmd.login(name.getText().toString(),password.getText().toString());
        }
    };

    private View.OnClickListener Click_OfflineBtn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Users.setOfflineUser();
            nextActivity();
        }
    };

    private void nextActivity() {
        if(cmd != null) {
            cmd.stop();
        }
        updata = false;
        handler.removeCallbacks(timer);
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this, PictureActivity.class);
        startActivity(intent);
        LoginActivity.this.finish();
    }
}
