package temple.edu.gridassignment;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Log;
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
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements PaletteFragment.FragmentInteractionListener {

    boolean clickedColor;
    FragmentManager fm;
    public final static String KEY = "key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle(R.string.name_Palette_activity);

        clickedColor = false;

        TextView user_prompt = (TextView) findViewById(R.id._prompt);
        user_prompt.setText(R.string.prompt_text);
        user_prompt.setTextSize(12);
        user_prompt.setGravity(Gravity.CENTER);

        final Resources res = getResources();
        ArrayList<String> colors = new ArrayList<String>(Arrays.asList(res.getStringArray(R.array.color_array)));

        Bundle bundle = new Bundle();
        PaletteFragment fragment = PaletteFragment.newInstance(colors);
        bundle.putStringArrayList(MainActivity.KEY, colors);
        fragment.setArguments(bundle);


        fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.container_1, fragment)
                .commit();


    }


    @Override
    public void onPause(){
        super.onPause();
        clickedColor = false;

    }



    @Override
    public void updateConfiguration(Configuration config){

    }


    @Override
    public void displayColor(int positon, String color) {

        CanvasFragment canvas = new CanvasFragment();
        canvas.defineColorView(positon, color);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if (clickedColor){
            ft.replace(R.id.container_2, canvas)
                    .commit();
        } else{
            ft.add(R.id.container_2, canvas)
                    .commit();
            clickedColor = true;
        }
    }
}