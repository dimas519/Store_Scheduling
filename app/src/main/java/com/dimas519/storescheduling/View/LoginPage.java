package com.dimas519.storescheduling.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dimas519.storescheduling.Presenter.LoginPresenter;
import com.dimas519.storescheduling.R;
import com.dimas519.storescheduling.databinding.FragmentLoginPageBinding;


public class LoginPage extends Fragment {
    private FragmentLoginPageBinding binding;
    private LoginPresenter presenter;


    public LoginPage() {
        // Required empty public constructor
    }


    public static LoginPage newInstance(LoginPresenter presenter) {
        LoginPage fragment = new LoginPage();
        fragment.presenter=presenter;
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.binding=FragmentLoginPageBinding.inflate(inflater);

        this.binding.loginBtn.setOnClickListener( view -> {
          String username=this.binding.username.getText().toString();
          String password=this.binding.password.getText().toString();



          if(!username.equals("") ||  !password.equals("")) {

              boolean loginSuc=this.presenter.Login(username,password);

              if(loginSuc){
                  getParentFragmentManager().setFragmentResult("login",new Bundle());
              }else{
                  Toast.makeText(getContext(), R.string.wrongLogin, Toast.LENGTH_SHORT).show();
              }
          }else{
              Toast.makeText(getContext(), R.string.fillLogin, Toast.LENGTH_SHORT).show();
          }
        });




        return this.binding.getRoot();
    }
}