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
    private final Context context;
    private final Resources res;
    Configuration configuration;

    public ColorAdapter(Context context, ArrayList<String> colors, Resources res, Configuration configuration){
        this.context = context;
        this.colors = colors;
        this.res = res;
        this.configuration = configuration;
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

        if (convertView == null) {
            textView = new TextView(this.context);
        } else {
            textView = (TextView) convertView;
        }


        textView.setLines(5);
        textView.setGravity(Gravity.CENTER);


        Log.i("yes", configuration.getLocales().toLanguageTags());

        textView.setText(colors.get(position).toUpperCase());
        textView.setBackgroundColor(Color.parseColor(englishColor_toParse(position)));


        return textView;
    }

    /**
     *
     * @return if the current configuration is english or not
     */
    private boolean isFrench(){
        return !configuration.getLocales().toLanguageTags().equals("en");
    }

    /**
     * grab a color based off english configuration
     * @param position grab position of the english color array
     * @return the english color at the position
     */

    private String englishColor_toParse(int position){
        if (isFrench()) {
            Configuration config = new Configuration();
            config.setLocale(new Locale("en"));
            return context.getApplicationContext().createConfigurationContext(config)
                    .getResources()
                    .getStringArray(R.array.color_array)[position];
        }
        return colors.get(position);
    }
}
