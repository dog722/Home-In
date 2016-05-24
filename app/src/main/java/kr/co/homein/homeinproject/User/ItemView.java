package kr.co.homein.homeinproject.User;

/**
 * Created by seoeunbi on 2016. 5. 24..
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.ImageView;

import kr.co.homein.homeinproject.R;

/**
 * Created by dongja94 on 2016-01-19.
 */
public class ItemView extends FrameLayout implements Checkable {
    public ItemView(Context context) {
        super(context);
        init();
    }

    ImageView checkView;
    private void init() {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(params);
        inflate(getContext(), R.layout.wishlist_view, this);
        checkView = (ImageView)findViewById(R.id.checked);
    }

    private void drawCheck() {
        if (isChecked) {
            checkView.setVisibility(View.VISIBLE);
        } else {
            checkView.setVisibility(View.GONE);
        }
    }

    boolean isChecked = false;
    @Override
    public void setChecked(boolean checked) {
        if (isChecked != checked) {
            isChecked = checked;
            drawCheck();
        }
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {
        setChecked(!isChecked);
    }
}
