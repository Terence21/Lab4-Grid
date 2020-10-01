package temple.edu.gridassignment;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class PaletteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle(R.string.name_Palette_activity);
    }
}