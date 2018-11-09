package spencerstudios.com.regexapp.Dialogs;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

public class DialogFactory {

    public static void exitDialog(final Context context) {
        final AlertDialog builder = new AlertDialog.Builder(context).create();
        builder.setTitle("Exit App");
        builder.setMessage("Are you sure you want to close this application?");
        builder.setButton(DialogInterface.BUTTON_POSITIVE, "Yes, exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ((Activity) context).finish();
            }
        });
        builder.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                builder.dismiss();
            }
        });
        builder.show();
    }
}
