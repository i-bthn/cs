package com.google.android.gms.measurement.internal;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.work.WorkRequest;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.measurement.zzca;
import com.google.android.gms.internal.measurement.zzcn;
import com.google.android.gms.internal.measurement.zzct;
import com.google.android.gms.internal.measurement.zzjn;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@VisibleForTesting
public final class zzak {
    static zzr zzfv;
    private static List<zzdu<?>> zzfw = Collections.synchronizedList(new ArrayList());
    private static Set<zzdu<?>> zzfx = Collections.synchronizedSet(new HashSet());
    private static final zzct zzfy = new zzct(zzcn.zzdh("com.google.android.gms.measurement"));
    @VisibleForTesting
    private static Boolean zzfz;
    private static zzdu<Boolean> zzga = zza("measurement.log_third_party_store_events_enabled", false, false, zzan.zzji);
    private static zzdu<Boolean> zzgb = zza("measurement.log_installs_enabled", false, false, zzam.zzji);
    private static zzdu<Boolean> zzgc = zza("measurement.log_upgrades_enabled", false, false, zzaz.zzji);
    public static zzdu<Boolean> zzgd = zza("measurement.log_androidId_enabled", false, false, zzbi.zzji);
    public static zzdu<Boolean> zzge = zza("measurement.upload_dsid_enabled", false, false, zzbv.zzji);
    public static zzdu<String> zzgf = zza("measurement.log_tag", "FA", "FA-SVC", zzce.zzji);
    public static zzdu<Long> zzgg;
    public static zzdu<Long> zzgh = zza("measurement.monitoring.sample_period_millis", 86400000L, 86400000L, zzda.zzji);
    public static zzdu<Long> zzgi = zza("measurement.config.cache_time", 86400000L, 3600000L, zzdn.zzji);
    public static zzdu<String> zzgj = zza("measurement.config.url_scheme", "https", "https", zzdt.zzji);
    public static zzdu<String> zzgk = zza("measurement.config.url_authority", "app-measurement.com", "app-measurement.com", zzap.zzji);
    public static zzdu<Integer> zzgl = zza("measurement.upload.max_bundles", 100, 100, zzao.zzji);
    public static zzdu<Integer> zzgm = zza("measurement.upload.max_batch_size", 65536, 65536, zzar.zzji);
    public static zzdu<Integer> zzgn = zza("measurement.upload.max_bundle_size", 65536, 65536, zzaq.zzji);
    public static zzdu<Integer> zzgo = zza("measurement.upload.max_events_per_bundle", 1000, 1000, zzat.zzji);
    public static zzdu<Integer> zzgp = zza("measurement.upload.max_events_per_day", 100000, 100000, zzas.zzji);
    public static zzdu<Integer> zzgq = zza("measurement.upload.max_error_events_per_day", 1000, 1000, zzav.zzji);
    public static zzdu<Integer> zzgr = zza("measurement.upload.max_public_events_per_day", 50000, 50000, zzau.zzji);
    public static zzdu<Integer> zzgs = zza("measurement.upload.max_conversions_per_day", 500, 500, zzax.zzji);
    public static zzdu<Integer> zzgt = zza("measurement.upload.max_realtime_events_per_day", 10, 10, zzaw.zzji);
    public static zzdu<Integer> zzgu = zza("measurement.store.max_stored_events_per_app", 100000, 100000, zzay.zzji);
    public static zzdu<String> zzgv = zza("measurement.upload.url", "https://app-measurement.com/a", "https://app-measurement.com/a", zzbb.zzji);
    public static zzdu<Long> zzgw = zza("measurement.upload.backoff_period", 43200000L, 43200000L, zzba.zzji);
    public static zzdu<Long> zzgx = zza("measurement.upload.window_interval", 3600000L, 3600000L, zzbd.zzji);
    public static zzdu<Long> zzgy = zza("measurement.upload.interval", 3600000L, 3600000L, zzbc.zzji);
    public static zzdu<Long> zzgz;
    public static zzdu<Long> zzha = zza("measurement.upload.debug_upload_interval", 1000L, 1000L, zzbe.zzji);
    public static zzdu<Long> zzhb = zza("measurement.upload.minimum_delay", 500L, 500L, zzbh.zzji);
    public static zzdu<Long> zzhc = zza("measurement.alarm_manager.minimum_interval", 60000L, 60000L, zzbg.zzji);
    public static zzdu<Long> zzhd = zza("measurement.upload.stale_data_deletion_interval", 86400000L, 86400000L, zzbj.zzji);
    public static zzdu<Long> zzhe = zza("measurement.upload.refresh_blacklisted_config_interval", 604800000L, 604800000L, zzbl.zzji);
    public static zzdu<Long> zzhf = zza("measurement.upload.initial_upload_delay_time", 15000L, 15000L, zzbk.zzji);
    public static zzdu<Long> zzhg = zza("measurement.upload.retry_time", 1800000L, 1800000L, zzbn.zzji);
    public static zzdu<Integer> zzhh = zza("measurement.upload.retry_count", 6, 6, zzbm.zzji);
    public static zzdu<Long> zzhi = zza("measurement.upload.max_queue_time", 2419200000L, 2419200000L, zzbp.zzji);
    public static zzdu<Integer> zzhj = zza("measurement.lifetimevalue.max_currency_tracked", 4, 4, zzbo.zzji);
    public static zzdu<Integer> zzhk = zza("measurement.audience.filter_result_max_count", 200, 200, zzbr.zzji);
    public static zzdu<Long> zzhl = zza("measurement.service_client.idle_disconnect_millis", 5000L, 5000L, zzbq.zzji);
    public static zzdu<Boolean> zzhm = zza("measurement.test.boolean_flag", false, false, zzbt.zzji);
    public static zzdu<String> zzhn = zza("measurement.test.string_flag", "---", "---", zzbs.zzji);
    public static zzdu<Long> zzho = zza("measurement.test.long_flag", -1L, -1L, zzbu.zzji);
    public static zzdu<Integer> zzhp = zza("measurement.test.int_flag", -2, -2, zzbx.zzji);
    public static zzdu<Double> zzhq;
    public static zzdu<Integer> zzhr = zza("measurement.experiment.max_ids", 50, 50, zzbz.zzji);
    public static zzdu<Boolean> zzhs = zza("measurement.validation.internal_limits_internal_event_params", false, false, zzby.zzji);
    public static zzdu<Boolean> zzht = zza("measurement.audience.dynamic_filters", true, true, zzcb.zzji);
    public static zzdu<Boolean> zzhu = zza("measurement.reset_analytics.persist_time", false, false, zzca.zzji);
    public static zzdu<Boolean> zzhv = zza("measurement.validation.value_and_currency_params", true, true, zzcd.zzji);
    public static zzdu<Boolean> zzhw = zza("measurement.sampling.time_zone_offset_enabled", false, false, zzcc.zzji);
    public static zzdu<Boolean> zzhx = zza("measurement.referrer.enable_logging_install_referrer_cmp_from_apk", false, false, zzcf.zzji);
    public static zzdu<Boolean> zzhy = zza("measurement.fetch_config_with_admob_app_id", true, true, zzch.zzji);
    public static zzdu<Boolean> zzhz = zza("measurement.client.sessions.session_id_enabled", false, false, zzcg.zzji);
    public static zzdu<Boolean> zzia = zza("measurement.service.sessions.session_number_enabled", false, false, zzcj.zzji);
    public static zzdu<Boolean> zzib = zza("measurement.client.sessions.immediate_start_enabled_foreground", false, false, zzci.zzji);
    public static zzdu<Boolean> zzic = zza("measurement.client.sessions.background_sessions_enabled", false, false, zzcl.zzji);
    public static zzdu<Boolean> zzid = zza("measurement.client.sessions.remove_expired_session_properties_enabled", false, false, zzck.zzji);
    public static zzdu<Boolean> zzie = zza("measurement.service.sessions.session_number_backfill_enabled", false, false, zzcn.zzji);
    public static zzdu<Boolean> zzif = zza("measurement.service.sessions.remove_disabled_session_number", false, false, zzcm.zzji);
    public static zzdu<Boolean> zzig = zza("measurement.collection.firebase_global_collection_flag_enabled", true, true, zzcp.zzji);
    public static zzdu<Boolean> zzih = zza("measurement.collection.efficient_engagement_reporting_enabled", false, false, zzco.zzji);
    public static zzdu<Boolean> zzii = zza("measurement.collection.redundant_engagement_removal_enabled", false, false, zzcq.zzji);
    public static zzdu<Boolean> zzij = zza("measurement.personalized_ads_signals_collection_enabled", true, true, zzct.zzji);
    public static zzdu<Boolean> zzik = zza("measurement.personalized_ads_property_translation_enabled", true, true, zzcs.zzji);
    public static zzdu<Boolean> zzil = zza("measurement.collection.init_params_control_enabled", true, true, zzcv.zzji);
    public static zzdu<Boolean> zzim = zza("measurement.upload.disable_is_uploader", true, true, zzcu.zzji);
    public static zzdu<Boolean> zzin = zza("measurement.experiment.enable_experiment_reporting", true, true, zzcx.zzji);
    public static zzdu<Boolean> zzio = zza("measurement.collection.log_event_and_bundle_v2", true, true, zzcw.zzji);
    public static zzdu<Boolean> zzip = zza("measurement.collection.null_empty_event_name_fix", true, true, zzcz.zzji);
    public static zzdu<Boolean> zziq = zza("measurement.audience.sequence_filters", false, false, zzcy.zzji);
    private static zzdu<Boolean> zzir = zza("measurement.audience.sequence_filters_bundle_timestamp", false, false, zzdb.zzji);
    public static zzdu<Boolean> zzis = zza("measurement.quality.checksum", false, false, null);
    public static zzdu<Boolean> zzit = zza("measurement.module.collection.conditionally_omit_admob_app_id", true, true, zzdd.zzji);
    public static zzdu<Boolean> zziu = zza("measurement.sdk.dynamite.use_dynamite2", false, false, zzdc.zzji);
    public static zzdu<Boolean> zziv = zza("measurement.sdk.dynamite.allow_remote_dynamite", false, false, zzdf.zzji);
    public static zzdu<Boolean> zziw = zza("measurement.sdk.collection.validate_param_names_alphabetical", false, false, zzde.zzji);
    public static zzdu<Boolean> zzix = zza("measurement.collection.event_safelist", false, false, zzdh.zzji);
    public static zzdu<Boolean> zziy = zza("measurement.service.audience.scoped_filters_v27", false, false, zzdg.zzji);
    public static zzdu<Boolean> zziz = zza("measurement.service.audience.session_scoped_event_aggregates", false, false, zzdj.zzji);
    private static volatile zzfj zzj;
    public static zzdu<Boolean> zzja = zza("measurement.service.audience.session_scoped_user_engagement", false, false, zzdi.zzji);
    public static zzdu<Boolean> zzjb = zza("measurement.service.audience.remove_disabled_session_scoped_user_engagement", false, false, zzdl.zzji);
    public static zzdu<Boolean> zzjc = zza("measurement.sdk.collection.retrieve_deeplink_from_bow", false, false, zzdk.zzji);
    public static zzdu<Boolean> zzjd = zza("measurement.app_launch.event_ordering_fix", false, false, zzdm.zzji);
    public static zzdu<Boolean> zzje = zza("measurement.sdk.collection.last_deep_link_referrer", false, false, zzdp.zzji);
    private static zzdu<Boolean> zzjf = zza("measurement.sdk.collection.last_deep_link_referrer_campaign", false, false, zzdo.zzji);
    public static zzdu<Boolean> zzjg = zza("measurement.sdk.collection.last_gclid_from_referrer", false, false, zzdr.zzji);
    public static zzdu<Boolean> zzjh = zza("measurement.upload.file_lock_state_check", false, false, zzdq.zzji);

