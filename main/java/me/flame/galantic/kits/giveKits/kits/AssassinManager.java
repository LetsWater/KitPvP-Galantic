package me.flame.galantic.kits.giveKits.kits;

import me.flame.galantic.kits.giveKits.kits.assassin.*;
import me.flame.galantic.kits.interfaces.IKitManager;
import me.flame.galantic.sql.SQLUser;
import me.flame.galantic.sql.managers.SQLUserManager;

import java.util.UUID;

public class AssassinManager implements IKitManager {

    private final assassinKit_1 AssassinKit_1 = new assassinKit_1();
    private final assassinKit_2 AssassinKit_2 = new assassinKit_2();
    private final assassinKit_3 AssassinKit_3 = new assassinKit_3();
    private final assassinKit_4 AssassinKit_4 = new assassinKit_4();
    private final assassinKit_5 AssassinKit_5 = new assassinKit_5();


    @Override
    public void giveKit(UUID uuid, String kit) {
        for(SQLUser user : SQLUserManager.userList){
            if(user.getUuid() == uuid){
                Integer level = user.getAssassin_level();
                switch(level){
                    case 1: default:
                        AssassinKit_1.giveAssassinKit_1(uuid);
                        break;
                    case 2:
                        AssassinKit_2.giveAssassinKit_2(uuid);
                        break;
                    case 3:
                        AssassinKit_3.giveAssassinKit_3(uuid);
                        break;
                    case 4:
                        AssassinKit_4.giveAssassinKit_4(uuid);
                        break;
                    case 5:
                        AssassinKit_5.giveAssassinKit_5(uuid);
                        break;
                }
            }
        }
    }
}
