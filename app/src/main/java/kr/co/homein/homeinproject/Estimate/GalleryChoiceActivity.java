package kr.co.homein.homeinproject.Estimate;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;

import kr.co.homein.homeinproject.R;

public class GalleryChoiceActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    SimpleCursorAdapter mAdapter;
    GridView gridView;
    int dataColumn = -1;
    String[] selectionArgs = {"image/jpeg"};
    String sortOrder = MediaStore.Images.Media.DATE_ADDED + " DESC";
    String[] projection = {MediaStore.Images.Media._ID , MediaStore.Images.Media.DISPLAY_NAME , MediaStore.Images.Media.DATA};
    String selection = MediaStore.Images.Media.MIME_TYPE + " LIKE ?";
    BitmapFactory.Options opts = new BitmapFactory.Options();
    ImageButton btn;
    final static String RESULT_LIST = "result_list";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gallery_choice);


        Toast.makeText(GalleryChoiceActivity.this, "갤러리로 이동", Toast.LENGTH_SHORT).show();

        gridView = (GridView) findViewById(R.id.grid_view);
        btn = (ImageButton) findViewById(R.id.upload_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray checkedlist = gridView.getCheckedItemPositions();
                ArrayList<ImageItem> list = new ArrayList<ImageItem>();
                for(int i = 0 ; i<checkedlist.size(); i++ ){
                    int position = checkedlist.keyAt(i);
                    if(checkedlist.get(position)){
                        ImageItem item = new ImageItem();
                        Cursor c = (Cursor) gridView.getItemAtPosition(position);
                        long id = c.getLong(c.getColumnIndex(MediaStore.Images.Media._ID));
                        item.uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI , id);
                        item.path = c.getString(c.getColumnIndex(MediaStore.Images.Media.DATA));
                        list.add(item);
                    }
                }


                Intent intent = new Intent();
                intent.putParcelableArrayListExtra(RESULT_LIST, list);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });


        String[] from = {MediaStore.Images.Media.DATA};
        int[]to = {R.id.image_thumbnail};
        mAdapter = new SimpleCursorAdapter(this, R.layout.grid_item_checked , null , from, to, 0);
        mAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
                if (columnIndex == dataColumn){
                    ImageView iv =(ImageView) view;
                    iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    String path = cursor.getString(columnIndex);
//                    opts.inSampleSize = 4;
//                    Bitmap bm = BitmapFactory.decodeFile(path , opts);
//                    iv.setImageBitmap(bm);
                    Glide.with(GalleryChoiceActivity.this).load(new File(path)).into(iv);
                    return true;
                }
                return false;
            }
        });
        gridView.setAdapter(mAdapter);
        gridView.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE);
        getSupportLoaderManager().initLoader(0, null, this);

    }





    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, MediaStore.Images.Media.EXTERNAL_CONTENT_URI , projection , selection ,
                selectionArgs, sortOrder);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        dataColumn = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
        mAdapter.swapCursor(cursor);
    }


    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

}
