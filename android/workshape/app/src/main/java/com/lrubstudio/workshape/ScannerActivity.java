package com.lrubstudio.workshape;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.CompoundBarcodeView;
import com.lrubstudio.workshape.R;

/**
 * Created by louis on 19/03/16.
 */
public class ScannerActivity extends Activity implements CompoundBarcodeView.TorchListener
{
    private CaptureManager capture;
    private CompoundBarcodeView barcodeScannerView;
    private Button switchFlashlightButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        barcodeScannerView = (CompoundBarcodeView)findViewById(R.id.zxing_barcode_scanner);
        barcodeScannerView.setTorchListener(this);

        switchFlashlightButton = (Button)findViewById(R.id.buttonFlash);

        // if the device does not have flashlight in its camera,
        // then remove the switch flashlight button...
        if (!hasFlash())
        {
            switchFlashlightButton.setVisibility(View.GONE);
        }
        else
        {
            // flash on by default
            onClickFlash(switchFlashlightButton);
        }

        // setup capture
        capture = new CaptureManager(this, barcodeScannerView);
        capture.initializeFromIntent(getIntent(), savedInstanceState);
        capture.decode();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        capture.onResume();
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        capture.onPause();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        capture.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        capture.onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        return barcodeScannerView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    /**
     * Check if the device's camera has a Flashlight.
     * @return true if there is Flashlight, otherwise false.
     */
    private boolean hasFlash()
    {
        return getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
    }

    public void onClickFlash(View view)
    {
        if (getString(R.string.turn_on_flashlight).equals(switchFlashlightButton.getText()))
        {
            barcodeScannerView.setTorchOn();
        }
        else
        {
            barcodeScannerView.setTorchOff();
        }
    }

    @Override
    public void onTorchOn()
    {
        switchFlashlightButton.setText(R.string.turn_off_flashlight);
    }

    @Override
    public void onTorchOff()
    {
        switchFlashlightButton.setText(R.string.turn_on_flashlight);
    }
}
