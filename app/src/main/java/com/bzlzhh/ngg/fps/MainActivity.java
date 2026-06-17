package com.bzlzhh.ngg.fps;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView tv = new TextView(this);
        tv.setText(
            "NGG FPS Renderer terpasang.\n\n" +
            "Buka Zalith/FCL > Settings > Renderer,\n" +
            "lalu pilih \"NGG FPS Renderer\".\n\n" +
            "Plugin ini cuma menyediakan renderer; tidak perlu dibuka lagi."
        );
        tv.setTextSize(16f);
        tv.setPadding(48, 96, 48, 48);
        setContentView(tv);
    }
}
