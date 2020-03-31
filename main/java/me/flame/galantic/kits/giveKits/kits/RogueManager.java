package me.flame.galantic.kits.giveKits.kits;

import me.flame.galantic.kits.giveKits.kits.rogue.*;
import me.flame.galantic.kits.interfaces.IKitManager;
import me.flame.galantic.sql.SQLUser;
import me.flame.galantic.sql.managers.SQLUserManager;

import java.util.UUID;

public class RogueManager implements IKitManager {

    final private kitRogue_1 KitRogue_1 = new kitRogue_1();
    final private kitRogue_2 KitRogue_2 = new kitRogue_2();
    final private kitRogue_3 KitRogue_3 = new kitRogue_3();
    final private kitRogue_4 KitRogue_4 = new kitRogue_4();
    final private kitRogue_5 KitRogue_5 = new kitRogue_5();

    @Override
    public void giveKit(UUID uuid, String kit) {
        for(SQLUser user : SQLUserManager.userList){
            if(user.getUuid() == uuid){
                Integer level = user.getRogue_level();
                switch(level){
                    case 1: default:
                        KitRogue_1.giveRogueKit_1(uuid);
                        break;
                    case 2:
                        KitRogue_2.giveRogueKit_2(uuid);
                        break;
                    case 3:
                        KitRogue_3.giveRogueKit_3(uuid);
                        break;
                    case 4:
                        KitRogue_4.giveRogueKit_4(uuid);
                    case 5:
                        KitRogue_5.giveRogueKit_5(uuid);
                        break;

                }
            }
        }
    }
}
