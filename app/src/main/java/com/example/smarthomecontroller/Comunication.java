package com.example.smarthomecontroller;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

public class Comunication extends AppCompatActivity {

    String address =null;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth =null;

    BluetoothSocket btSocket =null;
    BluetoothDevice remotedevice ;
    BluetoothServerSocket mmServer;

    private boolean isBtConnected =false;
    static final UUID myUUID =UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    Button led_on,led_off,btngarajac,btngarajkapat,btnalarmac,btnalarmkapat,btnalarmac2,btnalarmkapat2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comunicaiton);

        Intent newint =getIntent();
        address =newint.getStringExtra(MainActivity.EXTRA_ADRESS);

        new BTbaglan().execute();

        led_off=(Button)findViewById(R.id.led_off);
        led_on=(Button)findViewById(R.id.led_on);
        btngarajac=(Button)findViewById(R.id.btngarajac);
        btngarajkapat=(Button)findViewById(R.id.btngarajkapat);
        btnalarmac=(Button)findViewById(R.id.btnalarmac);
        btnalarmkapat=(Button)findViewById(R.id.btnalarmkapat);
        btnalarmac2=(Button)findViewById(R.id.btnalarmac2);
        btnalarmkapat2=(Button)findViewById(R.id.btnalarmkapat2);





        led_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btSocket!=null)
                {
                    try {
                        btSocket.getOutputStream().write("1".toString().getBytes());

                    }
                    catch (IOException e)
                    {

                    }
                }
            }
        });

        led_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btSocket!=null)
                {
                    try {
                        btSocket.getOutputStream().write("2".toString().getBytes());

                    }
                    catch (IOException e)
                    {

                    }
                }
            }
        });

        btngarajac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btSocket!=null)
                {
                    try {
                        btSocket.getOutputStream().write("3".toString().getBytes());

                    }
                    catch (IOException e)
                    {

                    }
                }
            }
        });
        btngarajkapat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btSocket!=null)
                {
                    try {
                        btSocket.getOutputStream().write("4".toString().getBytes());

                    }
                    catch (IOException e)
                    {

                    }
                }
            }
        });
        btnalarmac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btSocket!=null)
                {
                    try {
                        btSocket.getOutputStream().write("5".toString().getBytes());

                    }
                    catch (IOException e)
                    {

                    }
                }
            }
        });
        btnalarmkapat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btSocket!=null)
                {
                    try {
                        btSocket.getOutputStream().write("6".toString().getBytes());

                    }
                    catch (IOException e)
                    {

                    }
                }
            }
        });
        btnalarmac2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btSocket!=null)
                {
                    try {
                        btSocket.getOutputStream().write("7".toString().getBytes());

                    }
                    catch (IOException e)
                    {

                    }
                }
            }
        });
        btnalarmkapat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btSocket!=null)
                {
                    try {
                        btSocket.getOutputStream().write("8".toString().getBytes());

                    }
                    catch (IOException e)
                    {

                    }
                }
            }
        });
    }



    private void Disconnect(){
        if(btSocket!=null){
            try {
                btSocket.close();
            } catch (IOException e){
                // msg("Error");
            }
        }
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Disconnect();
    }

    private class BTbaglan extends AsyncTask<Void, Void, Void> {
        private boolean ConnectSuccess = true;

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(Comunication.this, "Baglanıyor...", "Lütfen Bekleyin");
        }


        @Override
        protected Void doInBackground(Void... devices) {
            try {
                if (btSocket == null || !isBtConnected) {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();
                    BluetoothDevice cihaz = myBluetooth.getRemoteDevice(address);
                    btSocket = cihaz.createInsecureRfcommSocketToServiceRecord(myUUID);
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();
                }
            } catch (IOException e) {
                ConnectSuccess = false;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (!ConnectSuccess) {
                // msg("Baglantı Hatası, Lütfen Tekrar Deneyin");
                Toast.makeText(getApplicationContext(),"Bağlantı Hatası Tekrar Deneyin", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                //   msg("Baglantı Basarılı");
                Toast.makeText(getApplicationContext(),"Bağlantı Başarılı",Toast.LENGTH_SHORT).show();

                isBtConnected = true;
                //Ümit Demirkol
            }
            progress.dismiss();
        }

    }

}