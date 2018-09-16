package com.ykrc17.game.qrzdconfigeditor.layout;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ykrc17.game.qrzdconfigeditor.R;

public class ActivityMainBinding {
  public ImageView iv_erubi;

  public TextView tv_version;

  public CheckBox cb_frame_rate;

  public LinearLayout ll_ending;

  public EditText et_ending;

  public Button btn_go_cg_list;

  public Button btn_decrement;

  public Button btn_increment;

  public TextView tv_debug;

  public ActivityMainBinding() {
  }

  public ActivityMainBinding(View view) {
    bind(view);
  }

  public void bind(View view) {
    iv_erubi = (ImageView) view.findViewById(R.id.iv_erubi);
    tv_version = (TextView) view.findViewById(R.id.tv_version);
    cb_frame_rate = (CheckBox) view.findViewById(R.id.cb_frame_rate);
    ll_ending = (LinearLayout) view.findViewById(R.id.ll_ending);
    et_ending = (EditText) view.findViewById(R.id.et_ending);
    btn_go_cg_list = (Button) view.findViewById(R.id.btn_go_cg_list);
    btn_decrement = (Button) view.findViewById(R.id.btn_decrement);
    btn_increment = (Button) view.findViewById(R.id.btn_increment);
    tv_debug = (TextView) view.findViewById(R.id.tv_debug);
  }

  public int getLayoutRes() {
    return R.layout.activity_main;
  }
}
