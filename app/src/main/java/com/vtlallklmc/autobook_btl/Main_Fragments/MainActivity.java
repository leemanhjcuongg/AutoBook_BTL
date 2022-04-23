package com.vtlallklmc.autobook_btl.Main_Fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vtlallklmc.autobook_btl.Car.Car;
import com.vtlallklmc.autobook_btl.Car.DatabaseData;
import com.vtlallklmc.autobook_btl.DetailActivity;
import com.vtlallklmc.autobook_btl.R;
import com.vtlallklmc.autobook_btl.User.UserDatabaseData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView navigationView;
    ViewPager viewPager;
    AutoCompleteTextView searchBar;
    ImageButton btnSearch;
    ArrayList<String> lstCarResult = new ArrayList<>();
    EditText from, to;
    Button loc;

    DatabaseData databaseData;

    String keyword;

    private long backPressTime; //thời gian chờ nhấn nút back 2 lần liên tiếp

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //anh xa
        navigationView = findViewById(R.id.bottom_nav);
        viewPager = findViewById(R.id.view_pager);

        from = findViewById(R.id.edtFrom);
        to = findViewById(R.id.edtTo);
        loc = findViewById(R.id.btnLoc);

        setUpViewPager();

        navigationView.setOnNavigationItemSelectedListener(item ->  {
                switch (item.getItemId()){
                    case R.id.newCarItem:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.bestSelledItem:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.brandItem:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.personal:
                        viewPager.setCurrentItem(3);
                        break;
                }
                return true;
        });

        databaseData = new DatabaseData(MainActivity.this);
        //chèn dữ liệu
        databaseData.insertCar(new Car(1,"BMW 320i GT 2016","BMW","Trắng",2016,4,R.drawable.bmw_1,+R.drawable.bmw_2,+R.drawable.bmw_3,3359,"Tự động","7.2l xăng/100km",280,1350000000));
        databaseData.insertCar(new Car(2,"BMW X5 2022","BMW","Trắng",2022,2,R.drawable.x5_1,+R.drawable.x5_2,+R.drawable.x5_3,3359,"Tự động","8.1l xăng/100km",320,4479000000.0));
        databaseData.insertCar(new Car(3,"Honda Civic 1.8 2022","Honda","Trắng",2022,6,R.drawable.civic_1,+R.drawable.civic_2,+R.drawable.cvic_3,1800,"Tự động","7.0l dầu/100km",260,738000000.0));
        databaseData.insertCar(new Car(4,"Honda CR-V 2.4AT 2022","Honda","Trắng",2022,2,R.drawable.crv_1,+R.drawable.crv_2,+R.drawable.crv_3,2000,"Sàn","6.4l dầu/100km",230,998000000.0));
        databaseData.insertCar(new Car(5,"Hyundai Elantra 2.0 2018","Hyundai","Đen",2018,10,R.drawable.elatran_1,+R.drawable.elatran_2,+R.drawable.elatran_3,2300,"Tự động","7l xăng/100km",240,575000000.0));
        databaseData.insertCar(new Car(6,"SANTAFE PREMIUM 2020","Hyundai","Trắng",2020,8,R.drawable.satafe_3,+R.drawable.satafe_2,+R.drawable.satafe_1,2359,"Tự động","7.2l xăng/100km",200,1100000000.0));
        databaseData.insertCar(new Car(7,"Kia K3 2022","Kia","Xám",2022,18,R.drawable.kia_k3_1,+R.drawable.kia_k3_2,+R.drawable.kia_k3_3,1600,"Sàn","6.7l xăng/100km",260,559000000.0));
        databaseData.insertCar(new Car(8,"Kia Seltos 1.4 Deluxe 2022","Kia","Trắng",2022,2,R.drawable.seltos_1,+R.drawable.seltos_2,+R.drawable.seltos_3,1450,"Tự động","6.9l xăng/100km",250,639000000.0));
        databaseData.insertCar(new Car(9,"Kia Sorento 2.2D Deluxe 2022","Kia","Trắng",2022,2,R.drawable.kia_1,+R.drawable.kia_2,+R.drawable.kia_3,1900,"Tự động","6.8l xăng/100km",240,999000000.0));
        databaseData.insertCar(new Car(10,"Mazda 3 1.5L Luxury 2022","Mazda","Đỏ",2022,3,R.drawable.mazda_1,+R.drawable.mazda_2,+R.drawable.mazda_3,1559,"Tự động","7.1l xăng/100km",250,686000000.0));
        databaseData.insertCar(new Car(11,"Mazda CX-5 2.0 Deluxe 2022","Mazda","Trắng",2022,2,R.drawable.cx5_1,+R.drawable.cx5_2,+R.drawable.cx5_3,1800,"Tự động","7.2l xăng/100km",260,818000000.0));
        databaseData.insertCar(new Car(12,"Mercedes-Benz C 200 Exclusive","Mercedes-Benz","Đỏ",2021,4,R.drawable.c200_1,+R.drawable.c200_2,+R.drawable.c200_3,1991,"Tự động","7.47l xăng/100km",239,1700000000.0));
        databaseData.insertCar(new Car(13,"Mercedes GLC 300 4MATIC 2021","Mercedes-Benz","Trắng",2021,1,R.drawable.glc_1,+R.drawable.glc_2,+R.drawable.glc_3,2010,"Tự động","7.8l xăng/100km",300,2530000000.0));
        databaseData.insertCar(new Car(14,"Mitsubishi Xpander 1.5MT 2020","Mitsubishi","Xám",2020,6,R.drawable.xpander_1,+R.drawable.xpander_2,+R.drawable.xpander_3,1959,"Sàn","6.1l xăng/100km",210,485000000.0));
        databaseData.insertCar(new Car(15,"VinFast Fadil 2021","VinFast","Đen",2021,1,R.drawable.fadil_1,+R.drawable.fadil_2,+R.drawable.fadil_3,1359,"Tự động","6.6l xăng/100km",230,368000000.0));
        databaseData.insertCar(new Car(16,"Vinfast Lux A2.0","VinFast","Đen",2020,2,R.drawable.vin_1,+R.drawable.vin_2,+R.drawable.vin_3,1559,"Tự động","7.1l xăng/100km",250,950000000.0));
        databaseData.insertCar(new Car(17,"VinFast LUX SA2.0","VinFast","Trắng",2021,3,R.drawable.sa_1,+R.drawable.sa_2,+R.drawable.sa_3,1659,"Tự động","7.1l xăng/100km",280,1170000000.0));

        searchBar = findViewById(R.id.search_bar); //ánh xạ
        searchBar.setText("");

        for(Car c:databaseData.getAllCar()){ //vòng lặp duyệt toàn bộ xe
            lstCarResult.add(c.getName()); //thêm từng tên xe vào danh sách để hiển thị lên AutoCompleteTextView
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,lstCarResult); //khai báo Adapter trên context MainAct này, layout list mặc định, lấy source từ danh sách lstCarResult
        searchBar.setAdapter(arrayAdapter); //hiển thị adapter lên searchBar;

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                keyword = charSequence.toString();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        searchBar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentSend = new Intent(MainActivity.this, DetailActivity.class);
                intentSend.putExtra("keyword",keyword);
                startActivity(intentSend);
