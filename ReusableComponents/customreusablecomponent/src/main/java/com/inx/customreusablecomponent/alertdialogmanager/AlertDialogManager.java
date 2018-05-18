package com.inx.customreusablecomponent.alertdialogmanager;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Created by keerthana on 18/5/18.
 */

public class AlertDialogManager {
    Context mContext;
    AlertDialog.Builder builder;
    private static AlertDialogManager alertDialogManager = null;
   public void setContext(Context context){
        this.mContext = context;
       builder = new AlertDialog.Builder(mContext);

    }
    private AlertDialogManager(){

    }

    public static synchronized AlertDialogManager getInstance(){
        if(alertDialogManager == null){
           alertDialogManager = new AlertDialogManager();
        }
        return alertDialogManager;
    }

    /**
     * It is the alert dialog with custom title and description, Also it has the listener for the
     * both positive and negative button
     * @param title
     * @param description
     * @param listenener
     */

    public void sampleAlertDialog(String title, String description,
                                  final DialogListenener listenener){

        builder.setTitle(title);
        builder.setMessage(description);

        String positiveText = "OK";
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // positive button logic
                        listenener.onClickPositive();
                        dialog.dismiss();
                    }
                });

        String negativeText = "Cancel";
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // negative button logic
                        listenener.onClickNegative();
                        dialog.dismiss();
                    }
                });
        // display dialog
        AlertDialog dialog = builder.create();
        dialog.show();


    }

    /**
     * It is the listener of the alert dialog.
     */
    public interface DialogListenener{

        /**
         * It is callled up on clicking the possitive button on the alert dialog.
         */
        public void onClickPositive();

        /**
         * It is callled up on clicking the negative button on the alert dialog.
         */
        public void onClickNegative();
    }
}
