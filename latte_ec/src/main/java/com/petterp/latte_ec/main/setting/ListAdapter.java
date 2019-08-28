package com.petterp.latte_ec.main.setting;

import android.app.KeyguardManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.widget.Toast;

import androidx.appcompat.widget.SwitchCompat;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.petterp.latte_core.app.Latte;
import com.petterp.latte_core.util.storage.LatterPreference;
import com.petterp.latte_ec.R;

import java.util.List;

/**
 * @author by petterp
 * @date 2019-08-22
 */
public class ListAdapter extends BaseMultiItemQuickAdapter<ListBean, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ListAdapter(List<ListBean> data) {
        super(data);
        addItemType(ListItemType.ITEM_SWITCH, R.layout.arrows_witch_layout);
    }

    @Override
    protected void convert(BaseViewHolder helper, ListBean item) {
        if (item.getItemType() == ListItemType.ITEM_SWITCH) {
            helper.setText(R.id.tv_arrow_switch_text, item.getmText());
            helper.setText(R.id.ic_icon_kind, item.getIcon());
            SwitchCompat switchCompat = helper.getView(R.id.list_item_switch);
            switchCompat.setChecked(LatterPreference.getFinderPaintf());
            switchCompat.setOnClickListener(view -> {
                boolean mode=LatterPreference.getFinderPaintf();
                if (mode) {
                    LatterPreference.setFinderPaintf(false);
                } else {
                    if (supportFingerprint()) {
                        LatterPreference.setFinderPaintf(true);
                    } else {
                        switchCompat.setChecked(false);
                    }
                }
            });
        }

    }

    public boolean supportFingerprint() {
        if (Build.VERSION.SDK_INT < 23) {
            Toast.makeText(Latte.getContext(), "您的系统版本过低，不支持指纹功能", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            KeyguardManager keyguardManager = Latte.getContext().getSystemService(KeyguardManager.class);
            FingerprintManager fingerprintManager = Latte.getContext().getSystemService(FingerprintManager.class);
            if (!fingerprintManager.isHardwareDetected()) {
                Toast.makeText(Latte.getContext(), "您的手机不支持指纹功能", Toast.LENGTH_SHORT).show();
                return false;
            } else if (!keyguardManager.isKeyguardSecure()) {
                Toast.makeText(Latte.getContext(), "您还未设置锁屏，请先设置锁屏并添加一个指纹", Toast.LENGTH_SHORT).show();
                return false;
            } else if (!fingerprintManager.hasEnrolledFingerprints()) {
                Toast.makeText(Latte.getContext(), "您至少需要在系统设置中添加一个指纹", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
}
