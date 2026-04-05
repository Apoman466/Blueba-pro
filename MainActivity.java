package com.bbgaming.blueba.tr;

import android.app.*;
import android.os.*;
import android.bluetooth.*;
import android.view.*;
import android.widget.*;
import android.content.*;
import java.io.*;
import java.util.*;

public class MainActivity extends Activity {
    private BluetoothAdapter myBluetooth = null;
    private BluetoothSocket btSocket = null;
    private OutputStream outputStream;
    private final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_main);

            myBluetooth = BluetoothAdapter.getDefaultAdapter();
            if (myBluetooth == null) {
                Toast.makeText(this, "Bluetooth Desteklenmiyor!", Toast.LENGTH_LONG).show();
                return;
            }

            if (!myBluetooth.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }

            // Buton Dinleyicilerini Başlat
            initControllers();

            // Bağlan Butonu
            View btnConnect = findViewById(R.id.btnConnect);
            if (btnConnect != null) {
                btnConnect.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							listPairedDevices();
						}
					});
            }

            // Ayarlar Butonu
            View btnSettings = findViewById(R.id.btnSettings);
            if (btnSettings != null) {
                btnSettings.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							showInfoDialog();
						}
					});
            }

        } catch (Exception e) {
            Toast.makeText(this, "Başlatma Hatası: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void initControllers() {
        // Hareketler
        bindTouch(R.id.btnF, "F");
        bindTouch(R.id.btnB, "B");
        bindTouch(R.id.btnL, "L");
        bindTouch(R.id.btnR, "R");
        // Aksiyonlar
        bindTouch(R.id.btnTriangle, "1");
        bindTouch(R.id.btnX, "2");
        bindTouch(R.id.btnSquare, "3");
        bindTouch(R.id.btnCircle, "4");
    }

    private void bindTouch(int id, final String data) {
        View v = findViewById(id);
        if (v != null) {
            v.setOnTouchListener(new View.OnTouchListener() {
					@Override
					public boolean onTouch(View view, MotionEvent event) {
						if (event.getAction() == MotionEvent.ACTION_DOWN) {
							sendBluetoothData(data);
						} else if (event.getAction() == MotionEvent.ACTION_UP) {
							sendBluetoothData("0"); // Bırakınca sıfır gönderir
						}
						return false;
					}
				});
        }
    }

    private void listPairedDevices() {
        final Set<BluetoothDevice> pairedDevices = myBluetooth.getBondedDevices();
        final ArrayList<String> list = new ArrayList<String>();

        if (pairedDevices.size() > 0) {
            for (BluetoothDevice bt : pairedDevices) {
                list.add(bt.getName() + "\n" + bt.getAddress());
            }
        } else {
            Toast.makeText(this, "Eşleşmiş cihaz yok!", Toast.LENGTH_SHORT).show();
            return;
        }

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        ListView lv = new ListView(this);
        lv.setAdapter(adapter);

        final AlertDialog dialog = new AlertDialog.Builder(this)
            .setTitle("Bluetooth Seç")
            .setView(lv)
            .create();
        dialog.show();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> p, View v, int pos, long id) {
					String info = ((TextView) v).getText().toString();
					final String address = info.substring(info.length() - 17);
					new Thread(new Runnable() {
							@Override
							public void run() {
								makeConnection(address);
							}
						}).start();
					dialog.dismiss();
				}
			});
    }

    private void makeConnection(String address) {
        try {
            BluetoothDevice device = myBluetooth.getRemoteDevice(address);
            btSocket = device.createInsecureRfcommSocketToServiceRecord(myUUID);
            btSocket.connect();
            outputStream = btSocket.getOutputStream();
            postToast("Bağlandı!");
        } catch (IOException e) {
            postToast("Bağlantı Başarısız!");
        }
    }

    private void sendBluetoothData(String msg) {
        if (outputStream != null) {
            try {
                outputStream.write(msg.getBytes());
            } catch (IOException e) {
                // Veri gönderilemedi
            }
        }
    }

    private void showInfoDialog() {
        new AlertDialog.Builder(this)
            .setTitle("Buton Haritası")
            .setMessage("Yönler: F,B,L,R\nΔ: 1, X: 2\n□: 3, O: 4\nBoşta: 0")
            .setPositiveButton("Tamam", null)
            .show();
    }

    private void postToast(final String msg) {
        runOnUiThread(new Runnable() {
				@Override
				public void run() {
					Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
				}
			});
    }
}

