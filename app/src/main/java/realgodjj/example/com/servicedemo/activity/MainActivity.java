package realgodjj.example.com.servicedemo.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ServiceConfigurationError;

import realgodjj.example.com.servicedemo.R;
import realgodjj.example.com.servicedemo.service.MyService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = "RealgodJJ";

    private Button btStartService;
    private Button btStopService;
    private Button btBindService;
    private Button btUnbindService;
    private ServiceConnection serviceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btStartService = findViewById(R.id.bt_start_service);
        btStopService = findViewById(R.id.bt_stop_service);
        btBindService = findViewById(R.id.bt_bind_service);
        btUnbindService = findViewById(R.id.bt_unbind_service);
        btStartService.setOnClickListener(this);
        btStopService.setOnClickListener(this);
        btBindService.setOnClickListener(this);
        btUnbindService.setOnClickListener(this);
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                MyService.MyBinder myBinder = (MyService.MyBinder) service;
                Log.d(TAG, "onServiceConnected: " + myBinder.getProcess());
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_start_service:
                Intent intent = new Intent(this, MyService.class);
                startService(intent);
                break;

            case R.id.bt_stop_service:
                Intent intent1 = new Intent(this, MyService.class);
                stopService(intent1);
                break;

            case R.id.bt_bind_service:
                Intent intent2 = new Intent(this, MyService.class);
                bindService(intent2, serviceConnection, BIND_AUTO_CREATE);
                break;

            case R.id.bt_unbind_service:
                if (serviceConnection != null)
                    unbindService(serviceConnection);
                break;
        }
    }
}
