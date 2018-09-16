package com.ykrc17.game.qrzdconfigeditor.layout;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ykrc17.game.qrzdconfigeditor.R;

public class ActivityMainBinding {
  public CheckBox cb_frame_rate;

  public LinearLayout ll_ending;

  public EditText et_ending;

  public TextView tv_debug;

  public ActivityMainBinding() {
  }

  public ActivityMainBinding(View view) {
    bind(view);
  }

  public void bind(View view) {
    cb_frame_rate = (CheckBox) view.findViewById(R.id.cb_frame_rate);
    ll_ending = (LinearLayout) view.findViewById(R.id.ll_ending);
    et_ending = (EditText) view.findViewById(R.id.et_ending);
    tv_debug = (TextView) view.findViewById(R.id.tv_debug);
  }

  public int getLayoutRes() {
    return R.layout.activity_main;
  }
}
