package me.flame.galantic.kits.giveKits.kits;

import me.flame.galantic.kits.giveKits.kits.tank.*;
import me.flame.galantic.kits.interfaces.IKitManager;
import me.flame.galantic.sql.SQLUser;
import me.flame.galantic.sql.managers.SQLUserManager;

import java.util.UUID;

public class TankManager implements IKitManager {

    final private tankKit_1 TankKit_1 = new tankKit_1();
    final private tankKit_2 TankKit_2 = new tankKit_2();
    final private tankKit_3 TankKit_3 = new tankKit_3();
    final private tankKit_4 TankKit_4 = new tankKit_4();
    final private tankKit_5 TankKit_5 = new tankKit_5();

    @Override
    public void giveKit(UUID uuid, String kit) {
        for(SQLUser user : SQLUserManager.userList){
            if(user.getUuid() == uuid){
                Integer level = user.getTank_level();
                switch(level){
                    case 1: default:
                        TankKit_1.giveTankKit_1(uuid);
                        break;
                    case 2:
                        TankKit_2.giveTankKit_2(uuid);
                        break;
                    case 3:
                        TankKit_3.giveTankKit_3(uuid);
                        break;
                    case 4:
                        TankKit_4.giveTankKit_3(uuid);
                        break;
                    case 5:
                        TankKit_5.giveTankKit_3(uuid);
                        break;
                }
            }
        }
    }
}