    public static Map<String, String> zzk(Context context) {
        zzca zza = zzca.zza(context.getContentResolver(), zzcn.zzdh("com.google.android.gms.measurement"));
        return zza == null ? Collections.emptyMap() : zza.zzre();
    }

    static void zza(zzfj zzfj) {
        zzj = zzfj;
    }

    @VisibleForTesting
    static void zza(Exception exc) {
        if (zzj != null) {
            Context context = zzj.getContext();
            if (zzfz == null) {
                zzfz = Boolean.valueOf(GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE) == 0);
            }
            if (zzfz.booleanValue()) {
                zzj.zzab().zzgk().zza("Got Exception on PhenotypeFlag.get on Play device", exc);
            }
        }
    }

    @VisibleForTesting
    private static <V> zzdu<V> zza(@Nullable String str, @Nullable V v, @Nullable V v2, @Nullable zzdv<V> zzdv) {
        zzdu<V> zzdu = new zzdu<>(str, v, v2, zzdv);
        zzfw.add(zzdu);
        return zzdu;
    }

    static void zza(zzr zzr) {
        zzfv = zzr;
    }

    private static boolean isPackageSide() {
        if (zzfv != null) {
        }
        return false;
    }

    static final /* synthetic */ Long zzfu() {
        long j;
        if (isPackageSide()) {
            j = zzjn.zzyc();
        } else {
            j = zzjn.zzxo();
        }
        return Long.valueOf(j);
    }

    static final /* synthetic */ String zzfx() {
        return isPackageSide() ? zzjn.zzye() : zzjn.zzxp();
    }

    static {
        Long valueOf = Long.valueOf((long) WorkRequest.MIN_BACKOFF_MILLIS);
        zzgg = zza("measurement.ad_id_cache_time", valueOf, valueOf, zzcr.zzji);
        Long valueOf2 = Long.valueOf((long) WorkRequest.MIN_BACKOFF_MILLIS);
        zzgz = zza("measurement.upload.realtime_upload_interval", valueOf2, valueOf2, zzbf.zzji);
        Double valueOf3 = Double.valueOf(-3.0d);
        zzhq = zza("measurement.test.double_flag", valueOf3, valueOf3, zzbw.zzji);
    }
}
