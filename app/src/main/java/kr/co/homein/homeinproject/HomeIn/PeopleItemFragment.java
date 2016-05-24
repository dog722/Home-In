package kr.co.homein.homeinproject.HomeIn;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.matthewtamlin.sliding_intro_screen_library.DotIndicator;
import com.poliveira.parallaxrecycleradapter.ParallaxRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import kr.co.homein.homeinproject.MainActivity;
import kr.co.homein.homeinproject.R;
import kr.co.homein.homeinproject.data.PeopleItemData;


/**
 * A simple {@link Fragment} subclass.
 */
public class PeopleItemFragment extends Fragment {

    public static final int VIEW_TYPE_HEADER = 1;
    public static final int VEIW_TYPE_BODY = 2;
    List<PeopleItemData> peopleItem = new ArrayList<>();
    RecyclerView listView;
    ViewPager viewPager;
    EventPageAdapter eventPageAdapter;


    ParallaxRecyclerAdapter<PeopleItemData>  pAdapter;


//    final List<PeopleItemData> peopleItem = new ArrayList<>();
//    ParallaxRecyclerAdapter mAdapter;


    public PeopleItemFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mAdapter = new MainPeopleItemListAdapter();
//        setData();


        eventPageAdapter = new EventPageAdapter(getContext());
        pAdapter = new ParallaxRecyclerAdapter<>(peopleItem);
        pAdapter.implementRecyclerAdapterMethods(new ParallaxRecyclerAdapter.RecyclerAdapterMethods() {
            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
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
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.people_item_view, viewGroup, false);

                return new PeoPleItemListViewHolder(view);
            }

            @Override
            public int getItemCount() {
                return peopleItem.size();
            }
        });


        pAdapter.setOnClickEvent(new ParallaxRecyclerAdapter.OnClickEvent() {
            @Override
            public void onClick(View view, int i) {
                Intent intent = new Intent(getContext(), PeopleItemDetailActivity.class); //사용자 아이템 상세 페이지 이동
                startActivity(intent);
            }
        });

        pAdapter.setOnParallaxScroll(new ParallaxRecyclerAdapter.OnParallaxScroll() {
            @Override
            public void onParallaxScroll(float percentage, float offset, View parallax) {
                //TODO: implement toolbar alpha. See README for details
            }
        });

        setData();




    }

    @Override
    public void onResume() {
        super.onResume();
        listView.scrollToPosition(0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_people_item, container, false);

        listView = (RecyclerView) view.findViewById(R.id.rv_list);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);


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

        final DotIndicator infoIndicator = (DotIndicator) v.findViewById(R.id.dot);
        infoIndicator.setSelectedDotColor(Color.parseColor("#013ADF"));
        infoIndicator.setUnselectedDotColor(Color.parseColor("#CFCFCF"));

        pAdapter.setParallaxHeader(v, listView);

        infoIndicator.setNumberOfItems(eventPageAdapter.getCount());
        viewPager = (ViewPager)v.findViewById(R.id.eventPage);
        viewPager.setAdapter(eventPageAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //indicater 구현
                Toast.makeText(getContext(), "dfsdf", Toast.LENGTH_SHORT).show();
                // only one selected
                infoIndicator.setSelectedItem(viewPager.getCurrentItem(), true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return view;
    }



    private void setData() {

        for( int i = 0 ; i < 10 ; i ++){
            PeopleItemData p = new PeopleItemData();
            p.setGoodCount("20" + i);
            peopleItem.add(p);
            p.tag.add("태그1");
            p.tag.add("태그2");
        }

    }



}
