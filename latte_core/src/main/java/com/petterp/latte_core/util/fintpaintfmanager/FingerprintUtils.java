package com.petterp.latte_core.util.fintpaintfmanager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.biometrics.BiometricPrompt;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.petterp.latte_core.app.Latte;

import javax.crypto.Cipher;

/**
 * 指纹识别Utils
 *
 * @author by petterp
 * @date 2019-08-22
 */
public class FingerprintUtils {
    private Activity activity;
    private IfinderPaintf paintf;


    public FingerprintUtils(IfinderPaintf paintf, Activity context) {
        this.paintf = paintf;
        this.activity = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            showFinderPaintfP();
        } else {
            showFinderPaintfJ();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    private void showFinderPaintfP() {
        BiometricPrompt mBiometricPrompt;
        CancellationSignal mCancellationSignal;
        BiometricPrompt.AuthenticationCallback mAuthenticationCallback;
        mBiometricPrompt = new BiometricPrompt.Builder(activity)
                .setTitle("指纹验证")
                .setDescription("请验证你的身份")
                .setNegativeButton("取消", activity.getMainExecutor(), (dialogInterface, i) -> {
                    activity.finish();
                })
                .build();
        mCancellationSignal = new CancellationSignal();
        mAuthenticationCallback = new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                paintf.error();
            }

            @Override
            public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                paintf.success();
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                paintf.failed();
            }
        };
        mBiometricPrompt.authenticate(mCancellationSignal, activity.getMainExecutor(), mAuthenticationCallback);
    }


    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void showFinderPaintfJ() {
        FingerprintManager fingerprintManager = null;
        CancellationSignal mCancellationSignal;
        Cipher cipher = null;
        mCancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(new FingerprintManager.CryptoObject(cipher), mCancellationSignal, 0, new FingerprintManager.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, CharSequence errString) {

            }

            @Override
            public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
            }

            @Override
            public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {

            }

            @Override
            public void onAuthenticationFailed() {

            }
        }, null);
    }


}
