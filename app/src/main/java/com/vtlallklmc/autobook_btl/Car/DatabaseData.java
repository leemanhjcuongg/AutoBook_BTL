package com.vtlallklmc.autobook_btl.Car;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DatabaseData { //Lớp này để tạo các phương thức khai thác SQLite gồm thêm, lấy danh sách xe, lọc danh sách xe, tìm kiếm ...
    CarDBHelper carDBHelper; //gọi đối tượng carDBHelper để sử dụng
    Context context;

    //khởi tạo
    public DatabaseData(Context context) {
        this.context = context;
        this.carDBHelper = new CarDBHelper(context);
    }
    //thêm xe
    public void insertCar(Car car){
        ContentValues contentValues = new ContentValues();

        contentValues.put(CarDBHelper.PRODUCT_CODE, car.getProduct_code());
        contentValues.put(CarDBHelper.NAME, car.getName());
        contentValues.put(CarDBHelper.BRAND, car.getBrand());
        contentValues.put(CarDBHelper.COLOR, car.getColor());
        contentValues.put(CarDBHelper.NAMSX, car.getNamsx());
        contentValues.put(CarDBHelper.SL, car.getSl());
        contentValues.put(CarDBHelper.IMG_CODE1, car.getImg1());
        contentValues.put(CarDBHelper.IMG_CODE2,car.getImg2());
        contentValues.put(CarDBHelper.IMG_CODE3, car.getImg3());
        contentValues.put(CarDBHelper.DUNGTICH, car.getDungtich());
        contentValues.put(CarDBHelper.HOPSO, car.getHopso());
        contentValues.put(CarDBHelper.MUCTIEUTHU, car.getMuctieuthu());
        contentValues.put(CarDBHelper.VMAX, car.getVmax());
        contentValues.put(CarDBHelper.GIA, car.getGia());

        SQLiteDatabase db = carDBHelper.getWritableDatabase();
        long result = db.insert(CarDBHelper.TB_NAME,null,contentValues);
        db.close();
//        if(result!=-1)
//            Toast.makeText(context, "Thêm thành công!", Toast.LENGTH_SHORT).show();
//        else Toast.makeText(context, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
    }
    //lấy danh sách tất cả xe, trả lại 1 ArrayList
    public ArrayList<Car> getAllCar(){
        ArrayList<Car> lstCar = new ArrayList<>(); //khởi tạo mảng

        SQLiteDatabase db = carDBHelper.getReadableDatabase(); //gọi SQlite phương thức đọc từ db
        Cursor cursor = db.rawQuery("SELECT * FROM "+CarDBHelper.TB_NAME,null); //khởi tạo con trỏ, trỏ từng thuộc tính trong bảng truy vấn với câu lệnh SELECT *
        while (cursor.moveToNext()){ //khi con trỏ trỏ đến phần tử tiếp theo
            Car c = new Car( //khởi tạo 1 đối tượng xe truyền vào các thuộc tính sau:
                    cursor.getInt(0),  //cột 0 là mã xe
                    cursor.getString(1), //cột 1 là tên xe
                    cursor.getString(2), // ...
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getInt(5),
                    cursor.getInt(6),
                    cursor.getInt(7),
                    cursor.getInt(8),
                    cursor.getInt(9),
                    cursor.getString(10),
                    cursor.getString(11),
                    cursor.getInt(12),
                    cursor.getDouble(13)
            );
            lstCar.add(c); //thêm đối tượng car vào arrayList
        }
        return lstCar; // trả lại danh sách xe
    }
    public ArrayList<Car> getNewCar(){ //tương tự như phương thức lấy tất cả xe chỉ thay đổi câu lệnh SELECT
        ArrayList<Car> lstCar = new ArrayList<>();

        SQLiteDatabase db = carDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+CarDBHelper.TB_NAME+" ORDER BY nam_sx DESC LIMIT 10",null);
        while (cursor.moveToNext()){
            Car c = new Car(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getInt(5),
                    cursor.getInt(6),
                    cursor.getInt(7),
                    cursor.getInt(8),
                    cursor.getInt(9),
                    cursor.getString(10),
                    cursor.getString(11),
                    cursor.getInt(12),
                    cursor.getDouble(13)
            );
            lstCar.add(c);
        }
        return lstCar;
    }
    public ArrayList<Car> getBestSelledCar(){ //tương tự như phương thức lấy tất cả xe chỉ thay đổi câu lệnh SELECT
        ArrayList<Car> lstCar = new ArrayList<>();

        SQLiteDatabase db = carDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+CarDBHelper.TB_NAME+" ORDER BY soluong ASC LIMIT 10",null);
        while (cursor.moveToNext()){
            Car c = new Car(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getInt(5),
                    cursor.getInt(6),
                    cursor.getInt(7),
                    cursor.getInt(8),
                    cursor.getInt(9),
                    cursor.getString(10),
                    cursor.getString(11),
                    cursor.getInt(12),
                    cursor.getDouble(13)
            );
            lstCar.add(c);
        }
        return lstCar;
    }
    public ArrayList<Car> getCarbyBrand(){ //tương tự như phương thức lấy tất cả xe chỉ thay đổi câu lệnh SELECT
        ArrayList<Car> lstCar = new ArrayList<>();

        SQLiteDatabase db = carDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+CarDBHelper.TB_NAME+" ORDER BY brand ASC",null);
        while (cursor.moveToNext()){
            Car c = new Car(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getInt(5),
                    cursor.getInt(6),
                    cursor.getInt(7),
                    cursor.getInt(8),
                    cursor.getInt(9),
                    cursor.getString(10),
                    cursor.getString(11),
                    cursor.getInt(12),
                    cursor.getDouble(13)
            );
            lstCar.add(c);
        }
        return lstCar;
    }public ArrayList<Car> getCarNameAsc(){ //tương tự như phương thức lấy tất cả xe chỉ thay đổi câu lệnh SELECT
        ArrayList<Car> lstCar = new ArrayList<>();

        SQLiteDatabase db = carDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+CarDBHelper.TB_NAME+" ORDER BY "+CarDBHelper.NAME+" ASC",null);
        while (cursor.moveToNext()){
            Car c = new Car(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getInt(5),
                    cursor.getInt(6),
                    cursor.getInt(7),
                    cursor.getInt(8),
                    cursor.getInt(9),
                    cursor.getString(10),
                    cursor.getString(11),
                    cursor.getInt(12),
                    cursor.getDouble(13)
            );
            lstCar.add(c);
        }
        return lstCar;
    }
    public Car searchCar(String query){ //Tìm xe, khởi tạo đối tượng Car truyền vào String query (tên xe cần tìm)

        SQLiteDatabase db = carDBHelper.getReadableDatabase(); // đọc db
//        Cursor cursor = db.rawQuery("SELECT * from "+CarDBHelper.TB_NAME+" WHERE name like '%"+query+"%'",null);
//        while (cursor.moveToNext()){
//            Car c = new Car(
//                    cursor.getInt(0),
//                    cursor.getString(1),
//                    cursor.getString(2),
//                    cursor.getString(3),
//                    cursor.getInt(4),
//                    cursor.getInt(5),
//                    cursor.getInt(6),
//                    cursor.getInt(7),
//                    cursor.getInt(8),
//                    cursor.getInt(9),
//                    cursor.getString(10),
//                    cursor.getString(11),
//                    cursor.getInt(12),
//                    cursor.getDouble(13)
//            );
//        }
        //khai báo con trỏ, dùng câu lệnh truy vấn
                                    //tên bảng          tên cột muốn chọn       mã                      tên             hãng                màu             năm                sl ...
        Cursor cursor = db.query(CarDBHelper.TB_NAME,new String[]{CarDBHelper.PRODUCT_CODE,CarDBHelper.NAME,CarDBHelper.BRAND,CarDBHelper.COLOR,CarDBHelper.NAMSX,CarDBHelper.SL,
                CarDBHelper.IMG_CODE1,CarDBHelper.IMG_CODE2,CarDBHelper.IMG_CODE3,CarDBHelper.DUNGTICH,CarDBHelper.HOPSO,CarDBHelper.MUCTIEUTHU,CarDBHelper.VMAX,CarDBHelper.GIA},CarDBHelper.NAME+"=?",new String[]{String.valueOf(query)},null,null,null);
                            //ảnh 1                 2                       3               dung tích               hop so              mtt                 vmax            gia, điều kiện:         tên xe = query                                 , group by, having, order by: 0
        if(cursor!=null){ //con trỏ khác null, tức là tìm thấy
            cursor.moveToFirst(); //thì chuyển lên đầu
        }
        return new Car( //trả lại thuộc tính hàng vừa mới chuyển lên đầu tiên
                cursor.getInt(0),
                cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4),
                    cursor.getInt(5),
                    cursor.getInt(6),
                    cursor.getInt(7),
                    cursor.getInt(8),
                    cursor.getInt(9),
                    cursor.getString(10),
                    cursor.getString(11),
                    cursor.getInt(12),
                    cursor.getDouble(13));
    }
    public ArrayList<String> getAllCarName(){ //phương thức lấy tất cả tên xe dùng trong auto complete text view
        ArrayList<String> lstCar = new ArrayList<>();

        SQLiteDatabase db = carDBHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT "+CarDBHelper.NAME+" FROM "+CarDBHelper.TB_NAME,null);
        while (cursor.moveToNext()){
            lstCar.add(cursor.getString(0));
        }
        return lstCar;
    }
}
