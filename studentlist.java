package com.example.final1;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class studentlist extends AppCompatActivity {
    GridView gridView;
    ArrayList<student> list;
    studentlistadapter adapter = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlist);

        gridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new studentlistadapter(this, R.layout.student_list, list);
        gridView.setAdapter(adapter);

        // get all data from sqlite
        final Cursor cursor = Login.sqLiteHelper.getData("SELECT * FROM StudentTable");
        list.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String rollno = cursor.getString(2);
            String email = cursor.getString(3);
            String password = cursor.getString(4);


            list.add(new student(name, rollno, email,password));
        }
        adapter.notifyDataSetChanged();
      /*  gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

                CharSequence[] items = {"Update", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(studentlist.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            // update
                            Cursor c = Login.sqLiteHelper.getData("SELECT id FROM StudentTable");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            // show dialog update at here
                            showDialogUpdate(studentlist.this, arrID.get(position));

                        } else {
                            // delete
                            Cursor c = Login.sqLiteHelper.getData("SELECT id FROM StudentTable");
                            ArrayList<Integer> arrID = new ArrayList<Integer>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            showDialogDelete(arrID.get(position));
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });
    }
    private void showDialogUpdate(Activity activity, final int position){

        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_student);
        dialog.setTitle("Update");

        final EditText name = (EditText) dialog.findViewById(R.id.name);
        final EditText reg = (EditText) dialog.findViewById(R.id.reg);
        final EditText email = (EditText) dialog.findViewById(R.id.email);
        final EditText password = (EditText) dialog.findViewById(R.id.password);

        Button btnUpdate = (Button) dialog.findViewById(R.id.update);

        // set width for dialog
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels * 0.95);
        // set height for dialog
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels * 0.7);
        dialog.getWindow().setLayout(width, height);
        dialog.show();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Login.sqLiteHelper.updateData3(
                            name.getText().toString().trim(),
                            reg.getText().toString().trim(),
                            email.getText().toString().trim(),
                            password.getText().toString().trim(),
                            position
                    );
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Update successfully!!!",Toast.LENGTH_SHORT).show();
                }
                catch (Exception error) {
                    Log.e("Update error", error.getMessage());
                }
                updateFoodList();
            }
        });
    }

    private void showDialogDelete(final int id){
        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(studentlist.this);

        dialogDelete.setTitle("Warning!!");
        dialogDelete.setMessage("Are you sure you want to this delete?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    Login.sqLiteHelper.deleteData4(id);
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
        Cursor cursor = Login.sqLiteHelper.getData("SELECT * FROM StudentTable");
        list.clear();
        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            String rollno = cursor.getString(2);
            String email = cursor.getString(3);
            String password = cursor.getString(4);

            list.add(new student(name, rollno,email,password));
        }
        adapter.notifyDataSetChanged();
    }
}
