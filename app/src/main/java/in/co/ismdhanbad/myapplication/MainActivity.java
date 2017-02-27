package in.co.ismdhanbad.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Method;

public class MainActivity
        extends AppCompatActivity
        implements View.OnClickListener,View.OnLongClickListener {

    private Button createHotspot;
    private Button connect;
    private Button getList;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ChildEventListener childEventListener;
    private int i = 0,z= 0,pi=0,pn=0;
    int pc = Color.argb(150,246,238,25);
    ImageView i1,i2,i3,i4,i5,i6,i7,ii;
    int[][] n = new int[8][2];

    String safe,unsafe;
    TextView saf,unsaf,sl,ul;
    Button bc;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseDatabase = firebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference().child("Warn");

        createHotspot = (Button) findViewById(R.id.createHotspotBtn);
        connect  = (Button) findViewById(R.id.connectBtn);

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

        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if(!wifiManager.isWifiEnabled())
            wifiManager.setWifiEnabled(true);

        for(int j=1;j<=7;j++){
            n[j][0] = 0;
            n[j][1] = pc;
        }

        createHotspot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = pi*2;
                if(n[pi][0]==-1){
                    x--;
                }
                pi = 0;
                configApState(MainActivity.this,x);
            }
        });

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        receiveBrodcast();


    }

    private void receiveBrodcast(){

        z = 0;

        countDownTimer = new CountDownTimer(300000, 10000) {

            public void onTick(long millisUntilFinished) {
                Log.d("NetworkCheckReceiver", "connectedadasdsd"+z);
//                z++;
                WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                if(!wifiManager.isWifiEnabled())
                    wifiManager.setWifiEnabled(true);
                ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

                if (!mWifi.isConnected()) {
                    z ++;
                    if(z>12)
                        z=1;
                    Log.d("NetworkCheckReceiver", "connected"+z);
                    WifiConfiguration wifiConfig = new WifiConfiguration();
                    wifiConfig.SSID = String.format("\"%s\"", "Nexus" + z);
                    wifiConfig.preSharedKey = String.format("\"%s\"", "12345678");

                    int netId = wifiManager.addNetwork(wifiConfig);
                    wifiManager.disconnect();
                    wifiManager.enableNetwork(netId, true);
                    wifiManager.reconnect();
                }
                else{
                    Log.d("NetworkCheckReceiver", "disconnected");
                    countDownTimer.cancel();
                    countDownTimer.onFinish();
                    setColor(z);
                    configApState(MainActivity.this,z);
                }
            }

            public void onFinish() {

            }
        };
        countDownTimer.start();
    }


    public void setColor(int i){
        switch(i){
            case 1:
                i1.setColorFilter(Color.argb(150,250,0,0));
                n[1][0]=1;
                n[1][1]=Color.argb(150,250,0,0);
                break;
            case 2:
                i1.setColorFilter(Color.argb(150,0,250,0));
                n[1][0]=1;
                n[1][1]=Color.argb(150,0,250,0);
                break;
            case 3:
                i2.setColorFilter(Color.argb(150,250,0,0));
                i4.setColorFilter(Color.argb(150,250,0,0));
                n[2][0]=1;
                n[2][1]=Color.argb(150,250,0,0);
                break;
            case 4:
                i2.setColorFilter(Color.argb(150,0,250,0));
                i4.setColorFilter(Color.argb(150,0,250,0));
                n[2][0]=1;
                n[2][1]=Color.argb(150,0,250,0);
                break;
            case 5:
                i3.setColorFilter(Color.argb(150,250,0,0));
                n[3][0]=1;
                n[3][1]=Color.argb(150,250,0,0);
                break;
            case 6:
                i3.setColorFilter(Color.argb(150,0,250,0));
                n[3][0]=1;
                n[3][1]=Color.argb(150,0,250,0);
                break;
            case 7:
                i5.setColorFilter(Color.argb(150,250,0,0));
                n[4][0]=1;
                n[4][1]=Color.argb(150,250,0,0);
                break;
            case 8:
                i5.setColorFilter(Color.argb(150,0,250,0));
                n[4][0]=1;
                n[4][1]=Color.argb(150,0,250,0);
                break;
            case 9:
                i6.setColorFilter(Color.argb(150,250,0,0));
                n[5][0]=1;
                n[5][1]=Color.argb(150,250,0,0);
                break;
            case 10:
                i6.setColorFilter(Color.argb(150,0,250,0));
                n[5][0]=1;
                n[5][1]=Color.argb(150,0,250,0);
                break;
            case 11:
                i7.setColorFilter(Color.argb(150,250,0,0));
                n[6][0]=1;
                n[6][1]=Color.argb(150,250,0,0);
                break;
            case 12:
                i7.setColorFilter(Color.argb(150,0,250,0));
                n[6][0]=1;
                n[6][1]=Color.argb(150,0,250,0);
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
                n[1][1]=Color.argb(150,250,0,0);
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
                n[2][1]=Color.argb(150,250,0,0);
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
                n[3][1]=Color.argb(150,250,0,0);
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
                n[2][1]=Color.argb(150,250,0,0);
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
                n[4][1]=Color.argb(150,250,0,0);
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
                n[5][1]=Color.argb(150,250,0,0);
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
                n[6][1]=Color.argb(150,250,0,0);
                break;

        }
    }

    @Override
    public boolean onLongClick(View v) {
        switch(v.getId()){
            case R.id.i1:
                i1.setColorFilter(Color.argb(150,0,250,0));
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
                n[1][1]=Color.argb(150,0,250,0);
                break;
            case R.id.i2:
                i2.setColorFilter(Color.argb(150,0,250,0));
                i4.setColorFilter(Color.argb(150,0,250,0));
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
                n[2][1]=Color.argb(150,0,250,0);
                break;
            case R.id.i3:
                i3.setColorFilter(Color.argb(150,0,250,0));
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
                n[3][1]=Color.argb(150,0,250,0);
                break;
            case R.id.i4:
                i4.setColorFilter(Color.argb(150,0,250,0));
                i2.setColorFilter(Color.argb(150,0,250,0));
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
                n[2][1]=Color.argb(150,0,250,0);
                break;
            case R.id.i5:
                i5.setColorFilter(Color.argb(150,0,250,0));
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
                n[4][1]=Color.argb(150,0,250,0);
                break;
            case R.id.i6:
                i6.setColorFilter(Color.argb(150,0,250,0));
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
                n[5][1]=Color.argb(150,0,250,0);
                break;
            case R.id.i7:
                i7.setColorFilter(Color.argb(150,0,250,0));
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
                n[6][1]=Color.argb(150,0,250,0);
                break;

        }

        return true;
    }




}
