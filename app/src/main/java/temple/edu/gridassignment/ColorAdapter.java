package temple.edu.gridassignment;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.LocaleList;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class ColorAdapter extends BaseAdapter {

    private ArrayList<String> colors;
    private Context context;
    private final Resources res;

    public ColorAdapter(Context context, ArrayList<String> colors, Resources res){
        this.context = context;
        this.colors = colors;
        this.res = res;
    }
    @Override
    public int getCount() {
        return colors.size();
    }

    @Override
    public Object getItem(int position) {
        return colors.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * To set size of cell, enlarge textView to size of 5 lines, and center text
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;

        if (convertView == null){
            textView = new TextView(this.context);
        } else{
            textView = (TextView) convertView;
        }


        textView.setLines(5); textView.setGravity(Gravity.CENTER);



        Configuration configuration = new Configuration();
        configuration.setLocale(new Locale("en"));

        if (isFrench()) {
            colors = new ArrayList<String>(Arrays.asList(context.getApplicationContext().createConfigurationContext(configuration).getResources().getStringArray(R.array.color_array)));
            Log.i("yes", colors.get(position));
            textView.setBackgroundColor(Color.parseColor(colors.get(position)));
        }
        if (isFrench()) {
             configuration.setLocale(new Locale("fr"));
            colors = new ArrayList<String>(Arrays.asList(context.getApplicationContext().createConfigurationContext(configuration).getResources().getStringArray(R.array.color_array)));
            textView.setText(colors.get(position).toUpperCase());
        }

        return textView;
    }

    private boolean isFrench(){
        LocaleList localelist = res.getConfiguration().getLocales();
        Locale locale = localelist.get(0);
        return !locale.getCountry().equals("en");
    }
}
