package com.example.notes;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.BundleCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class CitiesFragment extends Fragment {

    private boolean isLandscape;

    public CitiesFragment() {
        // Required empty public constructor
    }

    private static final String BUNDLE_CONTENT = "bundle_content";
    public static CitiesFragment newInstance(final String content) {
        final CitiesFragment fragment = new CitiesFragment();
        final Bundle args = new Bundle();
        args.putString(BUNDLE_CONTENT, content);
        fragment.setArguments(args);
        return fragment;
    }
    private String content;

    // activity создана, можно к ней обращаться. Выполним начальные действия
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Определение, можно ли будет расположить рядом заметку в другом фрагменте
        isLandscape = getResources().getConfiguration().orientation
                == Configuration.ORIENTATION_LANDSCAPE;

        // Если можно поместить рядом фрагмент то сделаем это
        if (isLandscape) {
            showLandCoatOfArms(0);
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cities, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initList(view);
    }

    private void initList(View view) {
        LinearLayout layoutView = (LinearLayout) view;
        String[] cities = getResources().getStringArray(R.array.cities);

        // В этом цикле создаём элемент TextView,
        // заполняем его значениями,
        // и добавляем на экран.
        // Кроме того, создаём обработку касания на элемент
        Context context = getContext();
        for (int i = 0; i < cities.length; i++) {
            if (context != null) {
                String city = cities[i];
                TextView textView = new TextView(context);
                textView.setText(city);
                textView.setTextSize(30);
                layoutView.addView(textView);
                final int fi = i;
                textView.setOnClickListener(v -> showCoatOfArms(fi));
            }
        }
    }

    private void showCoatOfArms(int index) {
        if (isLandscape) {
            showLandCoatOfArms(index);
        } else {
            showPortCoatOfArms(index);
        }
    }

    // Показать заметку в ландшафтной ориентации
    private void showLandCoatOfArms(int index) {
        // Создаём новый фрагмент с текущей позицией
        CoatOfArmsFragment detail = CoatOfArmsFragment.newInstance(index);

        // Выполняем транзакцию по замене фрагмента
        FragmentActivity context = getActivity();
        if (context != null) {
            FragmentManager fragmentManager = context.getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.coat_of_arms, detail);  // замена фрагмента
            //fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.commit();
        }
    }

    // Показать заметку в портретной ориентации.
    private void showPortCoatOfArms(int index) {
        // Откроем вторую activity
        Context context = getContext();
        if (context != null) {
            Intent intent = new Intent(context, CoatOfArmsActivity.class);
            // и передадим туда параметры
            intent.putExtra(CoatOfArmsFragment.ARG_INDEX, index);
            startActivity(intent);
        }
    }
}
