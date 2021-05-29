package com.nodz.dishapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import com.nodz.dishapp.Adapter.DishAdapter;
import com.nodz.dishapp.Model.DishModel;
import com.nodz.dishapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //String json_urlmeal= "https://www.themealdb.com/api/json/v1/1/filter.php?c=Seafood";
    String json_url = "https://www.themealdb.com/api/json/v1/1/categories.php";
    DishAdapter adapter;

    List<DishModel> dishList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dishList = new ArrayList<>();
        recyclerView = findViewById(R.id.viewR);

        GetData getData = new GetData();
        getData.execute();

    }

    public class GetData extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... strings) {
            String current="";

            try{
                URL url;
                HttpURLConnection urlConnection = null;
                try{

                    url = new URL(json_url);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream is = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);

                    int data = isr.read();

                    while(data != -1){

                        current +=(char) data;
                        data = isr.read();
                    }
                    return current;

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    if(urlConnection != null){
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return  current;
        }

        @Override
        protected void onPostExecute(String s) {

            try{
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("categories");
                JSONArray jsonArraytwo = jsonObject.getJSONArray("meals");

                for(int i = 0 ;i<jsonArray.length(); i++){

                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    DishModel dishModel = new DishModel();
                    dishModel.setDetails(jsonObject1.getString("strCategoryDescription"));
                    dishModel.setName(jsonObject1.getString("strCategory"));
                    dishModel.setImg(jsonObject1.getString("strCategoryThumb"));

                    dishList.add(dishModel);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
        loadDataIntoRecyclerView(dishList);
        }
    }
    private void loadDataIntoRecyclerView(List<DishModel> dishList){
        adapter = new DishAdapter(this, dishList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

    }
}