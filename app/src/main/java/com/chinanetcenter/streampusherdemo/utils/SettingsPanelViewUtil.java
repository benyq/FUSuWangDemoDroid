package com.chinanetcenter.streampusherdemo.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.PopupWindow;

import com.chinanetcenter.streampusherdemo.R;
import com.chinanetcenter.streampusherdemo.adapter.SettingsAdapter;
import com.chinanetcenter.streampusherdemo.object.SettingItem;

public class SettingsPanelViewUtil {

    private Activity mActivity;
    private PopupWindow mPopupWindow;
    private GridView mGridView;

    // With action button
    public SettingsPanelViewUtil(Activity activity, final SettingsAdapter adapter, final OnItemClickListener listener) {
        mActivity = activity;
        LayoutInflater mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = mInflater.inflate(R.layout.setting_panel, null);
        mGridView = (GridView) view.findViewById(R.id.setting_panel);
        mGridView.setAdapter(adapter);
        mGridView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listener != null) {
                    listener.onItemClick(parent, view, position, id);
                }
                SettingItem item = adapter.getItem(position);
                if (item != null && item.dismissAfterClick) {
                    dismiss();
                }

            }
        });
        mPopupWindow = new PopupWindow(view, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setAnimationStyle(R.style.setting_popwindow_anim_style);
        mPopupWindow.setBackgroundDrawable(getDrawable());
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NOT_NEEDED);
        mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
    }

    private Drawable getDrawable() {
        ShapeDrawable bgdrawable = new ShapeDrawable(new OvalShape());
        bgdrawable.getPaint().setColor(mActivity.getResources().getColor(android.R.color.transparent));
        return bgdrawable;
    }

    public void show() {
        mPopupWindow.showAtLocation(mActivity.getWindow().getDecorView(), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    public boolean isShowing() {
        return mPopupWindow.isShowing();
    }

    public void dismiss() {
        mPopupWindow.dismiss();
    }

}