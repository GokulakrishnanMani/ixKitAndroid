package com.inx.customreusablecomponent.alertdialogmanager;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.widget.Button;

/**
 * Created by keerthana on 18/5/18.
 */


public class AlertDialogManager {

    Context mContext;
    AlertDialog.Builder builder;
    String FONT_COLOR = "<FONT color=";
    String BRANCH = ">";
    String FONT = "</FONT>";
    String BLACK_COLOR = "000000";
    private static AlertDialogManager alertDialogManager = null;

    public void setContext(Context context) {
        this.mContext = context;
        builder = new AlertDialog.Builder(mContext);

    }


    /*
     *Alert dialog getInstance
     */
    public static synchronized AlertDialogManager getInstance() {
        if (alertDialogManager == null) {
            alertDialogManager = new AlertDialogManager();
        }
        return alertDialogManager;
    }



    /**
     * It is the alert dialog with custom title and description, Also it has the listener for the
     * both positive and negative button
     * @param title
     * @param description
     * @param titleColor
     * @param descColor
     * @param posColor
     * @param negColor
     * @param positiveText
     * @param negativeText
     * @param listenener
     */

    public void alertDialog(String title, String description, int titleColor, int descColor, int posColor, int negColor, String positiveText, String negativeText,
                                  final DialogListenener listenener) {
        if(!title.equalsIgnoreCase("")) {
            if ((titleColor != 0)) {
                builder.setTitle(Html.fromHtml(FONT_COLOR + titleColor + BRANCH + title + FONT));
            } else {
                builder.setTitle(title);
            }
        }
        if ((descColor != 0)) {
            builder.setMessage(Html.fromHtml(FONT_COLOR + descColor + BRANCH + description + FONT));
        } else {
            builder.setMessage(description);
        }


        builder.setPositiveButton( positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // positive button logic
                        listenener.onClickPositive();
                        dialog.dismiss();
                    }
                });

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

        // Get the alert dialog buttons reference
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);

        // Change the alert dialog buttons text and background color
        if (posColor != 0) {
            positiveButton.setTextColor(posColor);
        } else {
            positiveButton.setTextColor(Color.parseColor(BLACK_COLOR));

        }

        if (negColor != 0) {
            negativeButton.setTextColor(negColor);
        } else {
            negativeButton.setTextColor(Color.parseColor(BLACK_COLOR));

        }

    }

    /**
     * It is the alert dialog with custom title and description, Also it has the listener for the
     * both positive
     * @param title
     * @param titleColor
     * @param description
     * @param descColor
     * @param posColor
     * @param posText
     * @param listenener
     */

    public void alertDialog(String title, int titleColor, String description, int descColor, int posColor, String posText, final DialogListenener listenener){
        if(!title.equalsIgnoreCase("")) {
            if ((titleColor != 0)) {
                builder.setTitle(Html.fromHtml(FONT_COLOR + titleColor + BRANCH + title + FONT));
            } else {
                builder.setTitle(title);
            }
        }
        if ((titleColor != 0)) {
            builder.setMessage(Html.fromHtml(FONT_COLOR + descColor + BRANCH + description + FONT));
        } else {
            builder.setMessage(description);
        }


        builder.setPositiveButton(posText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // positive button logic
                        listenener.onClickPositive();
                        dialog.dismiss();
                    }
                });


        // display dialog
        AlertDialog dialog = builder.create();
        dialog.show();

        // Get the alert dialog buttons reference
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);

        // Change the alert dialog buttons text and background color
        if (posColor != 0) {
            positiveButton.setTextColor(posColor);
        } else {
            positiveButton.setTextColor(Color.parseColor(BLACK_COLOR));

        }


    }

    /**
     * It is the alert dialog with custom title and description, Also it has the listener for the
     * both positive without callback method
     * @param title
     * @param titleColor
     * @param description
     * @param descColor
     * @param posColor
     * @param posText
     */
    public void alertDialog(String title, int titleColor, String description, int descColor, int posColor, String posText){
        if(!title.equalsIgnoreCase("")) {
            if ((titleColor != 0)) {
                builder.setTitle(Html.fromHtml(FONT_COLOR + titleColor + BRANCH + title + FONT));
            } else {
                builder.setTitle(title);
            }
        }
        if ((titleColor != 0)) {
            builder.setMessage(Html.fromHtml(FONT_COLOR + descColor + BRANCH + description + FONT));
        } else {
            builder.setMessage(description);
        }


        builder.setPositiveButton(posText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // positive button logic
                        dialog.dismiss();
                    }
                });


        // display dialog
        AlertDialog dialog = builder.create();
        dialog.show();

        // Get the alert dialog buttons reference
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);

        // Change the alert dialog buttons text and background color
        if (posColor != 0) {
            positiveButton.setTextColor(posColor);
        } else {
            positiveButton.setTextColor(Color.parseColor(BLACK_COLOR));

        }


    }

    /**
     * It is the alert dialog with custom title and description, Also it has the listener for the
     * both positive, negative button and neutral button
     * @param title
     * @param description
     * @param titleColor
     * @param descColor
     * @param posColor
     * @param negColor
     * @param positiveText
     * @param negativeText
     * @param neturalColor
     * @param neturalText
     * @param listener
     */

    public void alertDialog(String title, String description, int titleColor,
                                           int descColor, int posColor, int negColor,
                                            String positiveText,
                                           String negativeText,int neturalColor,String neturalText,
                                           final DialogListenener listener){
        if(!title.equalsIgnoreCase("")) {
            if ((titleColor != 0)) {
                builder.setTitle(Html.fromHtml(FONT_COLOR + titleColor + BRANCH + title + FONT));
            } else {
                builder.setTitle(title);
            }
        }
        if ((descColor != 0)) {
            builder.setMessage(Html.fromHtml(FONT_COLOR + descColor + BRANCH + description + FONT));
        } else {
            builder.setMessage(description);
        }


        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {


                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // positive button logic
                        listener.onClickPositive();
                        dialog.dismiss();
                    }
                });

        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // negative button logic
                        listener.onClickNegative();
                        dialog.dismiss();
                    }
                });
        builder.setNeutralButton(neturalText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // netural button logic
                        listener.onClickNeutral();
                        dialog.dismiss();
                    }
                });
        // display dialog
        AlertDialog dialog = builder.create();
        dialog.show();

        // Get the alert dialog buttons reference
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        Button neutralButton = dialog.getButton(AlertDialog.BUTTON_NEUTRAL);

        // Change the alert dialog buttons text and background color
        if (posColor != 0) {
            positiveButton.setTextColor(posColor);
        } else {
            positiveButton.setTextColor(Color.parseColor(BLACK_COLOR));

        }

        if (negColor != 0) {
            negativeButton.setTextColor(negColor);
        } else {
            negativeButton.setTextColor(Color.parseColor(BLACK_COLOR));

        }
        if (neturalColor != 0) {
            neutralButton.setTextColor(neturalColor);
        } else {
            neutralButton.setTextColor(Color.parseColor(BLACK_COLOR));

        }
    }
    /**
     * It is the listener of the alert dialog.
     */
    public interface DialogListenener {

        /**
         * It is callled up on clicking the possitive button on the alert dialog.
         */
        public void onClickPositive();

        /**
         * It is callled up on clicking the negative button on the alert dialog.
         */
        public void onClickNeutral();

        /**
         * It is callled up on clicking the negative button on the alert dialog.
         */
        public void onClickNegative();
    }
}
