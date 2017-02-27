package in.co.ismdhanbad.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Method;

public class MainActivity
        extends AppCompatActivity
        implements View.OnClickListener,View.OnLongClickListener {

    //objects
    private Button createHotspot;
    private Button connect;
    private Button reset;
    private ImageView i1,i2,i3,i4,i5,i6,i7,ii;
    private CountDownTimer countDownTimer;

    //firebase services
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ChildEventListener childEventListener;
    private ChildEventListener mChildEventListener;

    //variables
    private int i = 0,z= 0,pi=0,pn=0,y;
    private int pc = y = Color.argb(150,246,238,25);
    private int r = Color.argb(150,250,0,0);
    private int g = Color.argb(150,0,250,0);
    private int[][] n = new int[8][2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Warn");

        createHotspot = (Button) findViewById(R.id.createHotspotBtn);
        connect  = (Button) findViewById(R.id.connectBtn);
        reset = (Button) findViewById(R.id.resetBtn);

        i1=(ImageView) findViewById(R.id.i1);
        i2=(ImageView) findViewById(R.id.i2);
        i3=(ImageView) findViewById(R.id.i3);
        i4=(ImageView) findViewById(R.id.i4);
        i5=(ImageView) findViewById(R.id.i5);
        i6=(ImageView) findViewById(R.id.i6);
        i7=(ImageView) findViewById(R.id.i7);
        i1.setOnClickListener(this);
        i2.setOnClickListener(this);
        i3.setOnClickListener(this);
        i4.setOnClickListener(this);
        i5.setOnClickListener(this);
        i6.setOnClickListener(this);
        i7.setOnClickListener(this);
        i1.setOnLongClickListener(this);i2.setOnLongClickListener(this);i3.setOnLongClickListener(this);i4.setOnLongClickListener(this);i5.setOnLongClickListener(this);
        i6.setOnLongClickListener(this);i7.setOnLongClickListener(this);

//        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//        if(!wifiManager.isWifiEnabled())
//            wifiManager.setWifiEnabled(true);

        for(int j=1;j<=7;j++){
            n[j][0] = 0;
            n[j][1] = pc;
        }

        Log.d("yyyyyyy", y + "");

        createHotspot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attachUpdateListener();
            }
        });

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        attachSetListener();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attachResetListener();
            }
        });

        //receiveBrodcast();


    }

    private void attachResetListener(){
        if(childEventListener != null) {
            databaseReference.removeEventListener(childEventListener);
            childEventListener = null;
        }
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Message message1 = dataSnapshot.getValue(Message.class);
                message1.setColor(-1762202087);
                databaseReference.child(dataSnapshot.getKey()).setValue(message1);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        databaseReference.addChildEventListener(childEventListener);
    }

    private void attachSetListener(){
        if(mChildEventListener != null) {
            databaseReference.removeEventListener(mChildEventListener);
            mChildEventListener = null;
        }
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Message message1 = dataSnapshot.getValue(Message.class);
                setColor(message1.getRoom(),message1.getColor());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Message message1 = dataSnapshot.getValue(Message.class);
                setColor(message1.getRoom(),message1.getColor());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        databaseReference.addChildEventListener(mChildEventListener);
    }

    private void attachUpdateListener(){
        if(childEventListener != null) {
            databaseReference.removeEventListener(childEventListener);
            childEventListener = null;
        }
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Message message1 = dataSnapshot.getValue(Message.class);
                if(message1.getRoom() == pi) {
                    message1.setColor(n[pi][1]);
                    pi = 0;
                }
                databaseReference.child(dataSnapshot.getKey()).setValue(message1);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        databaseReference.addChildEventListener(childEventListener);

    }

