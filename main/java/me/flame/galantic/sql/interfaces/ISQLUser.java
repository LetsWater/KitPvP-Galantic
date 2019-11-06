package me.flame.galantic.sql.interfaces;

import java.util.UUID;

public interface ISQLUser {

    void registerUser(UUID uuid);

    void loadUser(UUID uuid);

    void saveUser(UUID uuid);


}
