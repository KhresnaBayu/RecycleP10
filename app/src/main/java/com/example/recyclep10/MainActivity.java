package com.example.recyclep10;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.example.recyclep10.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private boolean isGridView = false;
    ArrayList<Presiden> list;

    // data yang dimasukkan
    public static String[][] data = new String[][]{
            {"Soekarno", "Presiden Pertama", "https://upload.wikimedia.org/wikipedia/commons/thumb/0/01/Presiden_Sukarno.jpg/960px-Presiden_Sukarno.jpg?utm_source=commons.wikimedia.org&utm_campaign=index&utm_content=thumbnail"},
            {"Soeharto", "Presiden Kedua", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/59/President_Suharto%2C_1993.jpg/960px-President_Suharto%2C_1993.jpg"},
            {"Bacharuddin Jusuf Habibie", "Presiden Ketiga", "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2a/B._J._Habibie%2C_President_of_Indonesia_portrait.jpg/960px-B._J._Habibie%2C_President_of_Indonesia_portrait.jpg"},
            {"Abdurrahman Wahid", "Presiden Keempat", "https://upload.wikimedia.org/wikipedia/commons/thumb/3/35/President_Abdurrahman_Wahid_-_Indonesia.jpg/960px-President_Abdurrahman_Wahid_-_Indonesia.jpg"},
            {"Megawati Soekarnoputri", "Presiden Kelima", "https://upload.wikimedia.org/wikipedia/commons/thumb/8/88/President_Megawati_Sukarnoputri_-_Indonesia.jpg/960px-President_Megawati_Sukarnoputri_-_Indonesia.jpg"},
            {"Susilo Bambang Yudhoyono", "Presiden Keenam", "https://upload.wikimedia.org/wikipedia/commons/thumb/c/ce/Susilo_Bambang_Yudhoyono%2C_official_presidential_portrait_%282009%29.jpg/960px-Susilo_Bambang_Yudhoyono%2C_official_presidential_portrait_%282009%29.jpg"},
            {"Joko Widodo", "Presiden Ketujuh", "https://upload.wikimedia.org/wikipedia/commons/1/1c/Joko_Widodo_2014_official_portrait.jpg"},
            {"Prabowo", "Presiden Kedelapan", "https://upload.wikimedia.org/wikipedia/commons/thumb/d/d8/Prabowo_Subianto%2C_Candidate_for_Indonesia%27s_President_in_2024.jpg/330px-Prabowo_Subianto%2C_Candidate_for_Indonesia%27s_President_in_2024.jpg"}
    };

    private void showRecyclerView() {
        list = new ArrayList<>();

        for (int i = 0; i <data.length; i++) {
            list.add(new Presiden( data[i][0], data[i][1], data[i][2]));
        }

        // Animasi, import menggunakan androidx....
        TransitionManager.beginDelayedTransition(binding.recyclerView, new AutoTransition());

        // mengubah bentuk RecyclerView menjadi list atau grid view?
        if (isGridView) {
            binding.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else {
            binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, list);
        adapter.setViewType(isGridView); // kasih tau adapter ada perubahan layout
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        showRecyclerView();

        binding.appBar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.menu_grid) {
                isGridView = !isGridView;

                if (isGridView) { // ganti Icon
                    item.setIcon(R.drawable.baseline_view_list_24);
                } else {
                    item.setIcon(R.drawable.baseline_grid_view_24);
                }

                showRecyclerView();
                return true;
            }
            return false;
        });
    }
}