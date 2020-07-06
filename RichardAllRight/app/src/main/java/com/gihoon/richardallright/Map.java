package com.gihoon.richardallright;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Transaction;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class Map extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    String[] REQUIRED_PERMISSIONS = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    private GoogleMap mMap;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
    LatLng sydney;
    HashMap<String, HashMap> markers;
    private long backKeyPressedTime = 0;
    // 첫 번째 뒤로가기 버튼을 누를때 표시
    private Toast toast;

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }

        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finish();
            toast.cancel();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        System.out.println(currentUser.getEmail());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);

        ImageButton button = (ImageButton) findViewById(R.id.menuButton);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        button.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.RIGHT);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawer.closeDrawer(Gravity.RIGHT);

                switch (item.getItemId()) {
                    case R.id.goToMyPage:
                        //마이페이지 이동 로직 추가
                        Intent mypa = new Intent(getApplication(), Information.class);
                        mypa.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(mypa);
                        break;
                    case R.id.goToLogOut:
                        FirebaseAuth.getInstance().signOut();
                        Intent mype = new Intent(getApplication(), Auth.class);
                        mype.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(mype);
                        finish();
                        //로그아웃 이동 로직 추가
                        break;
                }

                return true;
            }
        });
        NavigationView nv = (NavigationView) findViewById(R.id.nav_view);
        ImageView iv = nv.getHeaderView(0).findViewById(R.id.action_image);
        TextView tv = nv.getHeaderView(0).findViewById(R.id.action_name);
        if (currentUser.getPhotoUrl() != null) {
            Glide.with(getApplication()).load(currentUser.getPhotoUrl()).into(iv);
        }
        if (currentUser.getDisplayName() != null) {
            if (!currentUser.getDisplayName().equals("")) {
                tv.setText(currentUser.getDisplayName());
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            update_location();
        } else {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, 100);
        }
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                LinearLayout fl2 = findViewById(R.id.fl2);
                fl2.setVisibility(View.INVISIBLE);
            }
        });
        ImageButton dkdk = findViewById(R.id.location);
        dkdk.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "위치를 업데이트합니다.\n권한이 없을 경우 기본 위치로 설정됩니다.", Toast.LENGTH_SHORT).show();
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    update_location();
                } else {
                    ActivityCompat.requestPermissions(getParent(), REQUIRED_PERMISSIONS, 100);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, @NonNull String[] permissions, @NonNull int[] grandResults) {
        if (permsRequestCode == 100 && grandResults.length == 2) update_location();
    }

    @SuppressLint("MissingPermission")
    public void update_location() {
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            sydney = new LatLng(37.5015492, 127.0353802);
        } else {
            GpsTracker gpsTracker = new GpsTracker(getApplicationContext());
            sydney = new LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude());
        }
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(sydney).title("내 위치"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 17));

        BitmapDrawable bitmapdrawe = (BitmapDrawable) getResources().getDrawable(R.drawable.location_logo_available);
        BitmapDrawable bitmapdrawf = (BitmapDrawable) getResources().getDrawable(R.drawable.location_logo_full);
        Bitmap be = bitmapdrawe.getBitmap();
        Bitmap bf = bitmapdrawf.getBitmap();
        final Bitmap emptyMarker = Bitmap.createScaledBitmap(be, 150, 150, false);
        final Bitmap fullMarker = Bitmap.createScaledBitmap(bf, 150, 150, false);
        markers = new HashMap<String, HashMap>();
        db.collection("parkingLot")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                HashMap a = (HashMap) document.getData();
                                a.put("id", document.getId());
                                Object x = a.get("x");
                                Object y = a.get("y");
                                LatLng b = new LatLng(Double.parseDouble(x.toString()), Double.parseDouble(y.toString()));
                                MarkerOptions op = new MarkerOptions().position(b);
                                if ((Boolean) a.get("flag"))
                                    op.icon(BitmapDescriptorFactory.fromBitmap(emptyMarker));
                                else op.icon(BitmapDescriptorFactory.fromBitmap(fullMarker));
                                Marker l = mMap.addMarker(op.title(a.get("title").toString()));
                                markers.put(l.getId(), a);
                            }
                        } else {
                            System.out.println(task.getException());
                        }
                    }
                });
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {
        if (markers.containsKey(marker.getId())) {
            System.out.println(markers.get(marker.getId()).get("title"));
            LinearLayout fl2 = findViewById(R.id.fl2);
            ImageView realimage = findViewById(R.id.realimage);
            TextView realname = findViewById(R.id.realname);
            TextView realaddress = findViewById(R.id.realaddress);
            TextView realprice = findViewById(R.id.realprice);
            ImageButton realconfirm = findViewById(R.id.realconfim);

            realname.setText(marker.getTitle());
            realprice.setText(markers.get(marker.getId()).get("price").toString() + "원");
            realaddress.setText(markers.get(marker.getId()).get("address").toString());

            fl2.setVisibility(View.VISIBLE);
            if (markers.get(marker.getId()).get("imageUrl") != null) {
                Glide.with(getApplicationContext())
                        .load(markers.get(marker.getId()).get("imageUrl"))
                        .into(realimage);
            } else {
                realimage.setImageResource(R.mipmap.ic_launcher);
            }
            if ((Boolean) markers.get(marker.getId()).get("flag")) {
                realconfirm.setVisibility(View.VISIBLE);
            } else {
                realconfirm.setVisibility(View.INVISIBLE);
            }
            fl2.setOnClickListener(new LinearLayout.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
            Date date = new Date();
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            final String year = String.valueOf(calendar.get(Calendar.YEAR));
            String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
            String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));

            if (month.length() == 1) month = "0" + month;
            if (day.length() == 1) day = "0" + day;

            final String finalmonth = month;
            final String finalday = day;
            realconfirm.setOnClickListener(new ImageButton.OnClickListener() {

                @Override
                public void onClick(View v) {
                    db.collection("reservation").whereEqualTo("uid", currentUser.getUid())
                            .whereEqualTo("date", year + finalmonth + finalday)
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    Boolean flag = false;
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            flag = true;
                                            HashMap a = (HashMap) document.getData();
                                            String docname = a.get("parkingLotId").toString();
                                            System.out.println("bb" + document.getData());
                                        }
                                    } else {
                                        System.out.println(task.getException());
                                    }
                                    if (flag == false) {
                                        final DocumentReference sfDocRef = db.collection("parkingLot").document(markers.get(marker.getId()).get("id").toString());

                                        db.runTransaction(new Transaction.Function<Boolean>() {
                                            @Override
                                            public Boolean apply(Transaction transaction) throws FirebaseFirestoreException {
                                                DocumentSnapshot snapshot = transaction.get(sfDocRef);
                                                boolean newPopulation = !snapshot.getBoolean("flag");
                                                if (!newPopulation) {
                                                    transaction.update(sfDocRef, "flag", newPopulation);
                                                    return newPopulation;
                                                } else {
                                                    throw new FirebaseFirestoreException("Population too high",
                                                            FirebaseFirestoreException.Code.ABORTED);
                                                }
                                            }
                                        }).addOnSuccessListener(new OnSuccessListener<Boolean>() {
                                            @Override
                                            public void onSuccess(Boolean result) {


                                                System.out.println("Transaction success: " + result);
                                                HashMap<String, Object> data = new HashMap<>();
                                                data.put("uid", currentUser.getUid());
                                                data.put("parkingLotId", markers.get(marker.getId()).get("id"));
                                                data.put("date", year + finalmonth + finalday);

                                                db.collection("reservation")
                                                        .add(data)
                                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                            @Override
                                                            public void onSuccess(DocumentReference documentReference) {
                                                                Toast.makeText(getApplicationContext(), "KakaoPay 앱이 실행됩니다", Toast.LENGTH_LONG).show();
                                                                String url = "https://kapi.kakao.com/v1/payment/ready";

                                                                ContentValues params = new ContentValues();
                                                                params.put("cid", "TC0ONETIME");
                                                                params.put("partner_order_id", "1001");
                                                                params.put("partner_user_id", "gihoony");
                                                                params.put("item_name", markers.get(marker.getId()).get("title").toString());
                                                                params.put("quantity", "1");
                                                                params.put("total_amount", markers.get(marker.getId()).get("price").toString());
                                                                params.put("tax_free_amount", 0);
                                                                params.put("approval_url", "https://gihoonrar.page.link/?link=https://richardallright.com/payment&apn=com.gihoon.richardallright");
                                                                params.put("cancel_url", "https://gihoonrar.page.link/cancel");
                                                                params.put("fail_url", "https://gihoonrar.page.link/fail");
                                                                NetworkTask networkTask = new NetworkTask(url, params);
                                                                networkTask.execute();
                                                            }
                                                        })
                                                        .addOnFailureListener(new OnFailureListener() {
                                                            @Override
                                                            public void onFailure(@NonNull Exception e) {
                                                                sfDocRef.update("flag", false);
                                                                Intent a = new Intent(getApplicationContext(), Map.class);
                                                                a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                                startActivity(a);
                                                                finish();
                                                            }
                                                        });
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(getApplicationContext(), "이미 예약된 자리입니다.\n왼쪽 상단의 위치 갱신 버튼을 눌러 위치를 갱신하세요.", Toast.LENGTH_LONG).show();
                                            }
                                        });
                                    } else {
                                        Toast.makeText(getApplicationContext(), "이미 예약하신 자리가 있습니다. My Page 에서 확인하여 주세요.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            });
        } else {
            //fl1.bringToFront();
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker.getPosition(), 17));
        return false;
    }


    public class NetworkTask extends AsyncTask<Void, Void, String> {

        private String url;
        private ContentValues values;

        public NetworkTask(String url, ContentValues values) {

            this.url = url;
            this.values = values;
        }

        @Override
        protected String doInBackground(Void... params) {

            String result; // 요청 결과를 저장할 변수.
            RequestHttpURLConnection requestHttpURLConnection = new RequestHttpURLConnection();
            result = requestHttpURLConnection.request(url, values, getResources().getString(R.string.kakao_admin_key)); // 해당 URL로 부터 결과물을 얻어온다.
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            JsonObject jsonObj = new JsonParser().parse(s).getAsJsonObject();
            //doInBackground()로 부터 리턴된 값이 onPostExecute()의 매개변수로 넘어오므로 s를 출력한다.
            String redirectUrl = jsonObj.get("next_redirect_app_url").getAsString();
            Intent intent = new Intent(getApplicationContext(), Kakaopay.class);
            intent.putExtra("url", redirectUrl);
            startActivity(intent);
        }
    }
}
