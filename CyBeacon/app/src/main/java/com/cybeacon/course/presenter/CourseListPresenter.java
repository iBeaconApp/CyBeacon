package com.cybeacon.course.presenter;

import android.content.Context;
import android.os.Handler;

import com.cybeacon.base.BasePresenter;
import com.cybeacon.course.model.CourseResult;
import com.cybeacon.course.view.CourseListView;

import java.util.ArrayList;

/**
 * @author Ming
 */
//communicate
public class CourseListPresenter extends BasePresenter<CourseListView> {


    private Handler handler = new Handler();

    public CourseListPresenter(Context mContext) {
        super(mContext);
    }

    @Override
    protected void handleError(int errorCode, String message) {

    }

    /**
     * load course data list
     * @param currentPage  page
     */
    public void loadCourse(final int currentPage) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<CourseResult> mDatas = new ArrayList<>();
                for (int i = (currentPage-1)*10; i < currentPage * 10; i++) {
                    CourseResult courseResult = new CourseResult("course "+i);
                    mDatas.add(courseResult);
                }
                view.loadData(mDatas);
            }
        },1000);
    }
}
