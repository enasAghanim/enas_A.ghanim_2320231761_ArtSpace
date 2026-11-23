
package com.example.artspace;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int currentIndex = 0;

    int[] images = {
            R.drawable.art1,
            R.drawable.art2,
            R.drawable.art3,
            R.drawable.art4,
            R.drawable.art5,
            R.drawable.art6
    };

    String[] titles = {
            "Mona Lisa",
            "The Starry Night",
            "Water Lilies",
            "The Scream",
            "Sunflowers",
            "Girl with a Pearl Earring"
    };

    String[] artists = {
            "Leonardo da Vinci - 1503",
            "Vincent van Gogh - 1889",
            "Claude Monet - 1906",
            "Edvard Munch - 1893",
            "Vincent van Gogh - 1888",
            "Johannes Vermeer - 1665"
    };

    TextView tvCounter, tvTitle, tvArtist;
    ImageView ivArtwork;
    Button btnPrevious, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCounter = findViewById(R.id.tvCounter);
        tvTitle = findViewById(R.id.tvTitle);
        tvArtist = findViewById(R.id.tvArtist);
        ivArtwork = findViewById(R.id.ivArtwork);
        ivArtwork.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,
                    ArtworkDetailsActivity.class);

            intent.putExtra("title", titles[currentIndex]);
            intent.putExtra("artist", artists[currentIndex]);

            startActivity(intent);
        });
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt("artworkIndex", 0);
        }

        updateArtwork();

        btnNext.setOnClickListener(v -> {
            if (currentIndex < 5) {
                currentIndex++;
                updateArtwork();
            }
        });

        btnPrevious.setOnClickListener(v -> {
            if (currentIndex > 0) {
                currentIndex--;
                updateArtwork();
            }
        });
    }

    private void updateArtwork() {
        ivArtwork.setImageResource(images[currentIndex]);
        tvTitle.setText(titles[currentIndex]);
        tvArtist.setText(artists[currentIndex]);
        tvCounter.setText((currentIndex + 1) + " / 6");

        btnPrevious.setEnabled(currentIndex != 0);
        btnNext.setEnabled(currentIndex != 5);
        btnPrevious.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(
                        android.graphics.Color.parseColor(
                                currentIndex == 0 ? "#D6D0CC" : "#96501F"
                        )
                )
        );

        btnNext.setBackgroundTintList(
                android.content.res.ColorStateList.valueOf(
                        android.graphics.Color.parseColor(
                                currentIndex == 5 ? "#D6D0CC" : "#96501F"
                        )
                )
        );
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("artworkIndex", currentIndex);
        super.onSaveInstanceState(outState);
    }
}
