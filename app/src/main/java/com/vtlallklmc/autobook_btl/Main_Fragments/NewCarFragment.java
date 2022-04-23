package com.vtlallklmc.autobook_btl.Main_Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.vtlallklmc.autobook_btl.Car.Car;
import com.vtlallklmc.autobook_btl.Car.CarAdapter;
import com.vtlallklmc.autobook_btl.Car.DatabaseData;
import com.vtlallklmc.autobook_btl.DetailActivity;
import com.vtlallklmc.autobook_btl.R;

import java.util.ArrayList;

public class NewCarFragment extends Fragment {
    ListView lvNewCar;
    ArrayList<Car> lstNewCar = new ArrayList<>();
    CarAdapter carAdapter;
    Context context;

    DatabaseData databaseData;

    private MainActivity parentActivity; //gọi lớp cha

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_car_fragment,container,false);
        parentActivity = (MainActivity) getActivity();

        lvNewCar = view.findViewById(R.id.lvNewCar);

        databaseData = new DatabaseData(inflater.getContext());

        lstNewCar = databaseData.getNewCar(); // lấy toàn bộ ArrayList Car
        carAdapter = new CarAdapter(inflater.getContext(),lstNewCar); //lắp ArrayList vào Adapter
        lvNewCar.setAdapter(carAdapter); // truyền Adapter vào listview để hiển thị lên

        lvNewCar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intentSend = new Intent(inflater.getContext(), DetailActivity.class);
                intentSend.putExtra("keyword",lstNewCar.get(i).getName());
                startActivity(intentSend);
//                parentActivity.finish();
            }
        });
        return view;
    }
}
