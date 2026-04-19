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

            initControllers();

            View btnConnect = findViewById(R.id.btnConnect);
            if (btnConnect != null) {
                btnConnect.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							listPairedDevices();
						}
					});
            }

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
        bindTouch(R.id.btnF, "F");
        bindTouch(R.id.btnB, "B");
        bindTouch(R.id.btnL, "L");
        bindTouch(R.id.btnR, "R");
        bindTouch(R.id.btnOpen, "W");
        bindTouch(R.id.btnClose, "w");

        View btnHigh = findViewById(R.id.speedHigh);
        if (btnHigh != null) {
            btnHigh.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						sendBluetoothData("q");
					}
				});
        }

        View btnMid = findViewById(R.id.speedMid);
        if (btnMid != null) {
            btnMid.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						sendBluetoothData("3");
					}
				});
        }

        View btnLow = findViewById(R.id.speedLow);
        if (btnLow != null) {
            btnLow.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						sendBluetoothData("1");
					}
				});
        }
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
							sendBluetoothData("S");
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

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        ListView lv = new ListView(this);
        lv.setAdapter(adapter);

        final AlertDialog dialog = new AlertDialog.Builder(this)
            .setTitle("Bluetooth Seç")
            .setView(lv)
            .create();
        dialog.show();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					String info = ((TextView) view).getText().toString();
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
                // Hata
            }
        }
    }

    private void showInfoDialog() {
        new AlertDialog.Builder(this)
            .setTitle("Kontrol Haritası")
            .setMessage("İleri: F, Geri: B, Sol: L, Sağ: R\nDUR: S\nKıskaç Aç: W, Kapat: w\nHızlar: Yüksek(q), Orta(3), Düşük(1)")
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
