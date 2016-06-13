package kr.co.homein.homeinproject.HomeIn;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.matthewtamlin.sliding_intro_screen_library.DotIndicator;
import com.poliveira.parallaxrecyclerview.ParallaxRecyclerAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kr.co.homein.homeinproject.MainActivity;
import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.EventPageData;
import kr.co.homein.homeinproject.data.PeopleItemData;
import kr.co.homein.homeinproject.manager.NetworkManager;
import okhttp3.Request;


/**
 * A simple {@link Fragment} subclass.
 */
public class PeopleItemFragment extends Fragment {

    public static final int VIEW_TYPE_HEADER = 1;
    public static final int VEIW_TYPE_BODY = 2;
    List<PeopleItemData> peopleItem = new ArrayList<>();
    RecyclerView listView;
    ViewPager viewPager;
    ImageView imgBack;
    DotIndicator infoIndicator;
    EventPageAdapter    eventPageAdapter;
    String PH_number;
    final static String PH_NUMBER = "PH_number";
    ParallaxRecyclerAdapter<PeopleItemData>  pAdapter;

    public PeopleItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        eventPageAdapter = new EventPageAdapter(getContext());



        pAdapter = new ParallaxRecyclerAdapter<PeopleItemData>(peopleItem) {
            @Override
            public void onBindViewHolderImpl(RecyclerView.ViewHolder viewHolder, ParallaxRecyclerAdapter<PeopleItemData> parallaxRecyclerAdapter, int i) {
                PeoPleItemListViewHolder h = (PeoPleItemListViewHolder) viewHolder;

//                h.tag.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
////                        Intent intent = new Intent(getContext(), CompanyInfoActivity.class); //사용자 아이템 상세 페이지 이동
////                        startActivity(intent);
//                        //이거 누르면 검색 창으로 가기 !!!!
//
//                    }
//                });
                h.setPeopleItem(peopleItem.get(i));
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolderImpl(ViewGroup viewGroup, ParallaxRecyclerAdapter<PeopleItemData> parallaxRecyclerAdapter, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.people_item_view, viewGroup, false);
                return new PeoPleItemListViewHolder(view);
            }

            @Override
            public int getItemCountImpl(ParallaxRecyclerAdapter<PeopleItemData> parallaxRecyclerAdapter) {
                return peopleItem.size();
            }


        };


        pAdapter.setScrollMultiplier(1);



//        public interface OnItemScrollListener {
//            public void onItemClick(View view, PeopleItemData peopleItem);
//        }
//
//        OnItemClickListener mListener;
//        public void setOnItemClickListener(OnItemClickListener listener) {
//            mListener = listener;
//        }




        pAdapter.setOnParallaxScroll(new ParallaxRecyclerAdapter.OnParallaxScroll() {
            @Override
            public void onParallaxScroll(float percentage, float offset, View parallax) {
                //TODO: implement toolbar alpha. See README for details
//                Drawable c = imgBack.getBackground();
//                c.setAlpha(Math.round(percentage * 255));
//                imgBack.setBackground(c);
            }
        });



        pAdapter.setOnClickEvent(new ParallaxRecyclerAdapter.OnClickEvent() {
            @Override
            public void onClick(View view, int i) {
                Intent intent = new Intent(getContext(), PeopleItemDetailActivity.class); //사용자 아이템 상세 페이지 이동
                PH_number = peopleItem.get(i).getPH_number();
                intent.putExtra(PH_NUMBER, PH_number);
                startActivity(intent);
            }
        });


        setEventPage();

        setData();
    }

    @Override
    public void onResume() {
        super.onResume();
        listView.scrollToPosition(0);
        for(int i = 0 ; i <peopleItem.size() ; i++)
        pAdapter.removeItem(peopleItem.get(i));

        setData();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_people_item, container, false);

        listView = (RecyclerView) view.findViewById(R.id.rv_list);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);

//        View view2 =  inflater.inflate(R.layout.homein_header_view, container, false);
//        imgBack = (ImageView) view2.findViewById(R.id.img_back);


        listView.setAdapter(pAdapter);
        listView.setLayoutManager(manager);
        listView.setHasFixedSize(true);

        listView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE){

                    ((MainActivity) getActivity()).visibleEditBtn();
                    ((MainActivity) getActivity()).visibleMenuBtn();
                    ((MainActivity) getActivity()).visibleMapBtn();

                }
                else if(newState == RecyclerView.SCROLL_STATE_DRAGGING){
                    ((MainActivity) getActivity()).goneMenuBtn();
                    ((MainActivity) getActivity()).goneEditBtn();
                    ((MainActivity) getActivity()).goneMapBtn();
                }


            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


            }
        });


//        View mainView =   LayoutInflater.from(getContext()).inflate(R.layout.people_item_view, container , false); //사용자 아이템 뷰
//        layout = (FlowLayout) mainView.findViewById(R.id.tag_layout);


        //헤더 선언
        View v = LayoutInflater.from(getContext()).inflate(R.layout.homein_header_view, container , false);

        infoIndicator = (DotIndicator) v.findViewById(R.id.dot);
        infoIndicator.setSelectedDotColor(Color.parseColor("#013ADF"));
        infoIndicator.setUnselectedDotColor(Color.parseColor("#CFCFCF"));

        pAdapter.setParallaxHeader(v, listView);

        imgBack = (ImageView) v.findViewById(R.id.img_back);

        viewPager = (ViewPager)v.findViewById(R.id.eventPage);
        viewPager.setAdapter(eventPageAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //indicater 구현
//                Toast.makeText(getContext(), "dfsdf", Toast.LENGTH_SHORT).show();
                // only one selected
                infoIndicator.setNumberOfItems(3);
                infoIndicator.setSelectedItem(viewPager.getCurrentItem(), true);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        pAdapter.setOnParallaxScroll(new ParallaxRecyclerAdapter.OnParallaxScroll() {
            @Override
            public void onParallaxScroll(float v, float v1, View view) {
                Drawable c = imgBack.getBackground();
                c.setAlpha(Math.round(v * 255));
                imgBack.setBackground(c);
            }
        });


        return view;
    }



    private void setEventPage(){


        NetworkManager.getInstance().getEventPage(getContext(), new NetworkManager.OnResultListener<List<EventPageData>>() {
            @Override
            public void onSuccess(Request request, List<EventPageData> result) {
//                mAdapter.clear();

                eventPageAdapter.addAll(result);
//                    mAdapter.addAll(result);

            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(getContext(), "exception : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setData() {
        NetworkManager.getInstance().getPeopleItemList(getContext(), new NetworkManager.OnResultListener<List<PeopleItemData>>() {
            @Override
            public void onSuccess(Request request, List<PeopleItemData> result) {
//                mAdapter.clear();

                for(int i = 0 ; i< result.size() ; i++) {
                    pAdapter.addItem(result.get(i), i);
//                    mAdapter.addAll(result);
                }
                Log.d("test7", "dhodho");
            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(getContext(), "exception : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
