package gaos.zhanchiff.com.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

public class BlurUtil {

    public static Bitmap blurBitmap(Context context, Bitmap orig, float radius,float scale,StringBuilder sb) {
        long cur = System.currentTimeMillis();
        sb.append("图片原始宽高"+orig.getWidth()+" * "+orig.getHeight());
        sb.append("\n");
        sb.append("模糊半径"+radius);
        sb.append("\n");
        // 计算图片缩小后的长宽
        int targetW = Math.round(orig.getWidth()*scale);
        int targetH = Math.round(orig.getHeight()*scale);

        Bitmap input = Bitmap.createScaledBitmap(orig, targetW, targetH, false);
        Bitmap out = Bitmap.createBitmap(input);

        RenderScript rs = RenderScript.create(context);
        ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        Allocation tmpIn = Allocation.createFromBitmap(rs, input);
        Allocation tmpOut = Allocation.createFromBitmap(rs, out);

        // 设置模糊程度
        script.setRadius(radius);
        script.setInput(tmpIn);
        script.forEach(tmpOut);

        tmpOut.copyTo(out);
        sb.append("耗时 "+(System.currentTimeMillis()-cur));
        return out;
    }
}
