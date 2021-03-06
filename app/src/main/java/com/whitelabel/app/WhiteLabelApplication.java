package com.whitelabel.app;

import android.content.Context;
import android.content.res.Configuration;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import com.android.volley.Request;
import com.bumptech.glide.request.target.ViewTarget;
import com.crashlytics.android.Crashlytics;
import com.facebook.FacebookSdk;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;

import com.orhanobut.logger.AndroidLogAdapter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.whitelabel.app.exception.CrashHandler;
import com.whitelabel.app.model.ApplicationConfigurationEntity;
import com.whitelabel.app.model.PhoneConfigurationEntity;
import com.whitelabel.app.network.HttpClientRequest;
import com.whitelabel.app.utils.JLogUtils;
import com.whitelabel.app.utils.JToolUtils;
import com.whitelabel.app.utils.LanguageUtils;

import injection.components.ApplicationComponent;
import injection.components.DaggerApplicationComponent;
import io.fabric.sdk.android.Fabric;

/**
 * Created by imaginato on 2015/6/10.
 */
public class WhiteLabelApplication extends MultiDexApplication {
    private static WhiteLabelApplication mInstance;
    public static boolean delayShowAppRate = false;
    private static PhoneConfigurationEntity phoneConfiguration;
    private static ApplicationConfigurationEntity appConfiguration;
    private Tracker mTracker;
    private GoogleAnalytics analytics;
    private static ApplicationComponent mApplicationComponent;
    private void initializeComponents() {
        mApplicationComponent = DaggerApplicationComponent
                .Initializer.init(this);
    }
    public static ApplicationComponent getApplicationComponent(){
        return mApplicationComponent;
    }
    public static PhoneConfigurationEntity getPhoneConfiguration() {
        if (phoneConfiguration == null) {
            phoneConfiguration = PhoneConfigurationEntity.getInstance();
        }
        return phoneConfiguration;
    }
    public static WhiteLabelApplication getInstance() {
        return mInstance;
    }

    public void addToRequestQueue(Request request, String tag) {
        HttpClientRequest.getInstance(getApplicationContext()).addToRequestQueue(request, tag);
    }
    public void cancelPendingRequests(Object tag) {
        HttpClientRequest.getInstance(getApplicationContext()).cancelPendingRequests(tag);
    }

    public static ApplicationConfigurationEntity getAppConfiguration() {
        if (appConfiguration == null) {
            appConfiguration = ApplicationConfigurationEntity.getInstance();
        }
        return appConfiguration;
    }
    public static void InitappConfigurationEntity() {
        appConfiguration = null;
    }
    @Override
    public void onCreate() {
        MultiDex.install(this);
        super.onCreate();
        initTwitter();

//        NewRelic.withApplicationToken(
//                "AAd06a20384063c34e4b1ab1ade0f956323ffa6de4"
//        ).start(this);
        LeakCanaryForTest.install(this);
        try {
            mInstance = this;
            GlobalData.init(this);
            FacebookSdk.sdkInitialize(getApplicationContext());
            FacebookSdk.setApplicationId(GlobalData.facebookId);
            com.orhanobut.logger.Logger.addLogAdapter(new AndroidLogAdapter());
            WhiteLabelApplication.getAppConfiguration().isSignIn(getApplicationContext());
            WhiteLabelApplication.getAppConfiguration().init(getApplicationContext());
            ViewTarget.setTagId(R.id.glide_tag);
        } catch (Exception ex) {
            ex.getStackTrace();
        }
        initializeComponents();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());

        LanguageUtils.init(this);
        LanguageUtils.changeLanguage(LanguageUtils.getCurrentLanguage());
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public  GoogleAnalytics getAnalyticInstance() {
        if (analytics == null) {
            analytics = GoogleAnalytics.getInstance(this);

        }
        return analytics;
    }
    public Tracker getAnalyticTracherInstance() {
        if (mTracker == null) {
            getAnalyticInstance();
            mTracker = analytics.newTracker(GlobalData.gaTrackId);
            analytics.getLogger()
                    .setLogLevel(Logger.LogLevel.VERBOSE);
            mTracker.setAppVersion(JToolUtils.getAppVersionName());
            mTracker.enableExceptionReporting(true);
            mTracker.enableAdvertisingIdCollection(true);
            mTracker.enableAutoActivityTracking(false);
        }
        return mTracker;
    }

    private void initTwitter(){
        String twitterConsumerKey = BuildConfig.TWITTER_CONSUMER_KEY;
        String twitterSecret = BuildConfig.TWITTER_CONSUMER_SECRET;
        TwitterAuthConfig authConfig = new TwitterAuthConfig(twitterConsumerKey, twitterSecret);
        Fabric.with(this, new TwitterCore(authConfig),new Crashlytics());
    }
}
