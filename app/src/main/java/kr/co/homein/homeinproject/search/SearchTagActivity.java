package kr.co.homein.homeinproject.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.SearchData;
import kr.co.homein.homeinproject.data.SearchListDataResult;
import kr.co.homein.homeinproject.manager.NetworkManager;
import okhttp3.Request;

public class SearchTagActivity extends AppCompatActivity {


    EditText editText;
    RecyclerView listView;
    SearchAdapter mAdapter;
    String tag;
    int totalCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_tag);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.back_bt_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "center toolbar navigation click", Toast.LENGTH_SHORT).show();
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                finish();
            }
        });

        listView = (RecyclerView) findViewById(R.id.rv_list4);
        mAdapter = new SearchAdapter();
        listView.setAdapter(mAdapter);
        listView.setLayoutManager(new LinearLayoutManager(this));


        mAdapter.setOnItemClickListener(new SearchViewHolder.OnItemClickListener() {
            @Override
            public void onItemClick(View view, SearchData searchData) {

                Intent intent = new Intent(SearchTagActivity.this, SearchResultActivity.class);
                intent.putExtra("tag", searchData.getTag_name());
                startActivity(intent); //검색 결과창으로 이동.
                finish();
            }
        });

        editText = (EditText) findViewById(R.id.search_tag);
        editText.requestFocus();
        editText.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        editText.setInputType(InputType.TYPE_CLASS_TEXT);

        //키보드 보이게 하는 부분
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH:
//                        Toast.makeText(getApplicationContext(), "검색", Toast.LENGTH_LONG).show();
                        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                        //여기서 검색 키워드 서버에 보내주기
                         tag = editText.getText().toString();
                        setData();
                        break;
                    default:
//                        Toast.makeText(getApplicationContext(), "기본", Toast.LENGTH_LONG).show();
                        return false;
                }
                return true;
            }
        });

    }

    private void setData() {
        NetworkManager.getInstance().getSearchResult(this, tag, new NetworkManager.OnResultListener<SearchListDataResult>() {
            @Override
            public void onSuccess(Request request,SearchListDataResult result) {
//                Toast.makeText(SearchTagActivity.this, " tag:" + tag, Toast.LENGTH_SHORT).show();

                if(result.getSearchData().size() !=0) {

                    mAdapter.clear();
                    mAdapter.addAll(result.getSearchData());
                }

                mAdapter.addHeader(result.getTotal_count() + "");

            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(SearchTagActivity.this, "server disconnected", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
