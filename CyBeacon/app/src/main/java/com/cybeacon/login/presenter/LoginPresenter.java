package com.cybeacon.login.presenter;

import android.content.Context;

import com.cybeacon.base.BasePresenter;
import com.cybeacon.base.IPresenter;
import com.cybeacon.login.view.LoginView;

/**
 * @author Ming
 */
public class LoginPresenter extends BasePresenter<LoginView> {

    public LoginPresenter(Context mContext) {
        super(mContext);
    }

    @Override
    protected void handleError(int errorCode, String message) {

    }

}
