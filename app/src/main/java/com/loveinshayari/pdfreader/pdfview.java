package com.loveinshayari.pdfreader;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;

public class pdfview extends AppCompatActivity {

    PDFView pdfView;
    int position = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview);


        pdfView = findViewById(R.id.pdfView);

        position = getIntent().getIntExtra("position",-1);

        displyPDF();
    }

    private void displyPDF() {

        pdfView.fromFile(MainActivity.filelist.get(position))
                .enableSwipe(true)
                .enableAnnotationRendering(true)
                .scrollHandle(new DefaultScrollHandle(this))
                .load();
    }
}
