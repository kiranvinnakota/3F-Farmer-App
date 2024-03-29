package com.calibrage.a3ffarmerapp.Fragments;


import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.calibrage.a3ffarmerapp.R;

import java.io.File;
import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class PdfFragment extends Fragment implements View.OnClickListener{

    String path;
    ImageView imgView;
    Button btnPrevious, btnNext;
    int pageIndex;
    PdfRenderer pdfRenderer;
    PdfRenderer.Page curPage;
    ParcelFileDescriptor descriptor;

    public PdfFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //get the path of file
        path = getArguments().getString("keyPath");
        return inflater.inflate(R.layout.fragment_pdf, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //initializing the views
        imgView = (ImageView)view.findViewById(R.id.imgView);
        btnPrevious = (Button)view.findViewById(R.id.btnPrevious);
        btnNext = (Button)view.findViewById(R.id.btnNext);
        //set click listener on buttons
        btnPrevious.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        try {
            openPdfRenderer();
            displayPage(pageIndex);
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Sorry! This pdf is protected with password.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStop() {
        try {
            closePdfRenderer();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onStop();
    }


    void openPdfRenderer(){
        File file = new File(path);
        descriptor = null;
        pdfRenderer = null;
        try {
            descriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_ONLY);
            pdfRenderer = new PdfRenderer(descriptor);
        } catch (Exception e) {
            Toast.makeText(getContext(), "There's some error", Toast.LENGTH_LONG).show();
        }

    }

    private void closePdfRenderer() throws IOException {
        if (curPage != null)
            curPage.close();
        if (pdfRenderer != null)
            pdfRenderer.close();
        if(descriptor !=null)
            descriptor.close();
    }

    void displayPage(int index){
        if(pdfRenderer.getPageCount() <= index)
            return;
        //close the current page
        if(curPage != null)
            curPage.close();
        //open the specified page
        curPage = pdfRenderer.openPage(index);
        //get page width in points(1/72")
        int pageWidth = curPage.getWidth();
        //get page height in points(1/72")
        int pageHeight = curPage.getHeight();
        //returns a mutable bitmap
        Bitmap bitmap = Bitmap.createBitmap(pageWidth, pageHeight, Bitmap.Config.ARGB_8888);
        //render the page on bitmap
        curPage.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
        //display the bitmap
        imgView.setImageBitmap(bitmap);
        //enable or disable the button accordingly
        int pageCount = pdfRenderer.getPageCount();
        btnPrevious.setEnabled(0 != index);
        btnNext.setEnabled(index + 1 < pageCount);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPrevious: {
                //get the index of previous page
                int index = curPage.getIndex()-1;
                displayPage(index);
                break;
            }
            case R.id.btnNext: {
                //get the index of previous page
                int index = curPage.getIndex()+1;
                displayPage(index);
                break;
            }
        }
    }
}