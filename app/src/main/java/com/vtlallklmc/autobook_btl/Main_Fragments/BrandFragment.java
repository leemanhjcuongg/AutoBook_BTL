package com.vtlallklmc.autobook_btl.Main_Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.vtlallklmc.autobook_btl.Car.Car;
import com.vtlallklmc.autobook_btl.Car.CarAdapter;
import com.vtlallklmc.autobook_btl.Car.DatabaseData;
import com.vtlallklmc.autobook_btl.DetailActivity;
import com.vtlallklmc.autobook_btl.R;

import java.util.ArrayList;

public class BrandFragment extends Fragment {
    ListView lvBrandCar;
//    Button btnSort;
    Switch swSort;
    ArrayList<Car> lstBrandCar = new ArrayList<>();
    CarAdapter carAdapter;
    Context context;

    private MainActivity parentActivity;
    DatabaseData databaseData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.brand_fragment,container,false);
        parentActivity = (MainActivity) getActivity();

        lvBrandCar = view.findViewById(R.id.lvBrand);
//        btnSort = view.findViewById(R.id.btnSort);
//        swSort = view.findViewById(R.id.swSort);

        databaseData = new DatabaseData(inflater.getContext());
        lstBrandCar = databaseData.getCarbyBrand(); // lấy toàn bộ ArrayList Car
        carAdapter = new CarAdapter(inflater.getContext(),lstBrandCar); //lắp ArrayList vào Adapter
        lvBrandCar.setAdapter(carAdapter); // truyền Adapter vào listview để hiển thị lên

        lvBrandCar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentSend = new Intent(inflater.getContext(), DetailActivity.class);
                intentSend.putExtra("keyword",lstBrandCar.get(i).getName());
                startActivity(intentSend);
//                parentActivity.finish();
            }
        });
//        swSort.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if(b){
//                    lstBrandCar = databaseData.getCarNameAsc(); // lấy toàn bộ ArrayList Car
//                    carAdapter = new CarAdapter(inflater.getContext(),lstBrandCar); //lắp ArrayList vào Adapter
//                    lvBrandCar.setAdapter(carAdapter); // truyền Adapter vào listview để hiển thị lên
//                }else{
//                    lstBrandCar = databaseData.getCarbyBrand(); // lấy toàn bộ ArrayList Car
//                    carAdapter = new CarAdapter(inflater.getContext(),lstBrandCar); //lắp ArrayList vào Adapter
//                    lvBrandCar.setAdapter(carAdapter); // truyền Adapter vào listview để hiển thị lên
//                }
//            }
//        });
//        btnSort.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                lstBrandCar = databaseData.getCarNameAsc();
//                carAdapter = new CarAdapter(inflater.getContext(),lstBrandCar);
//                lvBrandCar.setAdapter(carAdapter);
//            }
//        });
        return view;
    }
}
