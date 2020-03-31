package me.flame.galantic.kits.giveKits.kits;

import me.flame.galantic.kits.giveKits.kits.axe.*;
import me.flame.galantic.kits.interfaces.IKitManager;
import me.flame.galantic.sql.SQLUser;
import me.flame.galantic.sql.managers.SQLUserManager;

import java.util.UUID;

public class AxeManager implements IKitManager {

    final private axeKit_1 AxeKit_1 = new axeKit_1();
    final private axeKit_2 AxeKit_2 = new axeKit_2();
    final private axeKit_3 AxeKit_3 = new axeKit_3();
    final private axeKit_4 AxeKit_4 = new axeKit_4();
    final private axeKit_5 AxeKit_5 = new axeKit_5();


    @Override
    public void giveKit(UUID uuid, String kit) {
        for (SQLUser user : SQLUserManager.userList) {
            if (user.getUuid() == uuid) {
                Integer level = user.getAxe_level();
                switch (level) {
                    case 1:
                    default:
                        AxeKit_1.giveAxeKit_1(uuid);
                        break;
                    case 2:
                        AxeKit_2.giveAxeKit_2(uuid);
                        break;
                    case 3:
                        AxeKit_3.giveAxeKit_3(uuid);
                        break;
                    case 4:
                        AxeKit_4.giveAxeKit_4(uuid);
                        break;
                    case 5:
                        AxeKit_5.giveAxeKit_5(uuid);
                        break;

                }
            }
        }
    }
}
