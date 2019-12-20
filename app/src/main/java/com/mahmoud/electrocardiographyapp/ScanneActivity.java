package com.mahmoud.electrocardiographyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelUuid;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.UUID;

public class ScanneActivity extends AppCompatActivity {

    StringBuilder message;

    BluetoothAdapter myBluetooth;
    Set<BluetoothDevice> piaredDevices;
    BluetoothSocket btSocket;

    String addrss,name;

    Thread thread;

    BluetoothDevice mBTDevice;

    public String TAG="Scanne Activity";

    Handler handler;
    private static UUID MY_UUID_INSECURE=UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    //  private static final UUID MY_UUID_INSECURE=UUID.fromString("00-21-31-00-0800200c9a66");


    TextView txt_disease;

    @Override
    protected void onResume() {
        super.onResume();
        startConnection();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanne);

        txt_disease=findViewById(R.id.txt_disease);

        try {
            myBluetooth=BluetoothAdapter.getDefaultAdapter();
            addrss=myBluetooth.getAddress();
            piaredDevices=myBluetooth.getBondedDevices();
            if (piaredDevices.size()>0){
                for (BluetoothDevice bt:piaredDevices)
                {
                    addrss=bt.getAddress().toString();
                    name=bt.getName().toString();

                    //   Toast.makeText(this, addrss+" "+name, Toast.LENGTH_SHORT).show();
                    if (addrss.equals("")||name.equals("HC-05")){
                        mBTDevice=bt;
                        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
                        Method getUuidsMethod = BluetoothAdapter.class.getDeclaredMethod("getUuids", null);
                        ParcelUuid[] uuids = (ParcelUuid[]) getUuidsMethod.invoke(adapter, null);
                        mBTDevice.getUuids();
                        for (ParcelUuid uuid: uuids) {
                            //  Toast.makeText(this, uuid.getUuid().toString(), Toast.LENGTH_SHORT).show();
                            MY_UUID_INSECURE=uuid.getUuid();
                        }
                    }

                }
            }
        }catch (Exception we){
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
        }

        startConnection();

    }

    public void startConnection(){
        try {
            startBTConnection(mBTDevice,MY_UUID_INSECURE);
        }catch (Exception e){

        }
    }

    //starting service method
    public void startBTConnection(BluetoothDevice device,UUID uuid){
        Log.d(TAG,"startBTConnection: Initializing RFCOM Bluetooth Connection.");
        if(device!=null){
            //  Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();
            //  Toast.makeText(this, uuid.toString(), Toast.LENGTH_SHORT).show();
            // mBluetoothConnection=new BluetoothConnectionService(LevelsActivity.this,uuid);
            //  mBluetoothConnection.startClient(device,uuid);
            //  mBluetoothConnection.write("1_on".getBytes());
            myBluetooth=BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
            device=myBluetooth.getRemoteDevice(device.getAddress());//connects to the device's address and check if it's available
            try {
                btSocket=device.createInsecureRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));//create a RFCOMM (SPP) connection
                btSocket.connect();
                Connection connection=new Connection(btSocket);
                thread=new Thread(connection);
                thread.start();


            } catch (IOException e) {
                // Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                try {
                    btSocket.close();
                    btSocket=device.createInsecureRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));//create a RFCOMM (SPP) connection
                    btSocket.connect();
                    Connection connection=new Connection(btSocket);
                    thread=new Thread(connection);
                    thread.start();

                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        }else {
            //  Toast.makeText(this, "There is no device to cinnect", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            if(thread.isAlive()){
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){

        }

    }

    public class Connection extends Thread{

        BluetoothSocket socket;

        public Connection( BluetoothSocket socket){
            this.socket=socket;
            //   Toast.makeText(LevelsActivity.this, "hi", Toast.LENGTH_SHORT).show();
            //  Thread thread = new Thread(this);
            //  thread.start();
        }
        public void run(){

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            while (true){
                try {
                    Log.d(TAG,"run start");
                    byte[] buffer=new byte[1024];// buffer store for the stream
                    int bytes;//bytes returned from read();
                    bytes=socket.getInputStream().read(buffer);
                    final String incomingMessage=new String(buffer,0,bytes);
                    // but_low.setText("hi");
                    Log.d(TAG,incomingMessage);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (incomingMessage.equalsIgnoreCase("a")){
                                txt_disease.setText("Left bundle branch block disease (LBBB)");
                            }else if (incomingMessage.equalsIgnoreCase("b")){
                                txt_disease.setText("Right Bundle Branch Block disease (RBBB");
                            }else if (incomingMessage.equalsIgnoreCase("c")){
                                txt_disease.setText("Ventricular flutter (VFL)");
                            }else if (incomingMessage.equalsIgnoreCase("d")){
                                txt_disease.setText("Idioventricular rhythm");
                            }else if (incomingMessage.equalsIgnoreCase("e")){
                                txt_disease.setText("PR heart disease");
                            }else if (incomingMessage.equalsIgnoreCase("f")){
                                txt_disease.setText("NORMAL SINUS HEART RATE");
                            }else if (incomingMessage.equalsIgnoreCase("g")){
                                txt_disease.setText("Atrial Premature Beats(APB)");
                            }
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
