package kr.co.codesnippet;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

//import com.github.mcmrarm.playdl.App;
//import com.github.mcmrarm.playdl.DeviceInfo;
//import com.github.mcmrarm.playdl.LoginManager;

import kr.co.codesnippet.util.third.mcmrarm.playdl.App;
import kr.co.codesnippet.util.third.mcmrarm.playdl.DeviceInfo;
import kr.co.codesnippet.util.third.mcmrarm.playdl.LoginManager;

public class DownAndInstallActivity extends AppCompatActivity {
    AccountManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_and_install);
        chkAccountPermission();
    }

    private void chkAccountPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.GET_ACCOUNTS},123);
            } else {
                resumeActivity();
            }
        } else {
            resumeActivity();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        resumeActivity();
    }

    private void resumeActivity() {
        AccountManager manager = (AccountManager) getSystemService(ACCOUNT_SERVICE);
        Account[] list = manager.getAccounts();
        String str_account = "";
        for(Account account : list) {
            if(account.name.contains("@")) {
                str_account = account.name;
                break;
            }
        }

        tryDownloadApk(str_account);
    }

    private void tryDownloadApk(String account) {
        String email = account;
        String pass = "rotlqkf1!";
        DeviceInfo.lang = "kr";
        DeviceInfo.androidId = Build.SERIAL;
        DeviceInfo.isX86 = true;

        try {
            LoginManager lmgr = new LoginManager("androidmarket", "com.android.vending");
            lmgr.setEmail(email);
            lmgr.login(pass, DeviceInfo.androidId);

//            String pkg = "com.twitter.android";
//            App app = new App(lmgr, pkg);
//            app.download(app.deliver(), pkg + app.versionCode + ".apk");
        } catch(Exception e) {
            Log.e("error", e.toString());
        }

    }
}
