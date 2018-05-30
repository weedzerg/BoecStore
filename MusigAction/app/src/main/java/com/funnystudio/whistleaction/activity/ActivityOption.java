package com.funnystudio.whistleaction.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.funnystudio.whistleaction.Ads;
import com.funnystudio.whistleaction.HKCache.Cache;
import com.funnystudio.whistleaction.R;
import com.funnystudio.whistleaction.common.Common;
import com.funnystudio.whistleaction.model.ObjectCallPhone;
import com.funnystudio.whistleaction.model.ObjectCamera;
import com.funnystudio.whistleaction.model.ObjectFindPhone;
import com.funnystudio.whistleaction.model.ObjectMusic;
import com.funnystudio.whistleaction.service.ListenSoundService;
import com.zer.android.ZAndroidSDK;

public class ActivityOption extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout findphone, callquickly, camera;
    private Dialog mdialogFindphone, mdialogCall;
    private ImageView imgfind, imgcall, imgcamera;
    private Cache cache;
    private ObjectMusic musicMp3;
    private EditText ednumber;
    private boolean checkoption = false;
    private RelativeLayout adslayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        getSupportActionBar().hide();
        cache = new Cache(getBaseContext());
        turnPermiss();
    }

    public void init() {
        findphone = (LinearLayout) findViewById(R.id.option_findphone);
        callquickly = (LinearLayout) findViewById(R.id.option_callquickly);
        camera = (LinearLayout) findViewById(R.id.option_camera);
        imgfind = (ImageView) findViewById(R.id.swfindphone);
        imgcall = (ImageView) findViewById(R.id.swcallphone);
        imgcamera = (ImageView) findViewById(R.id.swfindcamera);
        adslayout = (RelativeLayout) findViewById(R.id.adslayout);
        isStatusOption();
        findphone.setOnClickListener(this);
        callquickly.setOnClickListener(this);
        camera.setOnClickListener(this);
        imgcamera.setOnClickListener(click_checkstatus);
        imgcall.setOnClickListener(click_checkstatus);
        imgfind.setOnClickListener(click_checkstatus);
        Ads.f(this);
        Ads.b(this, adslayout, new Ads.OnAdsListener() {
            @Override
            public void onError() {
                adslayout.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAdLoaded() {
                adslayout.setVisibility(View.VISIBLE);

            }

            @Override
            public void onAdOpened() {
                adslayout.setVisibility(View.VISIBLE);

            }
        });
    }

    private View.OnClickListener click_checkstatus = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()) {
                case R.id.swfindphone: {
                    if (ListenSoundService.objectFindPhone.isStatus()) {
                        String[] findphone = cache.getLink("findphone");
                        findphone[0] = false + "";
                        cache.putLink("findphone", findphone);
                        checkoption = false;
                        imgfind.setImageResource(R.drawable.ic_off);
                        ListenSoundService.objectFindPhone.setStatus(false);
                    } else {
                        if (!checkoption) {
                            String[] findphone = cache.getLink("findphone");
                            findphone[0] = true + "";
                            cache.putLink("findphone", findphone);
                            checkoption = true;
                            imgfind.setImageResource(R.drawable.ic_on);
                            ListenSoundService.objectFindPhone.setStatus(true);
                            ListenSoundService.getdatafromCache();
                            CreateDialogDetail_FindPhone();
                        } else {
                            Toast.makeText(ActivityOption.this, R.string.hdchon, Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
                case R.id.swcallphone: {

                    if (ListenSoundService.objectCallPhone.isStatus()) {
                        ListenSoundService.objectCallPhone.setStatus(false);
                        String[] callphone = cache.getLink("callphone");
                        callphone[0] = false + "";
                        cache.putLink("callphone", callphone);
                        checkoption = false;
                        imgcall.setImageResource(R.drawable.ic_off);
                    } else {
                        if (!checkoption) {
                            ListenSoundService.objectCallPhone.setStatus(true);
                            String[] callphone = cache.getLink("callphone");
                            callphone[0] = true + "";
                            cache.putLink("callphone", callphone);
                            checkoption = true;
                            imgcall.setImageResource(R.drawable.ic_on);
                            CreateDialogDetail_Call();
                        } else {
                            Toast.makeText(ActivityOption.this, R.string.hdchon, Toast.LENGTH_SHORT).show();
                        }
                    }
                    break;
                }
                case R.id.swfindcamera: {
                    if (ListenSoundService.objectCamera.isStatus()) {
                        ListenSoundService.objectCamera.setStatus(false);
                        String[] camera_ = cache.getLink("camera");
                        camera_[0] = false + "";
                        cache.putLink("camera", camera_);

                        checkoption = false;
                        imgcamera.setImageResource(R.drawable.ic_off);
                    } else {
                        if (!checkoption) {
                            ListenSoundService.objectCamera.setStatus(true);
                            String[] camera_ = cache.getLink("camera");
                            camera_[0] = true + "";
                            cache.putLink("camera", camera_);
                            checkoption = true;
                            imgcamera.setImageResource(R.drawable.ic_on);
                        } else {
                            Toast.makeText(ActivityOption.this, R.string.hdchon, Toast.LENGTH_SHORT).show();
                        }
                    }

                    break;
                }
            }

            if (!checkoption) {
                removeNotification();
            }
        }
    };

    public void removeNotification() {
        NotificationManager nManager = ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE));
        nManager.cancelAll();
    }

    public void isStatusOption() {
        checkoption = false;
        if (ListenSoundService.objectCamera.isStatus()) {
            imgcamera.setImageResource(R.drawable.ic_on);
            checkoption = true;
        } else {
            imgcamera.setImageResource(R.drawable.ic_off);
        }

        if (ListenSoundService.objectCallPhone.isStatus()) {
            imgcall.setImageResource(R.drawable.ic_on);
            checkoption = true;
        } else {
            imgcall.setImageResource(R.drawable.ic_off);
        }
        if (ListenSoundService.objectFindPhone.isStatus()) {
            imgfind.setImageResource(R.drawable.ic_on);
            checkoption = true;
        } else {
            imgfind.setImageResource(R.drawable.ic_off);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.option_findphone: {
                ListenSoundService.getdatafromCache();
                CreateDialogDetail_FindPhone();
                break;
            }
            case R.id.option_callquickly: {
                ListenSoundService.getdatafromCache();
                CreateDialogDetail_Call();
                break;
            }
        }
    }

    public void initservice() {
        if (!(Common.CheckServiesRuning(ListenSoundService.class, this))) {
            startService(new Intent(this, ListenSoundService.class));
        }
        String[] setingfind = cache.getLink("findphone");
            String[] setingcall = cache.getLink("callphone");
            String[] setingcamera = cache.getLink("camera");
            if (setingfind != null) {
            boolean statusfindphone = Boolean.valueOf(setingfind[0]);
            boolean checkmusic = Boolean.valueOf(setingfind[1]);
            int idmusicfindphone = Integer.valueOf(setingfind[2]);
            String pathmusic = setingfind[3];
            String namemusicfindphone = setingfind[4];
            boolean flashfindphone = Boolean.valueOf(setingfind[5]);
            ListenSoundService.objectFindPhone = new ObjectFindPhone(statusfindphone,
                    new ObjectMusic(checkmusic, idmusicfindphone, namemusicfindphone, pathmusic), flashfindphone);

        } else {
            ListenSoundService.objectFindPhone = new ObjectFindPhone(false, new ObjectMusic(false, R.raw.ring01, "ring01.mp3", ""), true);
            setingfind = new String[6];
            setingfind[0] = "false";
            setingfind[1] = "false";
            setingfind[2] = R.raw.ring01 + "";
            setingfind[3] = "";
            setingfind[4] = "ring01.mp3";
            setingfind[5] = "true";
            cache.putLink("findphone", setingfind);
        }

        if (setingcall != null) {
            boolean statuscall = Boolean.valueOf(setingcall[0]);
            String numbercall = String.valueOf(setingcall[1]);
            ListenSoundService.objectCallPhone = new ObjectCallPhone(statuscall, numbercall);


        } else {
            ListenSoundService.objectCallPhone = new ObjectCallPhone(false, "0");
            setingcall = new String[2];
            setingcall[0] = "false";
            setingcall[1] = "0";
            cache.putLink("callphone", setingcall);
        }
        if (setingcamera != null) {
            boolean statuscall = Boolean.valueOf(setingcamera[0]);
            ListenSoundService.objectCamera = new ObjectCamera(statuscall);
        } else {
            ListenSoundService.objectCamera = new ObjectCamera(false);
            setingcamera = new String[1];
            setingcamera[0] = "false";
            cache.putLink("camera", setingcamera);
        }

    }

    TextView txtnamering;

    private void CreateDialogDetail_FindPhone() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        View v = getLayoutInflater().inflate(R.layout.activity_findphone, null);
        TextView okfind, cancelfind;
        final Switch imgflash;
        TextView openlsmp3;
        okfind = (TextView) v.findViewById(R.id.okfind);
        cancelfind = (TextView) v.findViewById(R.id.cancelfind);
        imgflash = (Switch) v.findViewById(R.id.swflash);
        txtnamering = (TextView) v.findViewById(R.id.txtring);
        openlsmp3 = (TextView) v.findViewById(R.id.librarymusic);
        final Activity activity = this;


        openlsmp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //kiểm tra phiên bản SDK của máy với phiên bản android studio
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
                                != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(activity,
                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
                            Intent intent = new Intent(getBaseContext(), SoundMP3.class);
                            startActivityForResult(intent, 500);
                            overridePendingTransition(R.anim.anim_right, R.anim.anim_left);
                        }
                    }
                } catch (SecurityException e) {
                    Toast.makeText(getBaseContext(), "Show My Location Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
                Intent intent = new Intent(getBaseContext(), SoundMP3.class);
                startActivityForResult(intent, 500);
                overridePendingTransition(R.anim.anim_right, R.anim.anim_left);
            }
        });

        final String[] setingfind = cache.getLink("findphone");
        ObjectFindPhone objectFindPhone = ListenSoundService.objectFindPhone;
        imgflash.setChecked(objectFindPhone.isFlash());
        txtnamering.setText(objectFindPhone.getRing().getNamemusic());
        okfind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (musicMp3 != null) {
                    setingfind[1] = musicMp3.isCheck() + "";
                    setingfind[2] = musicMp3.getIdlink() + "";
                    setingfind[3] = musicMp3.getFilename();
                    setingfind[4] = musicMp3.getNamemusic();
                }
                setingfind[5] = imgflash.isChecked() + "";
                cache.putLink("findphone", setingfind);
                ListenSoundService.objectFindPhone.setFlash(imgflash.isChecked());
                ListenSoundService.objectFindPhone.setRing(musicMp3);

                mdialogFindphone.dismiss();
            }
        });

        cancelfind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListenSoundService.objectFindPhone.setStatus(false);
                setingfind[0] = "false";
                mdialogFindphone.dismiss();
                isStatusOption();

            }
        });

        dialog.setView(v);

        mdialogFindphone = dialog.create();
        mdialogFindphone.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mdialogFindphone.show();
    }

    public void turnPermiss() {
        try {
            //kiểm tra phiên bản SDK của máy với phiên bản android studio
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                        && ContextCompat.checkSelfPermission(getBaseContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.RECORD_AUDIO,
                                    Manifest.permission.CALL_PHONE,
                                    Manifest.permission.CAMERA,
                                    Manifest.permission.READ_CONTACTS,
                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    ZAndroidSDK.init(this);
                    initservice();
                    init();
                }
            } else {
                ZAndroidSDK.init(this);
                initservice();
                init();
            }
        } catch (SecurityException e) {
            Toast.makeText(this, "Show My Location Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void openContact() {
        try {
            //kiểm tra phiên bản SDK của máy với phiên bản android studio
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 100);
                    Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                    startActivityForResult(intent, 100);
                }
            }
        } catch (SecurityException e) {
            Toast.makeText(this, "Show My Location Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, 100);
    }

    private String getNumberphone(Uri uriContact) {
        String contactNumber = null;
        String contactID = null;
        // getting contacts ID
        Cursor cursorID = getContentResolver().query(uriContact,
                new String[]{ContactsContract.Contacts._ID},
                null, null, null);

        if (cursorID.moveToFirst()) {

            contactID = cursorID.getString(cursorID.getColumnIndex(ContactsContract.Contacts._ID));
        }

        cursorID.close();

        Log.d("DEBUG", "Contact ID: " + contactID);

        // Using the contact ID now we will get contact phone number
        Cursor cursorPhone = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},

                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? AND " +
                        ContactsContract.CommonDataKinds.Phone.TYPE + " = " +
                        ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE,

                new String[]{contactID},
                null);

        if (cursorPhone.moveToFirst()) {
            contactNumber = cursorPhone.getString(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        }

        cursorPhone.close();

        Log.d("DEBUG", "Contact Phone Number: " + contactNumber);
        return contactNumber;
    }


    private void CreateDialogDetail_Call() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        View v = getLayoutInflater().inflate(R.layout.activity_call_quickly, null);
        TextView okcall, cancelcall, contact;
        okcall = (TextView) v.findViewById(R.id.okcall);
        cancelcall = (TextView) v.findViewById(R.id.cancelcall);
        contact = (TextView) v.findViewById(R.id.contact);
        ednumber = (EditText) v.findViewById(R.id.txtnumberphone);
        final String[] setingfind = cache.getLink("callphone");
        contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openContact();
            }
        });
        ObjectCallPhone objectCallPhone = ListenSoundService.objectCallPhone;
        ednumber.setText(objectCallPhone.getNumberphone() + "");

        okcall.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                setingfind[1] = ednumber.getText().toString();
                ListenSoundService.objectCallPhone.setNumberphone(setingfind[1]);
                cache.putLink("callphone", setingfind);
                mdialogCall.dismiss();
            }
        });

        cancelcall.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                ListenSoundService.objectCallPhone.setStatus(false);
                setingfind[0] = "false";
                mdialogCall.dismiss();
                isStatusOption();
            }
        });
        dialog.setView(v);
        mdialogCall = dialog.create();
        mdialogCall.getWindow().

                setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mdialogCall.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 100:
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    String numberphone = getNumberphone(contactData);
                    ednumber.setText(numberphone);
                }
                break;
            case 500: {
                if (data != null) {
                    musicMp3 = (ObjectMusic) data.getSerializableExtra("filemusic");
                    txtnamering.setText(musicMp3.getNamemusic());
                }
                break;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    ZAndroidSDK.init(this);
                    initservice();
                    init();
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(ActivityOption.this, "Permission denied!", Toast.LENGTH_SHORT).show();
                    finish();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }

    }
}
