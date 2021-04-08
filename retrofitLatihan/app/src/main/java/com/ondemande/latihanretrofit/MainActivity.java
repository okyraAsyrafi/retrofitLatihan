package com.ondemande.latihanretrofit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ondemande.latihanretrofit.retrofit.ApiService;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MainActivity";
    private List<MainModel.Result> results = new ArrayList<>();
    ProgressBar progressBar;
    RecyclerView recyclerView;
    MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpView();
        setUpRecyclerview();
        getListUsersFromApi();
    }

    private void setUpView() {
        recyclerView = findViewById(R.id.rvitemretrofit);
        progressBar = findViewById(R.id.pbprogress);
    }

    private void setUpRecyclerview() {
        mainAdapter = new MainAdapter(results, new MainAdapter.OnAdapterListener() {
            @Override
            public void onClick(MainModel.Result data) {
//                Toast.makeText(MainActivity.this, data.getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("INTENT_TITLE",data.getTitle());
                intent.putExtra("INTENT_IMAGE",data.getImage());
                startActivity(intent);
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mainAdapter);
    }

    private void getListUsersFromApi() {
        progressBar.setVisibility(View.VISIBLE);
        ApiService.endpoint().getListUsers().enqueue(new Callback<MainModel>() {
            @Override
            public void onResponse(Call<MainModel> call, Response<MainModel> response) {

                if (response.isSuccessful()) {
                    progressBar.setVisibility(View.GONE);
                    List<MainModel.Result> results = response.body().getResult();
                    Log.d(TAG, results.toString());
                    mainAdapter.setData(results);
                }
            }

            @Override
            public void onFailure(Call<MainModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Log.d(TAG, t.toString());
            }
        });
    }
}