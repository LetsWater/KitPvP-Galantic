package me.flame.galantic.kits.giveKits.kits;

import me.flame.galantic.kits.giveKits.kits.warrior.*;
import me.flame.galantic.kits.interfaces.IKitManager;
import me.flame.galantic.sql.SQLUser;
import me.flame.galantic.sql.managers.SQLUserManager;

import java.util.UUID;

public class WarriorManager implements IKitManager {

    final private warriorKit_1 WarriorKit_1 = new warriorKit_1();
    final private warriorKit_2 WarriorKit_2 = new warriorKit_2();
    final private warriorKit_3 WarriorKit_3 = new warriorKit_3();
    final private warriorKit_4 WarriorKit_4 = new warriorKit_4();
    final private warriorKit_5 WarriorKit_5 = new warriorKit_5();

    @Override
    public void giveKit(UUID uuid, String kit) {
        for(SQLUser user : SQLUserManager.userList){
            if(user.getUuid() == uuid){
                Integer level = user.getWarrior_level();
                switch(level){
                    case 1 : default:
                        WarriorKit_1.giveWarriorKit_1(uuid);
                        break;
                    case 2:
                        WarriorKit_2.giveWarriorKit_2(uuid);
                        break;
                    case 3:
                        WarriorKit_3.giveWarriorKit_3(uuid);
                        break;
                    case 4:
                        WarriorKit_4.giveWarriorKit_4(uuid);
                        break;
                    case 5:
                        WarriorKit_5.giveWarriorKit_5(uuid);
                        break;
                }
            }
        }
    }
}
