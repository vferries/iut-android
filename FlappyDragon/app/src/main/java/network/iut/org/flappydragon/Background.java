package network.iut.org.flappydragon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Background {
    private int height;
    private int width;
    private Bitmap background1;
    private Bitmap background2;
    private Bitmap background3;
    private Bitmap background4;
    private Bitmap background5;
    private Bitmap background6;
    private Bitmap background7;
    private Bitmap background8;
    private Bitmap background9;
    private Bitmap background10;

    public Background(Context context) {
        height = context.getResources().getDisplayMetrics().heightPixels;
        width = context.getResources().getDisplayMetrics().widthPixels;
        background1 = Util.decodeSampledBitmapFromResource(context.getResources(), R.drawable.layer1, width, height);
        background2 = Util.decodeSampledBitmapFromResource(context.getResources(), R.drawable.layer2, width, height);
        background3 = Util.decodeSampledBitmapFromResource(context.getResources(), R.drawable.layer3, width, height);
        background4 = Util.decodeSampledBitmapFromResource(context.getResources(), R.drawable.layer4, width, height);
        background5 = Util.decodeSampledBitmapFromResource(context.getResources(), R.drawable.layer5, width, height);
        background6 = Util.decodeSampledBitmapFromResource(context.getResources(), R.drawable.layer1, width, height);
        background7 = Util.decodeSampledBitmapFromResource(context.getResources(), R.drawable.layer2, width, height);
        background8 = Util.decodeSampledBitmapFromResource(context.getResources(), R.drawable.layer3, width, height);
        background9 = Util.decodeSampledBitmapFromResource(context.getResources(), R.drawable.layer4, width, height);
        background10 = Util.decodeSampledBitmapFromResource(context.getResources(), R.drawable.layer5, width, height);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(background1, new Rect(0, 0, background1.getWidth(), background1.getHeight()), new Rect(0, 0, width, height), null);
        canvas.drawBitmap(background2, new Rect(0, 0, background1.getWidth(), background1.getHeight()), new Rect(0, 0, width, height), null);
        canvas.drawBitmap(background3, new Rect(0, 0, background1.getWidth(), background1.getHeight()), new Rect(0, 0, width, height), null);
        canvas.drawBitmap(background4, new Rect(0, 0, background1.getWidth(), background1.getHeight()), new Rect(0, 0, width, height), null);
        canvas.drawBitmap(background5, new Rect(0, 0, background1.getWidth(), background1.getHeight()), new Rect(0, 0, width, height), null);
        canvas.drawBitmap(background6, new Rect(0, 0, background1.getWidth(), background1.getHeight()), new Rect(0, 0, width, height), null);
        canvas.drawBitmap(background7, new Rect(0, 0, background1.getWidth(), background1.getHeight()), new Rect(0, 0, width, height), null);
        canvas.drawBitmap(background8, new Rect(0, 0, background1.getWidth(), background1.getHeight()), new Rect(0, 0, width, height), null);
        canvas.drawBitmap(background9, new Rect(0, 0, background1.getWidth(), background1.getHeight()), new Rect(0, 0, width, height), null);
        canvas.drawBitmap(background10, new Rect(0, 0, background1.getWidth(), background1.getHeight()), new Rect(0, 0, width, height), null);
    }
}
