package com.cybeacon.course;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cybeacon.R;
import com.cybeacon.base.BaseMVPActivity;
import com.cybeacon.constants.IntentExtras;
import com.cybeacon.course.adapter.CourseListAdapter;
import com.cybeacon.course.model.CourseResult;
import com.cybeacon.course.presenter.CourseListPresenter;
import com.cybeacon.course.view.CourseListView;
import com.cybeacon.home.HomeActivity;
import com.cybeacon.professor.course_detail.CourseDetail;
import com.cybeacon.refresh.XZRecyclerView;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;

/**
 * @author Ming
 */

public class CourseListActivity extends BaseMVPActivity<CourseListPresenter,CourseListView> implements CourseListView{
    /**
     * courseList
     */
    private XZRecyclerView courseListView;

    private int currentPage = 1 ;
    private CourseListAdapter adapter;
    private String userType;

    @Override
    protected void businessCode() {
        userType = getIntent().getStringExtra(IntentExtras.UserType);
        //load data from server
        presenter.loadCourse(currentPage);

    }

    @Override
    protected void bindUI(View rootView) {
        courseListView = ((XZRecyclerView) findViewById(R.id.courseList));
    }

    @Override
    protected void bindEvent() {
        courseListView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
        courseListView.setOnRecyclerViewRefreshListener(refreshListener);
    }

    XZRecyclerView.OnRecyclerViewRefreshListener refreshListener = new XZRecyclerView.OnRecyclerViewRefreshListener() {
        @Override
        public void onLoadMore() {
            presenter.loadCourse(currentPage);
        }

        @Override
        public void onRefresh() {
            currentPage = 1;
            presenter.loadCourse(currentPage);
        }
    };

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_course_list_layout;
    }

    MultiItemTypeAdapter.OnItemClickListener onItemClickListener = new MultiItemTypeAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
            if (HomeActivity.UserType.PROFESSER.equals(userType)){
                Intent intent = new Intent(mContext, CourseDetail.class);
                startActivity(intent);
            }else {
                Intent intent = new Intent(mContext, com.cybeacon.student.course_detail.CourseDetail.class);
                startActivity(intent);
            }
        }

        @Override
        public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
            return false;
        }
    };

    @Override
    protected CourseListPresenter attachPresenter() {
        return new CourseListPresenter(mContext);
    }

    @Override
    protected CourseListView attachView() {
        return this;
    }

    @Override
    public void showErrorMessage(String errorMessage) {

    }

    @Override
    public void loadData(ArrayList<CourseResult> mDatas) {
        courseListView.refreshComplete();
        if (currentPage == 1){
            if (adapter == null){
                adapter = new CourseListAdapter(mContext,R.layout.item_course_layout,mDatas);
                adapter.setOnItemClickListener(onItemClickListener);
                courseListView.setIAdapter(adapter);
            }else {
                adapter.refreshDatas(mDatas);
            }
        }else {
            adapter.addDatas(mDatas);
        }
        currentPage++;
    }
}
