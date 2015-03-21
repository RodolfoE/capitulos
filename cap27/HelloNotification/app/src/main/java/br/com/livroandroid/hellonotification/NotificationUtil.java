package br.com.livroandroid.hellonotification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.TaskStackBuilder;

import java.util.List;

/**
 * Classe utilitária para criar notificações.
 *
 * http://developer.android.com/guide/topics/ui/notifiers/notifications.html
 */
public class NotificationUtil {

    public static final String ACTION_VISUALIZAR = "br.com.livroandroid.hellonotification.ACTION_VISUALIZAR";

    private static PendingIntent getPendingIntent(Context context, Intent intent, int id) {
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(intent.getComponent());
        stackBuilder.addNextIntent(intent);
        PendingIntent p = stackBuilder.getPendingIntent(id, PendingIntent.FLAG_UPDATE_CURRENT);
        return p;
    }

    public static void create(Context context, Intent intent, String contentTitle, String contentText, int id) {
        PendingIntent p = getPendingIntent(context, intent, id);

        // Cria a notificação
        NotificationCompat.Builder b = new NotificationCompat.Builder(context);
        b.setDefaults(Notification.DEFAULT_ALL); // Ativa configurações padrão
        b.setSmallIcon(R.mipmap.ic_launcher); // Ícone
        b.setContentTitle(contentTitle); // Título
        b.setContentText(contentText); // Mensagem
        b.setContentIntent(p); // Intent que será chamada ao clicar na notificação.
        b.setAutoCancel(true); // Auto cancela a notificação ao clicar nela

        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(id, b.build());
    }

    public static void createBig(Context context, Intent intent, String contentTitle, String contentText,List<String> lines, int id) {
        PendingIntent p = getPendingIntent(context, intent, id);

        // Configura o estilo Inbox
        int size = lines.size();
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle(contentTitle);
        for (String s: lines) {
            inboxStyle.addLine(s);
        }
        inboxStyle.setSummaryText(contentText);

        // Cria a notificação
        NotificationCompat.Builder b = new NotificationCompat.Builder(context);
        b.setDefaults(Notification.DEFAULT_ALL); // Ativa configurações padrão
        b.setSmallIcon(R.mipmap.ic_launcher); // Ícone
        b.setContentTitle(contentTitle); // Título
        b.setContentText(contentText); // Mensagem
        b.setContentIntent(p); // Intent que será chamada ao clicar na notificação.
        b.setAutoCancel(true); // Auto cancela a notificação ao clicar nela
        b.setNumber(size); // Número para aparecer na notificação
        b.setStyle(inboxStyle); // Estilo customizado

        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(id, b.build());
    }

    public static void createWithAction(Context context, Intent intent, String contentTitle, String contentText, int id) {
        PendingIntent p = getPendingIntent(context, intent, id);

        // Cria a notificação
        NotificationCompat.Builder b = new NotificationCompat.Builder(context);
        b.setDefaults(Notification.DEFAULT_ALL); // Ativa configurações padrão
        b.setSmallIcon(R.mipmap.ic_launcher); // Ícone
        b.setContentTitle(contentTitle); // Título
        b.setContentText(contentText); // Mensagem
        b.setContentIntent(p); // Intent que será chamada ao clicar na notificação.
        b.setAutoCancel(true); // Auto cancela a notificação ao clicar nela

        // Ação customizada (deixei a mesma intent para os dois)
        PendingIntent actionIntent = PendingIntent.getBroadcast(
                context, 0, new Intent(ACTION_VISUALIZAR), 0);
        b.addAction(R.drawable.ic_acao_pause, "Pause", actionIntent);
        b.addAction(R.drawable.ic_acao_play, "Play", actionIntent);

        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(id, b.build());
    }

    /*
public static void criarNotificacaoCompleta(Context ctx, String texto, int id) {
        Uri uriSom = Uri.parse("android.resource://"+
                ctx.getPackageName() +"/raw/som_notificacao");

        PendingIntent pitAcao = PendingIntent.getBroadcast(
                ctx, 0, new Intent(ACAO_NOTIFICACAO), 0);
        PendingIntent pitDelete = PendingIntent.getBroadcast(
                ctx, 0, new Intent(ACAO_DELETE), 0);

        Bitmap largeIcon = BitmapFactory.decodeResource(
                ctx.getResources(), R.drawable.ic_launcher);

        PendingIntent pitNotificacao= criarPendingIntent(ctx, texto, id);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(ctx)
                        .setSmallIcon(R.drawable.ic_notificacao)
                        .setContentTitle("Completa")
                        .setContentText(texto)
                        .setTicker("Chegou uma notificação")
                        .setWhen(System.currentTimeMillis())
                        .setLargeIcon(largeIcon)
                        .setAutoCancel(true)
                        .setContentIntent(pitNotificacao)
                        .setDeleteIntent(pitDelete)
                        .setLights(Color.BLUE, 1000, 5000)
                        .setSound(uriSom)
                        .setVibrate(new long[]{100, 500, 200, 800})
                        .addAction(R.drawable.ic_acao_notificacao, "Ação Customizada", pitAcao)
                        .setNumber(id)
                        .setSubText("Subtexto");

        NotificationManagerCompat nm = NotificationManagerCompat.from(ctx);
        nm.notify(id, mBuilder.build());
    }
     */

    public static void cancell(Context context, int id) {
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.cancel(id);
    }

    public static void cancellAll(Context context) {
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.cancelAll();
    }
}
