package ${YYAndroidPackageName};

//Game Maker Studio 2 Packages
import ${YYAndroidPackageName}.R;
import com.yoyogames.runner.RunnerJNILib;
import ${YYAndroidPackageName}.RunnerActivity;

//Some Android Packages
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.OnCompleteListener; 
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import android.content.Context;

public class GPReviewAPI extends RunnerActivity {

    public void RateApp() {

        Log.d("yoyo", "Try to open review panel");
        RunnerActivity.CurrentActivity.runOnUiThread(new Runnable() {

            public void run() {

                final ReviewManager manager = ReviewManagerFactory.create(RunnerActivity.CurrentActivity.getApplicationContext());
                Task<ReviewInfo> request = manager.requestReviewFlow();
        
                request.addOnCompleteListener(new OnCompleteListener<ReviewInfo>() {
                    public void onComplete(Task<ReviewInfo>task ) {
                        if (task.isSuccessful()) {
                            // We can get the ReviewInfo object
                            ReviewInfo reviewInfo = task.getResult();
                            Task<Void> flow = manager.launchReviewFlow(RunnerActivity.CurrentActivity, reviewInfo);
                            Log.d("yoyo", "Open review panel");
                        }
                    
                    }
                });

            }
        });           
    }
}