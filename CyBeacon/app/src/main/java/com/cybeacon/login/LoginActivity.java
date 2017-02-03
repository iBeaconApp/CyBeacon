package com.cybeacon.login;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.cybeacon.R;
import com.cybeacon.base.BaseMVPActivity;
import com.cybeacon.constants.IntentExtras;
import com.cybeacon.course.CourseListActivity;
import com.cybeacon.home.HomeActivity;
import com.cybeacon.login.presenter.LoginPresenter;
import com.cybeacon.login.view.LoginView;

/**
 * @author Ming
 */

public class LoginActivity extends BaseMVPActivity<LoginPresenter,LoginView> implements LoginView{
    private Button loginButton;

    @Override
    protected void businessCode() {

    }

    @Override
    protected void bindUI(View rootView) {
        loginButton = ((Button) findViewById(R.id.loginButton));
    }

    @Override
    protected void bindEvent() {
        loginButton.setOnClickListener(onClickListener);
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.loginButton:
                    Intent intent = new Intent(mContext, CourseListActivity.class);
                    intent.putExtra(IntentExtras.UserType, HomeActivity.UserType.PROFESSER);
                    startActivity(intent);
                    finish();
                    break;
            }
        }
    };

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login_layout;
    }

    @Override
    protected LoginPresenter attachPresenter() {
        return null;
    }

    @Override
    protected LoginView attachView() {
        return null;
    }

    @Override
    public void showErrorMessage(String errorMessage) {

    }
}
