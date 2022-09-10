package com.example.android_listview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btn_add, btn_edit, btn_delete;
    EditText inputNameLaptop;
    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter arrayAdapter;
    int index = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectID();
        arrayList = new ArrayList<>();
        arrayList.add("Lenovo");
        arrayList.add("Asus");
        arrayList.add("Dell");
        arrayList.add("HP");
        arrayList.add("ROG");
        arrayList.add("Windows");



        arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inputNameLaptop.getText().toString().trim().isEmpty() != true) {
                    String name = inputNameLaptop.getText().toString().trim();
                    arrayList.add(name);
                    arrayAdapter.notifyDataSetChanged();
                } else {
                    showDialog("Bạn chưa nhập laptop để thêm");
                }
            }
        });


        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inputNameLaptop.getText().toString().trim().isEmpty() != true) {
                    String name = inputNameLaptop.getText().toString().trim();
                    arrayList.set(index, name);
                    arrayAdapter.notifyDataSetChanged();
                } else {
                    showDialog("Bạn chưa chọn laptop để sửa");
                }
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(inputNameLaptop.getText().toString().trim().isEmpty() != true) {
                    confirmDelete(index);
                } else {
                    showDialog("Bạn chưa chọn laptop để xóa");
                }

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                index = i;
                inputNameLaptop.setText(arrayList.get(i));
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("nameLaptop", arrayList.get(i));

                intent.putExtra("bundle", bundle);
                startActivity(intent);
                return false;
            }
        });




    }

    public void connectID() {
        btn_add = findViewById(R.id.btn_add);
        btn_edit = findViewById(R.id.btn_edit);
        btn_delete = findViewById(R.id.btn_delete);
        inputNameLaptop = findViewById(R.id.inputNameLaptop);
        listView = findViewById(R.id.list);
    }

    public void confirmDelete(int position) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("");
        alertDialog.setMessage("Bạn có muốn xóa không");

        alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                arrayList.remove(index);
                arrayAdapter.notifyDataSetChanged();
                inputNameLaptop.setText("");
            }
        });

        alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        alertDialog.show();

    }

    public void showDialog(String str) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage(str);
        alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alertDialog.show();
    }



}