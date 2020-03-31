package me.flame.galantic.kits.giveKits.kits;

import me.flame.galantic.kits.giveKits.kits.hood.*;
import me.flame.galantic.kits.interfaces.IKitManager;
import me.flame.galantic.sql.SQLUser;
import me.flame.galantic.sql.managers.SQLUserManager;

import java.util.UUID;

public class HoodManager implements IKitManager {

    final private hoodKit_1 HoodKit_1 = new hoodKit_1();
    final private hoodKit_2 HoodKit_2 = new hoodKit_2();
    final private hoodKit_3 HoodKit_3 = new hoodKit_3();
    final private hoodKit_4 HoodKit_4 = new hoodKit_4();
    final private hoodKit_5 HoodKit_5 = new hoodKit_5();


    @Override
    public void giveKit(UUID uuid, String kit) {
        for(SQLUser user : SQLUserManager.userList){
            if(user.getUuid() == uuid){
                Integer level = user.getHood_level();
                switch(level){
                    case 1: default:
                        HoodKit_1.giveHoodKit_1(uuid);
                        break;
                    case 2:
                        HoodKit_2.giveHoodKit_2(uuid);
                        break;
                    case 3:
                        HoodKit_3.giveHoodKit_3(uuid);
                        break;
                    case 4:
                        HoodKit_4.giveHoodKit_4(uuid);
                        break;
                    case 5:
                        HoodKit_5.giveHoodKit_5(uuid);
                        break;

                }
            }
        }
    }
}
