package com.funnystudio.whistleaction.service;

import android.Manifest;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.funnystudio.whistleaction.HKCache.Cache;
import com.funnystudio.whistleaction.R;
import com.funnystudio.whistleaction.model.ObjectCallPhone;
import com.funnystudio.whistleaction.model.ObjectCamera;
import com.funnystudio.whistleaction.model.ObjectFindPhone;
import com.funnystudio.whistleaction.model.ObjectMusic;
import com.funnystudio.whistleaction.musig.DetectorThread;
import com.funnystudio.whistleaction.musig.OnSignalsDetectedListener;
import com.funnystudio.whistleaction.musig.RecorderThread;

public class ListenSoundService extends Service implements OnSignalsDetectedListener {
    private RecorderThread recorderThread;
    private DetectorThread detectorWhitles;
    private MediaPlayer mediaPlayer;
    private Camera cam;
    public static Cache cache;
    public static ObjectFindPhone objectFindPhone = null;
    public static ObjectCallPhone objectCallPhone = null;
    public static ObjectCamera objectCamera = null;


    public ListenSoundService(RecorderThread recorderThread, DetectorThread detectorWhitles) {
        this.recorderThread = recorderThread;
        this.detectorWhitles = detectorWhitles;
    }

    public ListenSoundService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(getBaseContext(), R.raw.ring01);
        initThread();
        Log.d("DEBUG", "start service");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("DEBUG", "start service action");
        cache = new Cache(this);
        if (intent == null || intent.getAction() == null) {
            return START_STICKY;
        }
        if (intent.getAction().equals("com.zergitas.ACTION_STOP")) {
            mediaPlayer.stop();
            if (cam != null) {
                turnOffFlash();
            }
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void initThread() {
        recorderThread = new RecorderThread();
        recorderThread.start();
        detectorWhitles = new DetectorThread(recorderThread);
        detectorWhitles.setOnSignalsDetectedListener(this);
        detectorWhitles.start();
    }

    private void creatNotification(String msg) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.drawable.ic_launcher);
        mBuilder.setContentTitle("Sound Action");
        mBuilder.setContentText(msg);
        mBuilder.setAutoCancel(true);
        Intent intent = new Intent("com.zergitas.ACTION_STOP");
        PendingIntent myIntent = PendingIntent.getService(this, 100, intent, 0);
        mBuilder.setContentIntent(myIntent);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // notificationID allows you to update the notification later on.
        mNotificationManager.notify(0, mBuilder.build());
    }

    public void callPhone(String value) {
        try {
            String num = "tel:" + value;
            Intent callintent = new Intent(Intent.ACTION_CALL, Uri.parse(num));
            callintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            this.startActivity(callintent);
        } catch (Exception e) {

            e.printStackTrace();
            return;
        }
    }

    public void openCamera() {
        try {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public void playmusic(boolean check) {
        if (check) {
                mediaPlayer = MediaPlayer.create(this, Uri.parse(objectFindPhone.getRing().getFilename()));
                mediaPlayer.setLooping(true);
                mediaPlayer.start();
        } else {
                mediaPlayer = MediaPlayer.create(getBaseContext(), objectFindPhone.getRing().getIdlink());
                mediaPlayer.start();
        }
    }

    @Override
    public void onWhistleDetected() {
        getdatafromCache();
        if (objectFindPhone != null) {
            if (objectFindPhone.isStatus()) {
                if (objectFindPhone.isFlash()) {
                    turnOnFlash();
                }
                creatNotification(getString(R.string.turnoffmp3));
                if (!mediaPlayer.isPlaying()) {
                    playmusic(objectFindPhone.getRing().isCheckringDefault());
                }
            }
        }
        if (objectCallPhone != null) {
            if (objectCallPhone.isStatus()) {
                creatNotification(getString(R.string.calling));
                callPhone(objectCallPhone.getNumberphone());
            }
        }
        if (objectCamera != null) {
            if (objectCamera.isStatus()) {
                creatNotification(getString(R.string.cameraopen));
                openCamera();
            }
        }


    }

    public void turnOnFlash() {
        cam = Camera.open();
        Camera.Parameters p = cam.getParameters();
        p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        cam.setParameters(p);
        cam.startPreview();
    }

    public void turnOffFlash() {
        cam.stopPreview();
        cam.release();
    }

    public static void getdatafromCache() {
        String[] findphone = cache.getLink("findphone");
        String[] callphone = cache.getLink("callphone");
        String[] camera = cache.getLink("camera");
        objectFindPhone = new ObjectFindPhone(Boolean.valueOf(findphone[0]),
                new ObjectMusic(Boolean.valueOf(findphone[1]),
                        Integer.valueOf(findphone[2]), findphone[4],
                        findphone[3]),
                Boolean.valueOf(findphone[5]));

        objectCallPhone = new ObjectCallPhone(Boolean.valueOf(callphone[0]),
                callphone[1]);
        objectCamera = new ObjectCamera(Boolean.valueOf(camera[0]));
    }

    public boolean isCameraUsebyApp() {
        Camera camera = null;
        try {
            camera = Camera.open();
        } catch (RuntimeException e) {
            return true;
        } finally {
            if (camera != null) camera.release();
        }
        return false;
    }
}
