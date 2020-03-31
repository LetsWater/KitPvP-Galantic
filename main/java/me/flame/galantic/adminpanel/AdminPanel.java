package me.flame.galantic.adminpanel;

public class AdminPanel {

    private boolean permissions;
    private String lastChangerPermissions;
    private double booster;
    private String lastChangerBooster;
    private double xpBooster;
    private String lastChangerXPBooster;

    public AdminPanel(boolean permissions, String lastChangerPermissions, double booster, String lastChangerBooster, double xpBooster, String lastChangerXPBooster){
        this.permissions = permissions;
        this.lastChangerPermissions = lastChangerPermissions;
        this.booster = booster;
        this.lastChangerBooster = lastChangerBooster;
        this.xpBooster = xpBooster;
        this.lastChangerXPBooster = lastChangerXPBooster;
    }

    public boolean getPermissions() {
        return permissions;
    }

    public void setPermissions(boolean permissions) {
        this.permissions = permissions;
    }

    public String getLastChangerPermissions() {
        return lastChangerPermissions;
    }

    public void setLastChangerPermissions(String lastChangerPermissions) {
        this.lastChangerPermissions = lastChangerPermissions;
    }


    public double getBooster() {
        return booster;
    }

    public void setBooster(double booster) {
        this.booster = booster;
    }

    public String getLastChangerBooster() {
        return lastChangerBooster;
    }

    public void setLastChangerBooster(String lastChangerBooster) {
        this.lastChangerBooster = lastChangerBooster;
    }

    public double getXpBooster() {
        return xpBooster;
    }

    public void setXpBooster(Double xpBooster) {
        this.xpBooster = xpBooster;
    }

    public String getLastChangerXPBooster() {
        return lastChangerXPBooster;
    }

    public void setLastChangerXPBooster(String lastChangerXPBooster) {
        this.lastChangerXPBooster = lastChangerXPBooster;
    }
}
