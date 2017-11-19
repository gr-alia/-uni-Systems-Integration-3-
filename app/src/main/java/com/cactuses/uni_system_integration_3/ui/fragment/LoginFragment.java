package com.cactuses.uni_system_integration_3.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cactuses.uni_system_integration_3.App;
import com.cactuses.uni_system_integration_3.R;
import com.cactuses.uni_system_integration_3.model.AuthWrapper;
import com.cactuses.uni_system_integration_3.network.RetrofitService;
import com.cactuses.uni_system_integration_3.network.VidmeAPI;
import com.cactuses.uni_system_integration_3.utils.InputValidator;


/**
 * Created by Alyona on 01.10.2017.
 */

public class LoginFragment extends BaseFragment {
    private EditText mEditUsername;
    private EditText mEditPassword;
    private Button mLogin;

    private AuthWrapper mAuth;
    private SharedPreferences mPrefs;


    public LoginFragment() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mEditUsername = (EditText) view.findViewById(R.id.username);
        mEditPassword = (EditText) view.findViewById(R.id.password);
        mLogin = (Button) view.findViewById(R.id.login);

        mLogin.setOnClickListener(v -> {
            String username = mEditUsername.getText().toString();
            String password = mEditPassword.getText().toString();
           if (!InputValidator.isValid(username, password)){
              int resId = InputValidator.errResource;
               Toast.makeText(getActivity(), resId , Toast.LENGTH_LONG).show();
               return;
           }
            VidmeAPI api = RetrofitService.getInstance().getApi();

            subscribe(api.createAuth(username, password), authWrapper -> {
                mAuth = authWrapper;
                String token = mAuth.getAuth().getToken();
                mPrefs = getActivity().getSharedPreferences(
                        getString(R.string.pref_user_data), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mPrefs.edit();
                editor.putString(getString(R.string.saved_token), token);
                editor.commit();
                App app =  (App) getActivity().getApplication();
                app.setActiveSession(true);

                FeedFragment feedFragment = (FeedFragment)getParentFragment();
                feedFragment.replaceFragment();

                getActivity().invalidateOptionsMenu();
            });
        });
        return view;
    }

}
