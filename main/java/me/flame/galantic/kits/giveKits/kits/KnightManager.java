package me.flame.galantic.kits.giveKits.kits;

import me.flame.galantic.kits.giveKits.kits.knight.*;
import me.flame.galantic.kits.interfaces.IKitManager;
import me.flame.galantic.sql.SQLUser;
import me.flame.galantic.sql.managers.SQLUserManager;

import java.util.UUID;

public class KnightManager implements IKitManager {

    private final knightKit_1 KnightKit_1 = new knightKit_1();
    private final knightKit_2 KnightKit_2 = new knightKit_2();
    private final knightKit_3 KnightKit_3 = new knightKit_3();
    private final knightKit_4 KnightKit_4 = new knightKit_4();
    private final knightKit_5 KnightKit_5 = new knightKit_5();

    @Override
    public void giveKit(UUID uuid, String kit) {
        for (SQLUser user : SQLUserManager.userList) {
            if (user.getUuid() == uuid) {
                Integer level = user.getKnight_level();
                switch (level) {
                    case 1:
                    default:
                        KnightKit_1.giveKnightKit_1(uuid);
                        break;
                    case 2:
                        KnightKit_2.giveKnightKit_2(uuid);
                        break;
                    case 3:
                        KnightKit_3.giveKnightKit_3(uuid);
                        break;
                    case 4:
                        KnightKit_4.giveKnightKit_4(uuid);
                        break;
                    case 5:
                        KnightKit_5.giveKnightKit_5(uuid);
                        break;

                }
            }
        }
    }
}
