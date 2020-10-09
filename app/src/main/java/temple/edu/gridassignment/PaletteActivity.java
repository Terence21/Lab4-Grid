package temple.edu.gridassignment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.LocaleList;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class PaletteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle(R.string.name_Palette_activity);

        TextView user_prompt = (TextView) findViewById(R.id._prompt);
        user_prompt.setText(R.string.prompt_text);
        user_prompt.setTextSize(12);
        user_prompt.setGravity(Gravity.CENTER);


        final Resources res = getResources();
        Configuration configuration = res.getConfiguration();
        String[] color_arr = res.getStringArray(R.array.color_array);
        ArrayList<String> colors = new ArrayList<>(Arrays.asList(color_arr));

        GridView grid = findViewById(R.id._ColorGrid);
        grid.setNumColumns(3);

        BaseAdapter adapter = new ColorAdapter(this,colors, configuration, res);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                send(view, position, PaletteActivity.this, res);
            }
        });
    }

    /**
     * send the text value of view to another activity
     * @param v TextView from from ColorAdapter to send text to activity
     */
    public void send(View v, int position, Context context, Resources res){
        Intent intent = new Intent(context, CanvasActivity.class);

        //respective language text from textView
        intent.putExtra(res.getString(R.string.Language), ((TextView)v).getText());

        //always send english output of array at position... is to be parsed by color
        Configuration configuration = new Configuration();
        configuration.setLocale(new Locale(res.getString(R.string.locale_en)));

        intent.putExtra(res.getString(R.string.English), this.getApplicationContext().createConfigurationContext(configuration)
                .getResources().getStringArray(R.array.color_array)[position]);
        startActivity(intent);

    }
}