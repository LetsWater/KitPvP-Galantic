package me.flame.galantic.kits.upgradeKits;

public class Upgrade {

    private String name;
    private double upgradeCost1;
    private double upgradeCost2;
    private double upgradeCost3;
    private double upgradeCost4;
    private double upgradeCost5;

    public Upgrade(String name, double upgradeCost1, double upgradeCost2, double upgradeCost3, double upgradeCost4, double upgradeCost5){
        this.name = name;
        this.upgradeCost1 = upgradeCost1;
        this.upgradeCost2 = upgradeCost2;
        this.upgradeCost3 = upgradeCost3;
        this.upgradeCost4 = upgradeCost4;
        this.upgradeCost5 = upgradeCost5;
    }

    public String getName() {
        return name;
    }

    public double getUpgradeCost1() {
        return upgradeCost1;
    }

    public double getUpgradeCost2() {
        return upgradeCost2;
    }

    public double getUpgradeCost3() {
        return upgradeCost3;
    }

    public double getUpgradeCost4() {
        return upgradeCost4;
    }

    public double getUpgradeCost5() {
        return upgradeCost5;
    }
}
