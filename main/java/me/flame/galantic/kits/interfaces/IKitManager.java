package me.flame.galantic.kits.interfaces;

import java.util.UUID;

public interface IKitManager {

    void giveKit(UUID uuid, String kit);

    void viewKit(UUID uuid, String kit);
}
