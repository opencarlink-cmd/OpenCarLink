package com.share.connect.security;

import androidx.room.RoomDatabase;
import androidx.room.Database;
import com.ucarhu.demo.sharelink.auth.PeerEntity;

@Database(entities = {PeerEntity.class}, exportSchema = false, version = 1)
public abstract class PeerDatabase extends RoomDatabase {
    public abstract PeerDao peerDao();
}
