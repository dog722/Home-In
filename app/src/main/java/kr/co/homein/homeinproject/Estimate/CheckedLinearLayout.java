package kr.co.homein.homeinproject.Estimate;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.LinearLayout;

import kr.co.homein.homeinproject.R;

/**
 * Created by seoeunbi on 2016. 6. 3..
 */
public class CheckedLinearLayout extends LinearLayout implements Checkable {

    boolean isChecked = false;
    ImageView checkView;

    public CheckedLinearLayout(Context context) {
        super(context);
    }

    public CheckedLinearLayout(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        checkView = (ImageView)findViewById(R.id.checked);
    }

    @Override
    public void setChecked(boolean checked) {
        if(isChecked() != checked){
            isChecked = checked;
            drawChecked();
        }
    }

    public void drawChecked() {
        if (isChecked) { // check draw
            checkView.setVisibility(View.VISIBLE);
//            imageView.setImageResource(R.drawable.check);

        }else{ // un check draw
            checkView.setVisibility(View.GONE);

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
