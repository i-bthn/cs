package com.pushwoosh.plugin.geolocation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.work.WorkRequest;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.pushwoosh.internal.event.EventBus;
import com.pushwoosh.internal.event.EventListener;
import com.pushwoosh.internal.event.PermissionEvent;
import com.pushwoosh.internal.utils.PermissionActivity;
import com.pushwoosh.plugin.geolocation.LocationSettingsResolutionActivity;

public class LocationManager {
    public static final String[] LOCATION_PERMISSIONS = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};

    private class ResolutionEventListener implements EventListener<LocationSettingsResolutionActivity.ResolutionEvent> {
        private Callback<Boolean> callback;

        public ResolutionEventListener(Callback<Boolean> callback2) {
            this.callback = callback2;
            EventBus.subscribe(LocationSettingsResolutionActivity.ResolutionEvent.class, this);
        }

        public void onReceive(LocationSettingsResolutionActivity.ResolutionEvent resolutionEvent) {
            this.callback.call(Boolean.valueOf(resolutionEvent.success), null);
            EventBus.unsubscribe(LocationSettingsResolutionActivity.ResolutionEvent.class, this);
        }
    }

    /* access modifiers changed from: private */
    public class PermissionsEventListener implements EventListener<PermissionEvent> {
        private Callback<Boolean> callback;

        public PermissionsEventListener(Callback<Boolean> callback2) {
            this.callback = callback2;
            EventBus.subscribe(PermissionEvent.class, this);
        }

        public void onReceive(PermissionEvent permissionEvent) {
            this.callback.call(Boolean.valueOf(permissionEvent.getGrantedPermissions().size() > 0), null);
            EventBus.unsubscribe(PermissionEvent.class, this);
        }
    }

    /* access modifiers changed from: protected */
    public LocationRequest createLocationRequest() {
        LocationRequest create = LocationRequest.create();
        create.setNumUpdates(1);
        create.setInterval(WorkRequest.MIN_BACKOFF_MILLIS);
        create.setMaxWaitTime(WorkRequest.MIN_BACKOFF_MILLIS);
        create.setExpirationDuration(WorkRequest.MIN_BACKOFF_MILLIS);
        create.setPriority(100);
        return create;
    }

    public void continueWithPermissionGranted(final Context context, final Callback<Boolean> callback) {
        Task<LocationSettingsResponse> checkLocationSettings = LocationServices.getSettingsClient(context).checkLocationSettings(new LocationSettingsRequest.Builder().addLocationRequest(createLocationRequest()).build());
        checkLocationSettings.addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
            /* class com.pushwoosh.plugin.geolocation.LocationManager.AnonymousClass1 */

            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
                callback.call(true, null);
            }
        });
        checkLocationSettings.addOnFailureListener(new OnFailureListener() {
            /* class com.pushwoosh.plugin.geolocation.LocationManager.AnonymousClass2 */

            @Override // com.google.android.gms.tasks.OnFailureListener
            public void onFailure(@NonNull Exception exc) {
                if (exc instanceof ResolvableApiException) {
                    LocationSettingsResolutionActivity.resolutionSettingApi(context, (ResolvableApiException) exc);
                    new ResolutionEventListener(callback);
                    return;
                }
                callback.call(false, exc);
            }
        });
    }

    public void requestPermissions(final Context context, final Callback<Boolean> callback) {
        boolean z = false;
        for (String str : LOCATION_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(context, str) == 0) {
                z = true;
            }
        }
        if (z) {
            callback.call(true, null);
            return;
        }
        PermissionActivity.requestPermissions(context, LOCATION_PERMISSIONS);
        new PermissionsEventListener(new Callback<Boolean>() {
            /* class com.pushwoosh.plugin.geolocation.LocationManager.AnonymousClass3 */

            public void call(Boolean bool, Exception exc) {
                if (bool.booleanValue()) {
                    LocationManager.this.continueWithPermissionGranted(context, callback);
                } else {
                    callback.call(false, null);
                }
            }
        });
    }

    @SuppressLint({"MissingPermission"})
    public void requestLocation(final Context context, final Callback<Location> callback) {
        requestPermissions(context, new Callback<Boolean>() {
            /* class com.pushwoosh.plugin.geolocation.LocationManager.AnonymousClass4 */

            public void call(Boolean bool, Exception exc) {
                if (bool == null || !bool.booleanValue()) {
                    callback.call(null, exc);
                } else {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        /* class com.pushwoosh.plugin.geolocation.LocationManager.AnonymousClass4.AnonymousClass1 */

                        public void run() {
                            final FusedLocationProviderClient fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
                            fusedLocationProviderClient.requestLocationUpdates(LocationManager.this.createLocationRequest(), new LocationCallback() {
                                /* class com.pushwoosh.plugin.geolocation.LocationManager.AnonymousClass4.AnonymousClass1.AnonymousClass1 */

                                @Override // com.google.android.gms.location.LocationCallback
                                public void onLocationResult(LocationResult locationResult) {
                                    if (locationResult == null) {
                                        callback.call(null, null);
                                        return;
                                    }
                                    callback.call(locationResult.getLastLocation(), null);
                                    fusedLocationProviderClient.removeLocationUpdates(this);
                                }
                            }, null);
                        }
                    });
                }
            }
        });
    }
}