//                finish();
                searchBar.setText("");
                searchBar.clearFocus();
            }
        });
        btnSearch = findViewById(R.id.btnSearch);
        if(searchBar.getText().equals("")==true){
            btnSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intentSend = new Intent(MainActivity.this,DetailActivity.class);
                    intentSend.putExtra("keyword",keyword);
                    startActivity(intentSend);
                    finish();
                    searchBar.setText("");
                    searchBar.clearFocus();
                }
            });
        }
        loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Hello", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void setUpViewPager(){
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        navigationView.getMenu().findItem(R.id.newCarItem).setChecked(true);
                        break;
                    case 1:
                        navigationView.getMenu().findItem(R.id.bestSelledItem).setChecked(true);
                        break;
                    case 2:
                        navigationView.getMenu().findItem(R.id.brandItem).setChecked(true);
                        break;
                    case 3:
                        navigationView.getMenu().findItem(R.id.personal).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    //ấn back 2 lần để thoát và đăng xuất
    @Override
    public void onBackPressed() {
        if(backPressTime + 2000 > System.currentTimeMillis()){
            super.onBackPressed();
            finish();
        }else{
            Toast.makeText(this, "Nhấn phím trở về ↩️ 1 lần nữa để thoát", Toast.LENGTH_SHORT).show();
        }
        backPressTime = System.currentTimeMillis();
    }
}