package Utils;

/**
 * Created by Saurabh on 26-03-2018.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saurabh.munshi.R;

import java.util.ArrayList;

import Models.Article;

public class CustomAdapter extends BaseAdapter {

    Context c;
    ArrayList<Article> articles;

    public CustomAdapter(Context c, ArrayList<Article> articles) {
        this.c = c;
        this.articles = articles;
    }

    @Override
    public int getCount() {
        return articles.size();
    }

    @Override
    public Object getItem(int position) {
        return articles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null)
        {
            convertView= LayoutInflater.from(c).inflate(R.layout.model,parent,false);
        }

        TextView titleTxt= (TextView) convertView.findViewById(R.id.titleTxt);
        TextView descTxt= (TextView) convertView.findViewById(R.id.descTxt);
        TextView dateTxt= (TextView) convertView.findViewById(R.id.dateTxt);

        final Article article= (Article) this.getItem(position);

        titleTxt.setText(article.getTitle());
        descTxt.setText(article.getDescription().substring(0, article.getDescription().length()));
        dateTxt.setText(article.getDate());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(c, article.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}
