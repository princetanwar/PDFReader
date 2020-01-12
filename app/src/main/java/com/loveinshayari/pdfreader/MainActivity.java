package com.loveinshayari.pdfreader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.loveinshayari.pdfreader.adaptor.PDFadptor;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ListView listView;
    public static ArrayList<File> filelist = new ArrayList<>();
    PDFadptor obj_adptor;
    public static  int PUSMISSION = 1;
    boolean boolean_purmission;
    File dir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView_pdf);

        dir = new File(Environment.getExternalStorageDirectory().toString());

        primission();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(),pdfview.class);
                intent.putExtra("position",i);
                startActivity(intent);

            }
        });
    }

    private void primission() {

        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){


            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)){

                new AlertDialog.Builder(this)
                        .setTitle("permission needed")
                        .setMessage("this permission is needed us to read your PDF files ")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PUSMISSION);

                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dialogInterface.dismiss();
                                finish();

                            }
                        })
                        .create()
                        .show();
            }

            else {
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PUSMISSION);
            }
        }

        else {
            boolean_purmission = true;
            getfile(dir);
            obj_adptor = new PDFadptor(getApplicationContext(),filelist);
            listView.setAdapter(obj_adptor);

        }
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PUSMISSION){

            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                boolean_purmission = true;
                getfile(dir);
                obj_adptor = new PDFadptor(getApplicationContext(),filelist);
                listView.setAdapter(obj_adptor);

            }

            else {

                Toast.makeText(this, "please allow the permission", Toast.LENGTH_SHORT).show();
                finish();
            }


        }
    }

   public  ArrayList<File> getfile(File dir){

        File listfile[] = dir.listFiles();
        if (listfile != null && listfile.length > 0){


            for (int i = 0; i < listfile.length; i++){

                if (listfile[i].isDirectory()){

                    getfile(listfile[i]);
                }

                else {

                    boolean booleanpdf = false;
                    if (listfile[i].getName().endsWith(".pdf")){

                        for (int j = 0;  j< filelist.size(); j++){

                            if (filelist.get(j).getName().equals(listfile[i].getName())){

                                booleanpdf = true;

                            }
                            else {}
                        }

                        if (booleanpdf){
                            booleanpdf = false;
                        }

                        else {
                            filelist.add(listfile[i]);
                        }
                    }
                }
            }


        }

        return filelist;
   }
}
