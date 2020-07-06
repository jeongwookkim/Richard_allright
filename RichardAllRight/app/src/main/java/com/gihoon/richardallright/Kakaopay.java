package com.gihoon.richardallright;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class Kakaopay extends AppCompatActivity {
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kakaopay);

        WebView wV = findViewById(R.id.webview);
        Intent before = getIntent();
        String url = before.getStringExtra("url");


        wV.setWebViewClient(new BWebviewClient());
        wV.getSettings().setJavaScriptEnabled(true);
        wV.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wV.getSettings().setAllowContentAccess(true);
        wV.loadUrl(url);
    }

    private class BWebviewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Map<String, String> a = getQueryMap(url);
            String path = null;
            try {
                URL b = new URL(url);
                path = b.getPath();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            System.out.println(path);
            if (path != null) {
                if (path.equals("/cancel")) {
                    Intent im = new Intent(view.getContext(), Payment_confirm.class);
                    im.putExtra("pg_token", "cancel");
                    startActivity(im);
                    finish();
                    return true;
                } else if (path.equals("/fail")) {
                    Intent im = new Intent(view.getContext(), Payment_confirm.class);
                    im.putExtra("pg_token", "fail");
                    startActivity(im);
                    finish();
                    return true;
                }
            }
            if (a.get("pg_token") != null) {
                Intent im = new Intent(view.getContext(), Payment_confirm.class);
                im.putExtra("pg_token", a.get("pg_token"));
                startActivity(im);
                finish();
                return true;
            } else {
                Intent intent = parse(url);

                if (isIntent(url)) {
                    if (isExistInfo(intent, view.getContext()) || isExistPackage(intent, view.getContext())) {
                        return start(intent, view.getContext());

                    } else
                        gotoMarket(intent, view.getContext());
                } else if (isMarket(url)) {
                    return start(intent, view.getContext());
                }
                return url.contains("https://bootpaymark");
            }
        }

        private Intent parse(String url) {
            try {
                return Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
            } catch (URISyntaxException e) {
                e.printStackTrace();
                return null;
            }
        }

        private Boolean isIntent(String url) {
            return url.matches("^intent:?\\w*://\\S+$");
        }

        private Boolean isMarket(String url) {
            return url.matches("^market://\\S+$");
        }

        private Boolean isExistInfo(Intent intent, Context context) {
            try {
                return intent != null && context.getPackageManager().getPackageInfo(intent.getPackage(), PackageManager.GET_ACTIVITIES) != null;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                return false;
            }
        }

        private Boolean isExistPackage(Intent intent, Context context) {
            return intent != null && context.getPackageManager().getLaunchIntentForPackage(intent.getPackage()) != null;
        }

        private boolean start(Intent intent, Context context) {
            context.startActivity(intent);
            return true;
        }

        private boolean gotoMarket(Intent intent, Context context) {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + intent.getPackage())));
            return true;
        }
    }

    public static Map<String, String> getQueryMap(String query) {
        if (query == null) return null;

        int pos1 = query.indexOf("?");
        if (pos1 >= 0) {
            query = query.substring(pos1 + 1);
        }

        String[] params = query.split("&");
        Map<String, String> map = new HashMap<String, String>();
        if (params.length >= 2) {
            for (String param : params) {
                String name = param.split("=")[0];
                String value = param.split("=")[1];
                map.put(name, value);
            }
        }
        return map;
    }
}

