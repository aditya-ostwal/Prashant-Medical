package girisanimation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.mtel.shoe.R;
import com.shoeARstore.HomeFragment;

/* loaded from: classes10.dex */
public class SplashActivity extends Activity {
    private static int SPLASH_TIME_OUT = 4000;

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setAnimation();
        getWindow().getDecorView().setSystemUiVisibility(2);
        new Handler().postDelayed(new Runnable() { // from class: girisanimation.SplashActivity.1
            @Override // java.lang.Runnable
            public void run() {
                Intent i = new Intent(SplashActivity.this, HomeFragment.class);
                SplashActivity.this.startActivity(i);
                SplashActivity.this.finish();
            }
        }, SPLASH_TIME_OUT);
    }

    private void setAnimation() {
        ObjectAnimator scaleXAnimation = ObjectAnimator.ofFloat(findViewById(R.id.welcome_text), "scaleX", 5.0f, 1.0f);
        scaleXAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleXAnimation.setDuration(1000L);
        ObjectAnimator scaleYAnimation = ObjectAnimator.ofFloat(findViewById(R.id.welcome_text), "scaleY", 5.0f, 1.0f);
        scaleYAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleYAnimation.setDuration(2000L);
        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(findViewById(R.id.welcome_text), "alpha", 0.0f, 1.0f);
        alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        alphaAnimation.setDuration(2000L);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleXAnimation).with(scaleYAnimation).with(alphaAnimation);
        animatorSet.setStartDelay(1000L);
        animatorSet.start();
        findViewById(R.id.imagelogo2).setAlpha(1.0f);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.fade);
        findViewById(R.id.imagelogo2).startAnimation(anim);
    }
}