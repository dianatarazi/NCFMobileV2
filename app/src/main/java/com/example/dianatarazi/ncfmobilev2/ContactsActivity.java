package com.example.dianatarazi.ncfmobilev2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.dianatarazi.ncfmobilev2.dummy.DummyContent;

public class ContactsActivity extends AppCompatActivity implements ContactsFragment.OnListFragmentInteractionListener {

    private final String TAG = "ContactsActivity";
    private static final int REQUEST_CALL = 1;
    private boolean callPermissionGranted = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        getSupportFragmentManager().beginTransaction().add(R.id.contacts_fragment_container,
                new ContactsFragment()).commit();

    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        Log.d(TAG, "onListFragmentInteraction: item clicked (from activity)");
        if (makePhoneCall()) {
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + item.number)));
            Log.d(TAG, "Calling " + item.name);
        }
    }

    private boolean makePhoneCall() {
        if (ContextCompat.checkSelfPermission(ContactsActivity.this,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            callPermissionGranted = true;
            //Toast.makeText(this, "permission is granted!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            ActivityCompat.requestPermissions(ContactsActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        callPermissionGranted = false;
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callPermissionGranted = true;
            } else {
                Toast.makeText(this, "Permission DENIED. Enable phone calls from settings.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
