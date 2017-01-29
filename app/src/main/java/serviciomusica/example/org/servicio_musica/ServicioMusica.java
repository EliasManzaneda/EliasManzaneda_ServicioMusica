package serviciomusica.example.org.servicio_musica;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;



public class ServicioMusica extends Service {
    MediaPlayer reproductor;


    // 8.3
    private NotificationManager notificationManager;
    private static final int ID_NOTIFICACION = 1;


    @Override
    public void onCreate() {
        Toast.makeText(this, "Servicio creado", Toast.LENGTH_SHORT).show();
        reproductor = MediaPlayer.create(this, R.raw.audio);

        notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
    }


    //8.2
    @Override
    public int onStartCommand(Intent intent, int flags, int idArranque) {

        //Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        long vibrate[] = {0, 100, 200, 300};
        Notification notification = new Notification.Builder(this).setContentTitle("Reproduciendo música")
                .setContentText("Información adicional").setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis()).setContentIntent(pendingIntent).setVibrate(vibrate)
                .setLights(0xffff00ff,0,0).getNotification();
        notification.flags |= Notification.FLAG_SHOW_LIGHTS;

        notificationManager.notify(ID_NOTIFICACION, notification);


        Toast.makeText(this,"Servicio arrancado "+ idArranque, Toast.LENGTH_SHORT).show();
        reproductor.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        notificationManager.cancel(ID_NOTIFICACION);

        Toast.makeText(this,"Servicio detenido", Toast.LENGTH_SHORT).show();
        reproductor.stop();
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    /*
    @Override
    public void onStart(Intent intent, int startId) {
        Toast.makeText(this, "Servicio arrancado " +
                startId, Toast.LENGTH_SHORT).show();
        reproductor.start();
    }
    */

}
