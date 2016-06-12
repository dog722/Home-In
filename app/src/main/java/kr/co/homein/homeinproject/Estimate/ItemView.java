package kr.co.homein.homeinproject.Estimate;

/**
 * Created by seoeunbi on 2016. 5. 24..
 */

import android.content.Context;
import android.view.ViewGroup;
import android.widget.AbsListView;
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
    ImageView selectImage;

    private void init() {
        ViewGroup.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(params);
        inflate(getContext(), R.layout.wishlist_view, this);
        checkView = (ImageView)findViewById(R.id.checked);
        selectImage = (ImageView) findViewById(R.id.select_img);
    }

    private void drawCheck() {
        if (isChecked) {
//            checkView.setVisibility(View.VISIBLE);
            selectImage.setImageResource(selectId);


        } else {
//            checkView.setVisibility(View.GONE);
            selectImage.setImageResource(unselectedId);

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

    int selectId, unselectedId;
    public void setImage(int resID1 , int resID2){
            selectImage.setImageResource(resID2);
            selectId = resID1;
            unselectedId = resID2;
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