//    private void receiveBrodcast(){
//
//        z = 0;
//
//        countDownTimer = new CountDownTimer(300000, 10000) {
//
//            public void onTick(long millisUntilFinished) {
//                Log.d("NetworkCheckReceiver", "connectedadasdsd"+z);
////                z++;
//                WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//                if(!wifiManager.isWifiEnabled())
//                    wifiManager.setWifiEnabled(true);
//                ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//                NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//
//                if (!mWifi.isConnected()) {
//                    z ++;
//                    if(z>12)
//                        z=1;
//                    Log.d("NetworkCheckReceiver", "connected"+z);
//                    WifiConfiguration wifiConfig = new WifiConfiguration();
//                    wifiConfig.SSID = String.format("\"%s\"", "Nexus" + z);
//                    wifiConfig.preSharedKey = String.format("\"%s\"", "12345678");
//
//                    int netId = wifiManager.addNetwork(wifiConfig);
//                    wifiManager.disconnect();
//                    wifiManager.enableNetwork(netId, true);
//                    wifiManager.reconnect();
//                }
//                else{
//                    Log.d("NetworkCheckReceiver", "disconnected");
//                    countDownTimer.cancel();
//                    countDownTimer.onFinish();
//                    setColor(z);
//                    configApState(MainActivity.this,z);
//                }
//            }
//
//            public void onFinish() {
//
//            }
//        };
//        countDownTimer.start();
//    }


    public void setColor(int i, int col){
        switch(i){
            case 1:
                i1.setColorFilter(col);
                n[1][0]=1;
                n[1][1]=col;
                break;
            case 2:
                i2.setColorFilter(col);
                i4.setColorFilter(col);
                n[2][1]=col;
                break;
            case 3:
                i3.setColorFilter(col);
                n[3][1]=col;
                break;
            case 4:
                i5.setColorFilter(col);
                n[4][1]=col;
                break;
            case 5:
                i6.setColorFilter(col);
                n[5][1]=col;
                break;
            case 6:
                i7.setColorFilter(col);
                n[6][1]=col;
                break;
        }
    }

    //check whether wifi hotspot on or off
    public static boolean isApOn(Context context) {
        WifiManager wifimanager = (WifiManager) context.getSystemService(context.WIFI_SERVICE);
        try {
            Method method = wifimanager.getClass().getDeclaredMethod("isWifiApEnabled");
            method.setAccessible(true);
            return (Boolean) method.invoke(wifimanager);
        }
        catch (Throwable ignored) {}
        return false;
    }

    // toggle wifi hotspot on or off
    public boolean configApState(Context context,int x) {
        WifiManager wifimanager = (WifiManager) context.getSystemService(context.WIFI_SERVICE);
        WifiConfiguration wificonfiguration = new WifiConfiguration();
        wificonfiguration.SSID = "Nexus" + x;
        wificonfiguration.preSharedKey = "12345678";
        wificonfiguration.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
        wificonfiguration.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
        wificonfiguration.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
        wificonfiguration.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);

        try {
            // if WiFi is on, turn it off
            WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if(wifiManager.isWifiEnabled())
                wifiManager.setWifiEnabled(false);
            Method method = wifimanager.getClass().getMethod("setWifiApEnabled", WifiConfiguration.class, boolean.class);
            method.invoke(wifimanager, wificonfiguration, !isApOn(context));
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void getClientList() {
        int macCount = 0;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("/proc/net/arp"));
            String line;
            while ((line = br.readLine()) != null) {
                String[] splitted = line.split(" +");
                if (splitted != null ) {
                    // Basic sanity check
                    String mac = splitted[3];
                    System.out.println("Mac : Outside If "+ mac );
                    if (mac.matches("..:..:..:..:..:..")) {
                        macCount++;
                   /* ClientList.add("Client(" + macCount + ")");
                    IpAddr.add(splitted[0]);
                    HWAddr.add(splitted[3]);
                    Device.add(splitted[5]);*/
                        System.out.println("Mac : "+ mac + " IP Address : "+splitted[0] );
                        System.out.println("Mac_Count  " + macCount + " MAC_ADDRESS  "+ mac);
                        Toast.makeText(
                                getApplicationContext(),
                                "Mac_Count  " + macCount + "   MAC_ADDRESS  "
                                        + mac, Toast.LENGTH_SHORT).show();

                    }
               /* for (int i = 0; i < splitted.length; i++)
                    System.out.println("Addressssssss     "+ splitted[i]);*/

                }
            }
        } catch(Exception e) {

        }
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.i1:
                i1.setColorFilter(Color.argb(150, 250, 0, 0));
                int pn1=n[1][0];
                n[1][0]=-1;
                Log.d("piasbsacu",pi+"");
                if(pi!=0){
                    n[pi][0]=pn;
                    ii.setColorFilter(pc);
                    n[pi][1]=pc;
                    if(ii==i2){
                        i4.setColorFilter(pc);

                    }
                }
                ii=i1;
                pi=1;
                pn=pn1;
                pc=n[1][1];
                n[1][1]=r;
                break;
            case R.id.i2:
                i2.setColorFilter(Color.argb(150, 250, 0, 0));i4.setColorFilter(Color.argb(150, 250, 0, 0));
                pn1=n[2][0];
                n[2][0]=-1;
                Log.d("piasbsacu",pi+"");
                if(pi!=0){
                    n[pi][0]=pn;
                    ii.setColorFilter(pc);
                    n[pi][1]=pc;
                    if(ii==i2){
                        i4.setColorFilter(pc);
                    }
                }
                ii=i2;
                pi=2;
                pn=pn1;
                pc=n[2][1];
                n[2][1]=r;
                break;
            case R.id.i3:
                i3.setColorFilter(Color.argb(150, 250, 0, 0));
                pn1=n[3][0];
                n[3][0]=-1;
                Log.d("piasbsacu",pi+"");
                if(pi!=0){
                    n[pi][0]=pn;
                    ii.setColorFilter(pc);
                    n[pi][1]=pc;
                    if(ii==i2){
                        i4.setColorFilter(pc);
                    }
                }
                ii=i3;
                pi=3;
                pn=pn1;
                pc=n[3][1];
                n[3][1]=r;
                break;
            case R.id.i4:
                i4.setColorFilter(Color.argb(150, 250, 0, 0));i2.setColorFilter(Color.argb(150, 250, 0, 0));
                pn1=n[2][0];
                n[2][0]=-1;
                Log.d("piasbsacu",pi+"");
                if(pi!=0){
                    n[pi][0]=pn;
                    ii.setColorFilter(pc);
                    n[pi][1]=pc;
                    if(ii==i2){
                        i4.setColorFilter(pc);
                    }
                }
                ii=i2;
                pi=2;
                pn=pn1;
                pc=n[2][1];
                n[2][1]=r;
                break;
            case R.id.i5:
                i5.setColorFilter(Color.argb(150, 250, 0, 0));
                pn1=n[4][0];
                n[4][0]=-1;
                Log.d("piasbsacu",pi+"");
                if(pi!=0){
                    n[pi][0]=pn;
                    ii.setColorFilter(pc);
                    n[pi][1]=pc;
                    if(ii==i2){
                        i4.setColorFilter(pc);

                    }
                }
                ii=i5;
                pi=4;
                pn=pn1;
                pc=n[4][1];
                n[4][1]=r;
                break;
            case R.id.i6:
                i6.setColorFilter(Color.argb(150, 250, 0, 0));
                pn1=n[5][0];
                n[5][0]=-1;
                Log.d("piasbsacu",pi+"");
                if(pi!=0){
                    n[pi][0]=pn;
                    ii.setColorFilter(pc);
                    n[pi][1]=pc;
                    if(ii==i2){
                        i4.setColorFilter(pc);
                    }
                }
                ii=i6;
                pi=5;
                pn=pn1;
                pc=n[5][1];
                n[5][1]=r;
                break;
            case R.id.i7:
                i7.setColorFilter(Color.argb(150, 250, 0, 0));
                pn1=n[6][0];
                n[6][0]=-1;
                Log.d("piasbsacu",pi+"");
                if(pi!=0){
                    n[pi][0]=pn;
                    ii.setColorFilter(pc);
                    n[pi][1]=pc;
                    if(ii==i2){
                        i4.setColorFilter(pc);
                    }
                }
                ii=i7;
                pi=6;
                pn=pn1;
                pc=n[6][1];
                n[6][1]=r;
                break;

        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch(v.getId()){
            case R.id.i1:
                i1.setColorFilter(g);
                int pn1=n[1][0];
                n[1][0]=1;
                if(pi!=0){
                    n[pi][0]=pn;
                    ii.setColorFilter(pc);
                    n[pi][1]=pc;
                    if(ii==i2){
                        i4.setColorFilter(pc);
                    }
                }
                ii=i1;
                pi=1;
                pn=pn1;
                pc=n[1][1];
                n[1][1]=g;
                break;
            case R.id.i2:
                i2.setColorFilter(g);
                i4.setColorFilter(g);
                pn1=n[2][0];
                n[2][0]=1;
                if(pi!=0){
                    n[pi][0]=pn;
                    ii.setColorFilter(pc);
                    n[pi][1]=pc;
                    if(ii==i2){
                        i4.setColorFilter(pc);
                    }
                }
                ii=i2;
                pi=2;
                pn=pn1;
                pc=n[2][1];
                n[2][1]=g;
                break;
            case R.id.i3:
                i3.setColorFilter(g);
                pn1=n[3][0];
                n[3][0]=1;
                if(pi!=0){
                    n[pi][0]=pn;
                    ii.setColorFilter(pc);
                    n[pi][1]=pc;
                    if(ii==i2){
                        i4.setColorFilter(pc);
                    }
                }
                ii=i3;
                pi=3;
                pn=pn1;
                pc=n[3][1];
                n[3][1]=g;
                break;
            case R.id.i4:
                i4.setColorFilter(g);
                i2.setColorFilter(g);
                pn1=n[2][0];
                n[2][0]=1;
                if(pi!=0){
                    n[pi][0]=pn;
                    ii.setColorFilter(pc);
                    n[pi][1]=pc;
                    if(ii==i2){
                        i4.setColorFilter(pc);
                    }
                }
                ii=i2;
                pi=2;
                pn=pn1;
                pc=n[2][1];
                n[2][1]=g;
                break;
            case R.id.i5:
                i5.setColorFilter(g);
                pn1=n[4][0];
                n[4][0]=1;
                if(pi!=0){
                    n[pi][0]=pn;
                    ii.setColorFilter(pc);
                    n[pi][1]=pc;
                    if(ii==i2){
                        i4.setColorFilter(pc);
                    }
                }
                ii=i5;
                pi=4;
                pn=pn1;
                pc=n[4][1];
                n[4][1]=g;
                break;
            case R.id.i6:
                i6.setColorFilter(g);
                pn1=n[5][0];
                n[5][0]=1;
                if(pi!=0){
                    n[pi][0]=pn;
                    ii.setColorFilter(pc);
                    n[pi][1]=pc;
                    if(ii==i2){
                        i4.setColorFilter(pc);
                    }
                }
                ii=i6;
                pi=5;
                pn=pn1;
                pc=n[5][1];
                n[5][1]=g;
                break;
            case R.id.i7:
                i7.setColorFilter(g);
                pn1=n[6][0];
                n[6][0]=1;
                if(pi!=0){
                    n[pi][0]=pn;
                    ii.setColorFilter(pc);
                    n[pi][1]=pc;
                    if(ii==i2){
                        i4.setColorFilter(pc);
                    }
                }
                ii=i7;
                pi=6;
                pn=pn1;
                pc=n[6][1];
                n[6][1]=g;
                break;

        }

        return true;
    }




}
