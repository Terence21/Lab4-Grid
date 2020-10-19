package temple.edu.gridassignment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements PaletteFragment.FragmentInteractionListener {

    boolean isClicked;
    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle(R.string.name_Palette_activity);

        TextView user_prompt = (TextView) findViewById(R.id._prompt);
        user_prompt.setText(R.string.prompt_text);
        user_prompt.setTextSize(12);
        user_prompt.setGravity(Gravity.CENTER);

        ArrayList<String> colors = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.color_array)));

        Bundle bundle = new Bundle();
        PaletteFragment fragment = PaletteFragment.newInstance(colors);
        bundle.putStringArrayList("key", colors);
        fragment.setArguments(bundle);


        fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.container_1, fragment)
                .commit();




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

        intent.putExtra(res.getString(R.string.position), position);
        startActivity(intent);

    }

    @Override
    public void displayColor(String color) {

    }
}