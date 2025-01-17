package org.milaifontanals.appsprite;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.ViewTreeObserver;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.milaifontanals.appsprite.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //setContentView(R.layout.activity_main);


        binding = ActivityMainBinding.inflate(this.getLayoutInflater());
        setContentView(binding.getRoot());

        binding.getRoot().getViewTreeObserver().addOnGlobalLayoutListener( () -> {
            // Aquest event permet saber l'amplada de totes les coses
            AnimationDrawable ad = (AnimationDrawable) binding.imvWorm.getDrawable();
            ad.start();
            int angle = 0;

            ObjectAnimator animator1 = ObjectAnimator.ofFloat(
                    binding.imvWorm, "X", 0).setDuration(2000);

            angle += 90;
            ObjectAnimator animator2 = ObjectAnimator.ofFloat(
                    binding.imvWorm, "rotation", angle).setDuration(1000);

            ObjectAnimator animator3 = ObjectAnimator.ofFloat(
                    binding.imvWorm, "Y", 0).setDuration(2000);

            angle += 90;
            ObjectAnimator animator4 = ObjectAnimator.ofFloat(
                    binding.imvWorm, "rotation", angle).setDuration(1000);

            ObjectAnimator animator5 = ObjectAnimator.ofFloat(
                    binding.imvWorm, "X", binding.getRoot().getWidth()-binding.imvWorm.getWidth() ).setDuration(2000);

            angle += 90;

            ObjectAnimator animator6 = ObjectAnimator.ofFloat(
                    binding.imvWorm, "rotation", angle).setDuration(1000);

            ObjectAnimator animator7 = ObjectAnimator.ofFloat(
                    binding.imvWorm, "Y",
                    this.getWindowManager().getDefaultDisplay().getHeight()-binding.imvWorm.getHeight()
                    ).setDuration(2000);


            angle += 90;
            ObjectAnimator animator8 = ObjectAnimator.ofFloat(
                    binding.imvWorm, "rotation", angle).setDuration(1000);


            AnimatorSet set = new AnimatorSet();
//
            set.playSequentially(animator1, animator2, animator3, animator4, animator5, animator6, animator7, animator8);

            set.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    set.start();
                }
            });


            set.start();

            /*ObjectAnimator animator = ObjectAnimator.ofFloat(
                    binding.imvWorm, "translationX",
                    -(this.getWindowManager().getDefaultDisplay().getWidth()
                            -
                            binding.imvWorm.getWidth()));*/


        });



    }
}