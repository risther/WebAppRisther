package com.example.wapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class WebAppInterface {
    View view;
    Context context;
     WebView webView;
    @JavascriptInterface
    public void showToastMessage(String message,String email){


        createSnackBar(email);
    }

    public WebAppInterface(Context context, View view, WebView webView){
        this.context=context;
        this.view=view;
        this.webView = webView;
    }

    private void createSnackBar( String email) {

        

        Snackbar.make(view,"¿Está seguro de guardar el usuario \""+email+"\"?", Snackbar.LENGTH_INDEFINITE)
                .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_FADE)
                .setBackgroundTint(Color.parseColor("#dc3545"))
                .setAction("Confirmar", new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        AlertDialog dialog;
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Felicitaciones");
                        builder.setMessage("Su email es  \""+email);
                        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                webView.getSettings().setJavaScriptEnabled(true);

                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                                    webView.evaluateJavascript("limpiar();", null);
                                } else {
                                    webView.loadUrl("javascript:limpiar();");
                                }

                                run("limpiar()");
                            }
                        });
                        dialog = builder.create();
                        dialog.setCancelable(false);
                        dialog.setCanceledOnTouchOutside(false);
                        dialog.show();
                    }
                }).show();
    }

    public void run(final String scriptSrc) {
        webView.post(new Runnable() {
            @Override public void run() {
                webView.loadUrl("javascript:" + scriptSrc); }
        });
    }

}

