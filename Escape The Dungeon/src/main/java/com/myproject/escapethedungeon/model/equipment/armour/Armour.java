package com.myproject.escapethedungeon.model.equipment.armour;

public class Armour {
    private final ArmourType type;
    private final String name;
    private final String description;
    private final int armourClass;


    public Armour(ArmourType type){
        this.name = type.getName();
        this.description = type.getDescription();
        this.armourClass = type.getAc();
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getArmourClass() {
        return armourClass;
    }

    public ArmourType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Armour-\n" +
                "Name: " + name + "\n" +
                "\nArmour Class: " + armourClass;
    }
}
