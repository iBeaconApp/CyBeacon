package com.cybeacon.home;

import android.view.View;
import android.widget.Button;

import com.cybeacon.R;
import com.cybeacon.base.BaseMVPActivity;
import com.cybeacon.home.presenter.HomePresenter;
import com.cybeacon.home.view.HomeView;

/**
 * @author Ming
 */

public class HomeActivity extends BaseMVPActivity<HomePresenter,HomeView> implements HomeView {
    Button studentButton;
    private Button professorButton;

    @Override
    protected void businessCode() {

    }

    @Override
    protected void bindUI(View rootView) {
        studentButton = (Button) findViewById(R.id.studentButton);
        professorButton = (Button) findViewById(R.id.professorButton);
    }

    @Override
    protected void bindEvent() {
        studentButton.setOnClickListener(onClickListener);
        professorButton.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.studentButton:
                    presenter.forwardCourseListView();
                    break;
                case R.id.professorButton:
                    presenter.forwardLoginView();
                    break;
            }
        }
    };

    public static class UserType{
        public static String STUDENT = "student";
        public static String PROFESSER = "professor";
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_home_layout;
    }

    @Override
    protected HomePresenter attachPresenter() {
        return new HomePresenter(mContext);
    }

    @Override
    protected HomeView attachView() {
        return this;
    }

    @Override
    public void showErrorMessage(String errorMessage) {

    }
}
