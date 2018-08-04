package study.com.ailatrieuphu;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

/**
 * Created by Administrator on 06/03/2018.
 */

public class Tools {
    static public void launchBrower(Context context, String url) {
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    public static void MessageBox (final Context context, final String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void AlertDialog(final Context ctx, final CharSequence title, final CharSequence message) {
        new AlertDialog.Builder(ctx)
                .setIcon(R.drawable.icon_question_start)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show()
        ;
    }
}
