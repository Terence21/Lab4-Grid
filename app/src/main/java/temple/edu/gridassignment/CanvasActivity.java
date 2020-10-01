package temple.edu.gridassignment;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class CanvasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        this.setTitle(R.string.name_Canvas_activity);
    }
}