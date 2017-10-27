package com.huutrung.docbaoturss;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView lvNews;
    private NewsAdapter adapter;
    private final String vnexpressRSS ="https://vnexpress.net/rss/thoi-su.rss";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvNews = (ListView) findViewById(R.id.lvNews);
        lvNews.setOnItemClickListener(onItemClick);
        new LoadRSS().execute(vnexpressRSS);
    }

    private AdapterView.OnItemClickListener onItemClick = new AdapterView.OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
             Intent detail = new Intent(MainActivity.this, NewDetailActivity.class);
             detail.putExtra("LINK", adapter.getItem(i).getLink());
             startActivity(detail);
        }
    };

    class LoadRSS extends AsyncTask<String, Void, ArrayList<NewsModel>>{
        ProgressDialog dialog;//cho xoay xoay de load du lieu
        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setMessage("Loading....");
            dialog.setCancelable(false);
            dialog.show();
        }



        @Override
        protected ArrayList<NewsModel> doInBackground(String... params) {
            String url = params[0];
            ArrayList<NewsModel> result = new ArrayList<>();

            try {
                Document doc =  Jsoup.connect(url).get();
                Elements elements = doc.select("item");
                for (Element item :elements){
                    String title = item.select("title").text();
                    String link  = item.select("link").text();
                    String description = item.select("description").text();

                    //html dung Jsoup de parse no tiep de lay img

                    Document docImg = Jsoup.parse(description);
                    String imageURL = docImg.select("img").get(0).attr("src");

                    result.add(new NewsModel(title, link, imageURL));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            //load data ve va parse =>JSOUP
            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<NewsModel> newsModels) {
            dialog.dismiss();

            adapter = new NewsAdapter(MainActivity.this,0,newsModels,getLayoutInflater());
            lvNews.setAdapter(adapter);
        }
    }
}
