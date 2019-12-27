package com.example.sensor;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    SensorManager sensorManager;
    Sensor s1,s2,s3;
    private static final String TAG = "MainActivity";
    TextView t1,t2,t3;
   ProgressBar p1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        s1=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        s2=sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        s3=sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        sensorManager.registerListener(MainActivity.this,s1,sensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(MainActivity.this,s2,sensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(MainActivity.this,s3,sensorManager.SENSOR_DELAY_NORMAL);
        t1=(TextView)findViewById(R.id.textView);
        t2=(TextView)findViewById(R.id.textView2);
        t3=(TextView)findViewById(R.id.textView3);
        p1=(ProgressBar)findViewById(R.id.progressBar3);


    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor=sensorEvent.sensor;
        if(sensor.getType()==Sensor.TYPE_LIGHT) {
            Log.d(TAG, "onSensorChanged: " + sensorEvent.values[0]);
            //t1.setText("xyz"+String.valueOf(sensorEvent.values[0]));
            compute(sensorEvent);

        }
        if(sensor.getType()==Sensor.TYPE_GYROSCOPE){
            t2.setText("Gyroscope value--->"+sensorEvent.values[0]+"+"+sensorEvent.values[1]);
        }

        if(sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD){
            t3.setText("Magnetic Field value--->"+sensorEvent.values[0]);
        }



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    public void compute(SensorEvent sensorEvent){

            t1.setText("Light intensity in LUX--->"+String.valueOf(sensorEvent.values[0]));
            int i1= Math.round(sensorEvent.values[0]*10);
            int i2=Math.round(sensorEvent.values[0]);
            p1.setProgress(i2);



    }
}
