package com.example.jugandoserviciosybroadcast;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ServicioCrono extends Service {
    public ServicioCrono() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}