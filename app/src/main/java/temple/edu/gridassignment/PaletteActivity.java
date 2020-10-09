package temple.edu.gridassignment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.LocaleList;
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


        Resources res = getResources();
        Configuration configuration = res.getConfiguration();
        String[] color_arr = res.getStringArray(R.array.color_array);
        ArrayList<String> colors = new ArrayList<>(Arrays.asList(color_arr));

        GridView grid = findViewById(R.id._ColorGrid);
        grid.setNumColumns(3);

        BaseAdapter adapter = new ColorAdapter(this,colors,res, configuration);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                send(view, position, PaletteActivity.this);
            }
        });
    }

    /**
     * send the text value of view to another activity
     * @param v TextView from from ColorAdapter to send text to activity
     */
    public void send(View v, int position, Context context){
        Intent intent = new Intent(PaletteActivity.this, CanvasActivity.class);

        //french text from textView
        intent.putExtra(Identifiers.LANGUAGE.id, ((TextView)v).getText());

        //always send english output of array at position
        Configuration configuration = new Configuration();
        configuration.setLocale(new Locale("en"));
        intent.putExtra(Identifiers.ENGLISH.id, this.getApplicationContext().createConfigurationContext(configuration)
                .getResources().getStringArray(R.array.color_array)[position]);
        startActivity(intent);

    }
}