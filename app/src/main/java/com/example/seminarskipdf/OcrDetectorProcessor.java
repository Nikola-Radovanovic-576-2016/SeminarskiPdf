package com.example.seminarskipdf;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.util.SparseArray;
import android.widget.VideoView;

import com.example.seminarskipdf.camera.GraphicOverlay;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import static com.example.seminarskipdf.DetectionActivity.PACKAGE_NAME;


public class OcrDetectorProcessor implements Detector.Processor<TextBlock>{

    private GraphicOverlay<OcrGraphic> graphicOverlay;
    private String currentPdf = "0";
    OcrDetectorProcessor(GraphicOverlay<OcrGraphic> ocrGraphicOverlay) {
        graphicOverlay = ocrGraphicOverlay;
    }

    @Override
    public void release() {
        graphicOverlay.clear();
    }

    @Override
    public void receiveDetections(Detector.Detections<TextBlock> detections) {
        graphicOverlay.clear();
        SparseArray<TextBlock> items = detections.getDetectedItems();
        for (int i = 0; i < items.size(); ++i) {
            TextBlock item = items.valueAt(i);
            if (item != null && item.getValue() != null) {
                Log.d("Processor", "Text detected! " + item.getValue());
                openpdf(graphicOverlay.getContext(),item.getValue());

            }
            OcrGraphic graphic = new OcrGraphic(graphicOverlay, item);
            graphicOverlay.add(graphic);
        }
    }


    public void openpdf(Context context, String brInd)
    {

        brInd = brInd.replace('/','-');
        String path = "/Download/" + brInd+".pdf";
        File file = new File(Environment.getExternalStorageDirectory()+path);
        Log.w("666",Environment.getExternalStorageDirectory()+path);

        if(file.exists() && !path.equals(currentPdf)) {
            currentPdf = path;
            Intent target = new Intent(Intent.ACTION_VIEW);
            target.setDataAndType(Uri.fromFile(file), "application/pdf");
            target.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

            Intent intent = Intent.createChooser(target, "Open File");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            try {
                context.startActivity(intent);

            } catch (ActivityNotFoundException e) {
                Log.w("666", e.getCause());
            }
        }
        else
            currentPdf = "0";

        //graphicOverlay.clear();
    }

}