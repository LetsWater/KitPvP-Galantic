package me.flame.galantic.kits.giveKits.kits;

import me.flame.galantic.kits.giveKits.kits.healer.*;
import me.flame.galantic.kits.interfaces.IKitManager;
import me.flame.galantic.sql.SQLUser;
import me.flame.galantic.sql.managers.SQLUserManager;

import java.util.UUID;

public class HealerManager implements IKitManager {

    final private healerKit_1 HealerKit_1 = new healerKit_1();
    final private healerKit_2 HealerKit_2 = new healerKit_2();
    final private healerKit_3 HealerKit_3 = new healerKit_3();
    final private healerKit_4 HealerKit_4 = new healerKit_4();
    final private healerKit_5 HealerKit_5 = new healerKit_5();

    @Override
    public void giveKit(UUID uuid, String kit) {
        for(SQLUser user : SQLUserManager.userList){
            if(user.getUuid() == uuid){
                Integer level = user.getHealer_level();
                switch(level){
                    case 1: default:
                        HealerKit_1.giveHealerKit_1(uuid);
                        break;
                    case 2:
                        HealerKit_2.giveHealerKit_2(uuid);
                        break;
                    case 3:
                        HealerKit_3.giveHealerKit_3(uuid);
                        break;
                    case 4:
                        HealerKit_4.giveHealerKit_4(uuid);
                        break;
                    case 5:
                        HealerKit_5.giveHealerKit_5(uuid);
                        break;
                }
            }
        }
    }
}
