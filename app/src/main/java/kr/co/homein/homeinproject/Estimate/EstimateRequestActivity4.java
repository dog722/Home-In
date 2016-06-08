package kr.co.homein.homeinproject.Estimate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.EstimateDetailData;

public class EstimateRequestActivity4 extends AppCompatActivity {

    Button nextBtn;
    final static int REQUEST_IMAGE_CHOICE = 1;
    EstimateDetailData estimateDetailData;
    ImageView uploadImg;

    String general_bumer;
    String office_number;
    String estimate_space;
    String estimate_sub_space;
    String estimate_size;
    String general_real_name;
    String general_email;
    String general_tel;
    String interior_info_content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estimate_request4);

        Intent intent = getIntent();
        estimateDetailData = (EstimateDetailData) intent.getSerializableExtra(EstimateRequestActivity.ESTIMATE_DATA);



        //사진 업로드 하는 버튼
        uploadImg = (ImageView) findViewById(R.id.plus);
        uploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EstimateRequestActivity4.this, "업로드 버튼 누르기 전 ", Toast.LENGTH_SHORT).show();
                startActivityForResult(new Intent(EstimateRequestActivity4.this , GalleryChoiceActivity.class), REQUEST_IMAGE_CHOICE);
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "center toolbar navigation click", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        nextBtn = (Button) findViewById(R.id.next_btn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EstimateRequestActivity4.this, EstimateRequestActivity5.class);
                intent.putExtra(EstimateRequestActivity.ESTIMATE_DATA, estimateDetailData);
                startActivity(intent); //견적문의 2페이지로 이동
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CHOICE && resultCode == Activity.RESULT_OK){
            ArrayList<ImageItem> list = data.getParcelableArrayListExtra(GalleryChoiceActivity.RESULT_LIST);

            //resultImage는 얻어온 uri
            ArrayList<String> resultImage = new ArrayList<String>();
            for(int i = 0 ; i < list.size() ; i++){
                resultImage.add(list.get(i).path);
//                estimateDetailData.getInterior_picture().set(list.get(i).uri.toString());
//                interior_picture.add(new File(imagName));
            }

            estimateDetailData.setInterior_picture(resultImage);

//            Toast.makeText(EstimateRequestActivity4.this, "얻어온 url[0] : " + resultImage.get(0), Toast.LENGTH_SHORT).show();

        }
    }



}
