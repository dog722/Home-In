package kr.co.homein.homeinproject.HomeIn;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.poliveira.parallaxrecycleradapter.ParallaxRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

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

                h.tag.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Intent intent = new Intent(getContext(), CompanyInfoActivity.class); //사용자 아이템 상세 페이지 이동
//                        startActivity(intent);
                        //이거 누르면 검색 창으로 가기 !!!!

                    }
                });
                h.setPeopleItem(peopleItem.get(i));
            }

            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.people_item_view, viewGroup,false);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_people_item, container, false);

        listView = (RecyclerView) view.findViewById(R.id.rv_list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setAdapter(pAdapter);
        listView.setLayoutManager(manager);
        listView.setHasFixedSize(true);

        //헤더 선언
        View v = LayoutInflater.from(getContext()).inflate(R.layout.homein_header_view, container , false);
        pAdapter.setParallaxHeader(v, listView);


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
        }

    }

}
