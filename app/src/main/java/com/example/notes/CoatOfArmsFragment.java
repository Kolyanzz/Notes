package com.example.notes;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

public class CoatOfArmsFragment extends Fragment {

    static final String ARG_INDEX = "index";
    private int index;

    public static CoatOfArmsFragment newInstance(int index) {
        CoatOfArmsFragment f = new CoatOfArmsFragment();    // создание
        // Передача параметра
        Bundle args = new Bundle();
        args.putInt(ARG_INDEX, index);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_INDEX);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coat_of_arms, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText editText = view.findViewById(R.id.coat_of_arms);
        // Получить из ресурсов массив указателей на заметку
        TypedArray images = getResources().obtainTypedArray(R.array.coat_of_arms_imgs);
        // Выбрать по индексу подходящий
        editText.setText(images.getResourceId(index, -1));
    }
}
