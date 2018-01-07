package ozxdno.filemanager.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

/**
 * Created by ozxdn on 2018/01/07.
 *
 * 已用静态方法实现了
 *
 */

public class TCP_Communication_Service extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // 写具体的操作
        // 比如：发送，接收等

        return super.onStartCommand(intent, flags, startId);
    }
}
