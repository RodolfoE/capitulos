package br.com.livroandroid.helloservice;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import livroandroid.lib.utils.NotificationUtil;

/**
 * Created by Ricardo Lecheta on 15/03/2015.
 */
public class HelloService extends Service {
    private static final int MAX = 10;
    private static final String TAG = "livro";
    protected int count;
    private boolean running;
    @Override
    public IBinder onBind(Intent i) {
        // null aqui porque n�o queremos interagir com o servi�o (veremos um exemplo disso depois)
        return null;
    }
    @Override
    public void onCreate() {
        Log.d(TAG, "HelloService.onCreate() - Service criado");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "HelloService.onStartCommand() - Service iniciado: " + startId);
        count = 0;
        // M�todo chamado depois do onCreate(), logo depois que o servi�o � iniciado
        // O par�metro �startId� representa o identificador deste servi�o
        running = true;
        // Delega para uma thread
        new WorkerThread().start();
        // Chama a implementa��o da classe m�e
        return super.onStartCommand(intent, flags, startId);
    }

    // Thread que faz o trabalho pesado
    class WorkerThread extends Thread {
        public void run() {
            try {
                while (running && count < MAX) {
                    // Simula algum processamento
                    Thread.sleep(1000);
                    Log.d(TAG, "HelloService executando... " + count);
                    count++;
                }
                Log.d(TAG, "HelloService fim.");
            } catch (InterruptedException e) {
                Log.e(TAG,e.getMessage(),e);
            } finally {
                // Auto-Encerra o servi�o se o contador chegou a 10
                stopSelf();
                // Cria uma notifica��o para avisar o usu�rio que terminou.
                Context context = HelloService.this;
                Intent intent = new Intent(context,MainActivity.class);
                NotificationUtil.create(context, 1, intent, R.mipmap.ic_launcher, "HelloService", "Fim do servi�o.");

            }
        }
    }
    @Override
    public void onDestroy() {
        // Ao encerrar o servi�o, altere o flag para a thread parar (isto � importante para encerrar
        // a thread caso algu�m tenha chamado o stopService(intent)
        running = false;
        Log.d(TAG, "HelloService.onDestroy() - Service destru�do");
    }
}
