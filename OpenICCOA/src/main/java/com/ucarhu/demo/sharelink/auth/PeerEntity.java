package com.ucarhu.demo.sharelink.auth;

@androidx.room.Entity(tableName = "Peer")
public class PeerEntity {

    @androidx.annotation.NonNull
    @androidx.room.PrimaryKey
    @androidx.room.ColumnInfo(name = "id")
    public java.lang.String f146a;

    @androidx.room.ColumnInfo(name = "auth_key")
    public java.lang.String f147b;

    @androidx.room.ColumnInfo(name = "connection_time")
    public long f148c;
}
