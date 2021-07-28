package com.example.piano;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import java.net.URI;

public class splash extends AppCompatActivity {

    private  static final int PERMISSION_CONSTANT=100;
    private static final int REQUEST_PERMISSION_SETTING=101;

    String[] permissionRequired=new String[]
            {
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.RECORD_AUDIO
            };
    private SharedPreferences permissionStatus;
    private Boolean sentToSettings=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        permissionStatus=getSharedPreferences("permissionStatus", MODE_PRIVATE);

        if(ActivityCompat.checkSelfPermission(splash.this,permissionRequired[0])!= PackageManager.PERMISSION_GRANTED ||
                ActivityCompat.checkSelfPermission(splash.this,permissionRequired[1])!= PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(splash.this,permissionRequired[0])
                    ||ActivityCompat.shouldShowRequestPermissionRationale(splash.this,permissionRequired[1]))
            {
                AlertDialog.Builder builder=new AlertDialog.Builder(splash.this);
                builder.setTitle("Need permissions");
                builder.setMessage("Storage and Record Audio permission required");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(splash.this,permissionRequired,PERMISSION_CONSTANT);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
            else if(permissionStatus.getBoolean(permissionRequired[0],false))
            {
                AlertDialog.Builder builder= new AlertDialog.Builder(splash.this);
                builder.setTitle("Need permissions");
                builder.setMessage("Storage and Record Audio permission required");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        sentToSettings=true;
                        Intent intent=new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri= Uri.fromParts("package",getPackageName(),null);
                        intent.setData(uri);
                        startActivityForResult(intent,REQUEST_PERMISSION_SETTING);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
            else
            {
                ActivityCompat.requestPermissions(splash.this,permissionRequired,PERMISSION_CONSTANT);
            }
        }
        else {
            proceedAfterPermission();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == PERMISSION_CONSTANT){  // here matching the requestCode to PERMISSION_CONSTANT
            //check if all permissions are granted
            boolean allgranted = false;         // here setting the variable to false

            for(int i=0;i<grantResults.length;i++){
                if(grantResults[i]==PackageManager.PERMISSION_GRANTED){
                    allgranted = true;
                } else {
                    allgranted = false;
                    break;
                }
            }

            if(allgranted){ //its true means permission granted then call the proceedAfterPermission() Method
                proceedAfterPermission(); // This method will takes the user to the PianoActiviy
            } else if(ActivityCompat.shouldShowRequestPermissionRationale(splash.this,permissionRequired[0])
                    || ActivityCompat.shouldShowRequestPermissionRationale(splash.this,permissionRequired[1])
            ){
//
                /*
                 *  Then again here need to build the AlertDialog to give some information to user
                 * */

                AlertDialog.Builder builder = new AlertDialog.Builder(splash.this);
                builder.setTitle("Need Multiple Permissions");
                builder.setMessage("This app needs Storage and Record Audio Permissions.");
                builder.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        ActivityCompat.requestPermissions(splash.this,permissionRequired,PERMISSION_CONSTANT);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                // Then at the end we need to show the dialog
                builder.show();
            } else {
                Toast.makeText(getBaseContext(),"Unable to get Permission",Toast.LENGTH_LONG).show();
                Toast.makeText(getBaseContext(),"You Need to give the permission manually by going into the Settings",Toast.LENGTH_LONG).show();
                Toast.makeText(getBaseContext(),"Closing the Application",Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PERMISSION_SETTING) {
            if (ActivityCompat.checkSelfPermission(splash.this, permissionRequired[0]) == PackageManager.PERMISSION_GRANTED) {
                //Got Permission
                proceedAfterPermission();
            }
        }
    }

    private void proceedAfterPermission() {
        Toast.makeText(this,"Got all permission",Toast.LENGTH_SHORT).show();
        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}