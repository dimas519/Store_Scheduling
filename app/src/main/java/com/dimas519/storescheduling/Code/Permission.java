package com.dimas519.storescheduling.Code;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import java.util.ArrayList;


public class Permission {
    //diperlukan karena android 11 hanya akan prompt 2x (2x nolak)
    private final ArrayList<String> deniedByUser=new ArrayList<>();

    private final String[] allPermission={
            Manifest.permission.READ_EXTERNAL_STORAGE
    };

    public void checkPermission(Activity activity,int requestcode){
            activity.requestPermissions(this.allPermission,requestcode);
    }

    public boolean isAllEnabled(Context context) {
        for( String permission :  this.allPermission){
            int res = context.checkCallingOrSelfPermission(permission);
            if(res==PackageManager.PERMISSION_DENIED){
                return false;
            }
        }
        return true;

    }

    public boolean deniedTwice(String permission){
        if(this.deniedByUser.contains(permission)){
            return true;
        }else{
            this.deniedByUser.add(permission);
            return false;
        }
    }




}
