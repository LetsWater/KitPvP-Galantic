package me.flame.galantic.kits.giveKits.kits;

import me.flame.galantic.kits.giveKits.kits.archer.*;
import me.flame.galantic.kits.interfaces.IKitManager;
import me.flame.galantic.sql.SQLUser;
import me.flame.galantic.sql.managers.SQLUserManager;

import java.util.UUID;

public class ArcherManager implements IKitManager {

    private final archerKit_1 ArcherKit_1 = new archerKit_1();
    private final archerKit_2 ArcherKit_2 = new archerKit_2();
    private final archerKit_3 ArcherKit_3 = new archerKit_3();
    private final archerKit_4 ArcherKit_4 = new archerKit_4();
    private final archerKit_5 ArcherKit_5 = new archerKit_5();

    @Override
    public void giveKit(UUID uuid, String kit) {
        for (SQLUser user : SQLUserManager.userList) {
            if (user.getUuid() == uuid) {
                Integer level = user.getArcher_level();
                switch (level) {
                    case 1:
                    default:
                        ArcherKit_1.giveArcherKit_1(uuid);
                        break;
                    case 2:
                        ArcherKit_2.giveArcherKit_2(uuid);
                        break;
                    case 3:
                        ArcherKit_3.giveArcherKit_3(uuid);
                        break;
                    case 4:
                        ArcherKit_4.giveArcherKit_4(uuid);
                        break;
                    case 5:
                        ArcherKit_5.giveArcherKit_5(uuid);
                        break;
                }
            }
        }
    }
}
