package me.flame.galantic.commands.gui.utils;

import me.flame.galantic.sql.SQLUser;
import me.flame.galantic.sql.managers.SQLUserManager;

import java.util.UUID;

public class CheckPermissions {

    public static boolean CheckPermissions(UUID uuid, String kitname){
        for(SQLUser user : SQLUserManager.userList){
            if(user.getUuid() == uuid){
                switch(kitname.toLowerCase()){
                    case "warrior": default:
                        if(user.getWarrior_level() > 0){
                            return true;
                        }
                        return false;
                    case "archer":
                        if(user.getArcher_level() > 0){
                            return true;
                        }
                        return false;
                    case "tank":
                        if(user.getTank_level() > 0){
                            return true;
                        }
                        return false;
                    case "axe":
                        if(user.getAxe_level() > 0){
                            return true;
                        }
                        return false;
                    case "ninja":
                        if(user.getNinja_level() > 0){
                            return true;
                        }
                        return false;
                    case "hood":
                        if(user.getHood_level() > 0){
                            return true;
                        }
                        return false;
                    case "healer":
                        if(user.getHealer_level() > 0){
                            return true;
                        }
                        return false;
                    case "rogue":
                        if(user.getRogue_level() > 0){
                            return true;
                        }
                        return false;
                    case "knight":
                        if(user.getKnight_level() > 0){
                            return true;
                        }
                        return false;
                    case "assassin":
                        if(user.getAssassin_level() > 0){
                            return true;
                        }
                        return false;

                }
            }
            break;
        }
        return false;
    }
}
