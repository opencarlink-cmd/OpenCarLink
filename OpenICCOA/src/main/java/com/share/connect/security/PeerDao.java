package com.share.connect.security;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Insert;
import com.ucarhu.demo.sharelink.auth.PeerEntity;

@Dao
public interface PeerDao {
    @Query("DELETE FROM peer WHERE id=:str")
    void delete(String str);

    @Query("SELECT * FROM peer WHERE id=:str")
    PeerEntity get(String str);

    @Query("SELECT * FROM peer ORDER BY connection_time DESC LIMIT 1")
    PeerEntity getLast();

    @Insert(onConflict = 1)
    long insert(PeerEntity c0019c);

    @Query("UPDATE peer SET connection_time= CASE WHEN id=:str THEN 1 ELSE 0 END")
    int updateLast(String str);
}
