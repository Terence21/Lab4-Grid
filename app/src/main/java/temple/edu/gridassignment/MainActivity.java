package temple.edu.gridassignment;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements PaletteFragment.FragmentInteractionListener {

    private boolean clickedColor;
    private String color;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("create", "onCrate: created");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle(R.string.name_Palette_activity);

        TextView user_prompt = (TextView) findViewById(R.id._prompt);
        user_prompt.setText(R.string.prompt_text);
        user_prompt.setTextSize(12);
        user_prompt.setGravity(Gravity.CENTER);

        final Resources res = getResources();
        ArrayList<String> colors = new ArrayList<String>(Arrays.asList(res.getStringArray(R.array.color_array)));

        Bundle bundle = new Bundle();
        PaletteFragment fragment = PaletteFragment.newInstance(this, colors);
        bundle.putStringArrayList(getResources().getString(R.string.KEY), colors);
        fragment.setArguments(bundle);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.container_1, fragment)
                .commit();
    }


    /**
     * Save members to bundle when called
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.i("Save", "onSaveInstanceState: is saved");
        super.onSaveInstanceState(outState);
        outState.putBoolean(getResources().getString(R.string.clickedColor),clickedColor);
        outState.putString(getResources().getString(R.string.color),color);
        outState.putInt(getResources().getString(R.string.main_position),position);

    }

    /**
     * restore previous instance state.... onCreate isn't called but onRestore
     * @param savedInstanceState
     */
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState){
        Log.i("Restore", "onRestoreInstanceState: is restored");
        super.onRestoreInstanceState(savedInstanceState);
        this.clickedColor = savedInstanceState.getBoolean(getResources().getString(R.string.clickedColor));
        this.color = savedInstanceState.getString(getResources().getString(R.string.color));
        this.position = savedInstanceState.getInt(getResources().getString(R.string.main_position));

        ArrayList<String> colors = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.color_array)));

        Bundle bundle = new Bundle();
        PaletteFragment fragment = PaletteFragment.newInstance(this, colors);
        bundle.putStringArrayList(getResources().getString(R.string.KEY), colors);
        fragment.setArguments(bundle);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.container_1, fragment)
                .commit();

        displayColor(position, color);
    }

    /**
     * create a custom configuration to always send english color to the CanvasFragment
     * @param positon to send to the CanvasFragment
     * @param color to set the CanvasFragment to
     */
    @Override
    public void displayColor(int positon, String color) {

        CanvasFragment canvas = new CanvasFragment();
        Configuration english_configuration = new Configuration();
        english_configuration.setLocale(new Locale("en"));
        color = this.getApplicationContext().createConfigurationContext(english_configuration).getResources().getStringArray(R.array.color_array)[positon];

        canvas.defineColorView(positon, color);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if (clickedColor){
            ft.replace(R.id.container_2, canvas)
                    .commit();
        }else{
            ft.add(R.id.container_2, canvas)
                    .commit();
            clickedColor = true;
        }
        this.color = color;
        this.position = positon;
    }
}