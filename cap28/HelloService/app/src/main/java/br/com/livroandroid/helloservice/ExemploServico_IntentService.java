package br.com.livroandroid.helloservice;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Ricardo Lecheta on 15/03/2015.
 */
public class ExemploServico_IntentService extends IntentService {
    public ExemploServico_IntentService() {
        super("NomeDaThreadAqui");
    }
    private static final int MAX = 10;
    private static final String TAG = "livro";
    private boolean running;
    @Override
    protected void onHandleIntent(Intent intent) {
        running = true;
        // Este m�todo executa em uma thread
        // Quando ele terminar, o m�todo stopSelf() ser� chamado automaticamente
        int count = 0;
        while (running && count < MAX) {
            fazAlgumaCoisa();
            Log.d(TAG, "ExemploServico executando... " + count);
            count++;
        }
        Log.d(TAG, "ExemploServico fim.");
    }
    private void fazAlgumaCoisa() {
        try {
            // Simula algum processamento
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Ao encerrar o servi�o, altera o flag para a thread parar
        running = false;
        Log.d(TAG, "ExemploServico.onDestroy()");
    }
}
