package com.ykrc17.game.qrzdconfigeditor.layout;

import android.view.View;
import android.widget.TextView;
import com.ykrc17.game.qrzdconfigeditor.R;

public class CgListItemBinding {
  public TextView tv_id;

  public TextView tv_name;

  public CgListItemBinding() {
  }

  public CgListItemBinding(View view) {
    bind(view);
  }

  public void bind(View view) {
    tv_id = (TextView) view.findViewById(R.id.tv_id);
    tv_name = (TextView) view.findViewById(R.id.tv_name);
  }

  public int getLayoutRes() {
    return R.layout.cg_list_item;
  }
}
