package com.example.dwkyanglao.manage;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.dwkyanglao.R;


/**
 * Created by Administrator on 2019/4/9.
 */

public class Popwindow {
    public static PopupWindow pop_game_cj;
    public static PopupWindow Pop_game_cj(final Context mAc) {
        if (pop_game_cj == null) {
            View popupWindow_view = LayoutInflater.from(mAc).inflate(R.layout.poplanya, null, false);
            pop_game_cj = new PopupWindow(mAc);
            pop_game_cj.setBackgroundDrawable(new BitmapDrawable());
            pop_game_cj.setContentView(popupWindow_view);
            pop_game_cj.setOutsideTouchable(false);
            pop_game_cj.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
            pop_game_cj.setHeight(700);
            pop_game_cj.setFocusable(true);
        }
        return pop_game_cj;
    }

    public static void clearAll() {
        pop_game_cj=null;
    }
}
