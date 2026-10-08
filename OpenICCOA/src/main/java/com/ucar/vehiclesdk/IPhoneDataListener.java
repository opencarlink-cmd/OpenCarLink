package com.ucar.vehiclesdk;

import com.ucar.vehiclesdk.UCarCommon;

public interface IPhoneDataListener {
    UCarCommon.AudioAttributes getAudioAttributesByType(UCarCommon.AudioType audioType);

    void onMusicInfoReceived(String str, UCarCommon.MusicInfo musicInfo);

    void onNavigationInfoReceived(String str, UCarCommon.NavigationInfo navigationInfo);

    void onPhoneStateInfoReceived(String str, UCarCommon.PhoneStateInfo phoneStateInfo);

    void onRequestAudioFocus(UCarCommon.AudioType audioType, int i);
}
