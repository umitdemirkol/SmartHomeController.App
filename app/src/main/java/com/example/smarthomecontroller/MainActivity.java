package com.example.smarthomecontroller;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smarthomecontroller.R;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {


    BluetoothAdapter mybluetooth;
    private Set<BluetoothDevice> pairedBT;
    Button toggle_button,btn_listele;
    ListView pairedlist;
    public static String EXTRA_ADRESS="device_address";
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggle_button=(Button)findViewById(R.id.btntoggle);
        mybluetooth=BluetoothAdapter.getDefaultAdapter();
        btn_listele=(Button)findViewById(R.id.btnlistBT);
        pairedlist=(ListView)findViewById(R.id.devicelist);

        toggle_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglebluetooth();
            }


        });

        btn_listele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Listdevice();
            }


        });
    }

    private void togglebluetooth() {
        if (mybluetooth==null)
        {
            Toast.makeText(getApplicationContext(),"Bluetooth bağlantısı kurulamadı",Toast.LENGTH_SHORT).show();
        }
        if (!mybluetooth.isEnabled())
        {
            Intent enableBTIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(enableBTIntent);
        }
        if (mybluetooth.isEnabled())
        {
            mybluetooth.disable();
        }

    }
    private void Listdevice()
    {
        pairedBT=mybluetooth.getBondedDevices();
        ArrayList list= new ArrayList();

        if (pairedBT.size() > 0)
        {
            for (BluetoothDevice bt: pairedBT)
            {
                list.add(bt.getName()+"\n"+bt.getAddress());
            }

        }
        else
        {
            Toast.makeText(getApplicationContext(),"Bluetooth cihazı bulunamadı",Toast.LENGTH_SHORT).show();
        }

        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        pairedlist.setAdapter(adapter);
        pairedlist.setOnItemClickListener(selectdevice);

    }
    public AdapterView.OnItemClickListener selectdevice = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String info=((TextView) view).getText().toString();
            String adress =info.substring(info.length()-17);

            Intent comintent = new Intent(MainActivity.this,Comunication.class);
            comintent.putExtra(EXTRA_ADRESS,adress);
            startActivity(comintent);

        }
    };
}