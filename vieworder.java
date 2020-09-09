package com.example.final1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class vieworder extends AppCompatActivity {
    GridView gridView;
    ArrayList<order> list;
    vieworderAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vieworder);
        gridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new vieworderAdapter(this, R.layout.order_list, list);
        gridView.setAdapter(adapter);

        // get all data from sqlite
        final Cursor cursor = Login.sqLiteHelper.getData("SELECT * FROM ITEMORDERS");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            String quantity = cursor.getString(3);
            String total = cursor.getString(4);
            String status = cursor.getString(5);
            String email = cursor.getString(6);
            list.add(new order(name, price, quantity,total,status,email,id));
        }

     adapter.notifyDataSetChanged();
       /* gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent = new Intent(FoodList.this,orderfood.class);
            String item = ((TextView)view.findViewById(R.id.txtName)).getText().toString();
            String price = ((TextView)view.findViewById(R.id.txtPrice)).getText().toString();
            intent.putExtra("name",item);
            intent.putExtra("price",price);
            startActivity(intent);
        }
    });*/

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

            CharSequence[] items = {"Delete"};
            AlertDialog.Builder dialog = new AlertDialog.Builder(vieworder.this);

            dialog.setTitle("Choose an action");
            dialog.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {
                        // delete
                        Cursor c = FoodMain.sqLiteHelper.getData("SELECT id FROM ITEMORDERS");
                        ArrayList<Integer> arrID = new ArrayList<Integer>();
                        while (c.moveToNext()){
                            arrID.add(c.getInt(0));
                        }
                        showDialogDelete(arrID.get(position));
                }
            });
            dialog.show();
            return true;
        }
    });
}

    private void showDialogDelete(final int idorder){
        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(vieworder.this);

        dialogDelete.setTitle("Warning!!");
        dialogDelete.setMessage("Are you sure you want to this delete?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    FoodMain.sqLiteHelper.deleteData2(idorder);
                    Toast.makeText(getApplicationContext(), "Delete successfully!!!",Toast.LENGTH_SHORT).show();
                } catch (Exception e){
                    Log.e("error", e.getMessage());
                }
                updateFoodList();
            }
        });

        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDelete.show();
    }

    private void updateFoodList(){
        // get all data from sqlite
        Cursor cursor = FoodMain.sqLiteHelper.getData("SELECT * FROM ITEMORDERS");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            String quantity = cursor.getString(3);
            String total = cursor.getString(4);
            String status = cursor.getString(5);
            String email = cursor.getString(6);
            list.add(new order(name, price, quantity,total,status,email, id));
        }
        adapter.notifyDataSetChanged();
    }
}
