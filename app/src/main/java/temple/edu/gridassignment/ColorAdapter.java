package temple.edu.gridassignment;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ColorAdapter extends BaseAdapter {

    private final ArrayList<String> colors;
    private final Context context;
    public ColorAdapter(Context context, ArrayList<String> colors){
        this.context = context;
        this.colors = colors;
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

        textView.setText(colors.get(position).toUpperCase());
        textView.setLines(5); textView.setGravity(Gravity.CENTER);
        textView.setBackgroundColor(Color.parseColor(colors.get(position)));

        return textView;
    }
}
