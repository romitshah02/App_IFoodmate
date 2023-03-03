package com.example.ifoodmate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.Nullable;


public class my_profile extends Fragment {
    TextView tv1,tv3;
    Button logout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_my_profile,
                    container, false);

            tv1 = rootView.findViewById(R.id.profile);
            tv1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent pf = new Intent(getActivity(), setting_page.class);

                    startActivity(pf);
                }
            });
            tv3 = rootView.findViewById(R.id.about_us);
            tv3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent ord = new Intent(getActivity(), aboutus_admin.class);
                    startActivity(ord);
                }
            });

            logout = rootView.findViewById(R.id.logout);
            logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),loginmain.class);
                    SharedPreferences pref = getContext().getSharedPreferences("login", Context.MODE_PRIVATE);
                    SharedPreferences name = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    SharedPreferences.Editor editor1 = name.edit();
                    editor.putBoolean("value",false);
                    editor1.putBoolean("uservalue",false);

                    editor1.apply();
                    editor.apply();
                    startActivity(intent);


                }
            });

            return rootView;

    }


}