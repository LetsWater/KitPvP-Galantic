package me.flame.galantic.kits.giveKits.kits;

import me.flame.galantic.kits.giveKits.kits.ninja.*;
import me.flame.galantic.kits.interfaces.IKitManager;
import me.flame.galantic.sql.SQLUser;
import me.flame.galantic.sql.managers.SQLUserManager;

import java.util.UUID;

public class NinjaManager implements IKitManager {

    final private ninjaKit_1 NinjaKit_1 = new ninjaKit_1();
    final private ninjaKit_2 NinjaKit_2 = new ninjaKit_2();
    final private ninjaKit_3 NinjaKit_3 = new ninjaKit_3();
    final private ninjaKit_4 NinjaKit_4 = new ninjaKit_4();
    final private ninjaKit_5 NinjaKit_5 = new ninjaKit_5();


    @Override
    public void giveKit(UUID uuid, String kit) {
        for (SQLUser user : SQLUserManager.userList) {
            if (user.getUuid() == uuid) {
                Integer level = user.getNinja_level();
                switch (level) {
                    case 1:
                    default:
                        NinjaKit_1.giveNinjaKit_1(uuid);
                        break;
                    case 2:
                        NinjaKit_2.giveNinjaKit_2(uuid);
                        break;
                    case 3:
                        NinjaKit_3.giveNinjaKit_3(uuid);
                        break;
                    case 4:
                        NinjaKit_4.giveNinjaKit_4(uuid);
                        break;
                    case 5:
                        NinjaKit_5.giveNinjaKit_5(uuid);
                        break;

                }
            }
        }
    }
}
