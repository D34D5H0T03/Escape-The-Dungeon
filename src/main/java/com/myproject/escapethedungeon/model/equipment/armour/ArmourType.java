package com.myproject.escapethedungeon.model.equipment.armour;

public enum ArmourType {
    CHAINMAIL("Chainmail", 16,
            "A Hauberk made by the armoursmiths of Karinsberg. It has many scars, signifying its age"),
    LEATHER_GARB("Leather Garb", 12,
            "A leather Garb worn by the thieves guild and its associates"),
    SCHOLARS_ROBE("Scholar's Robe", 11,
            "A scholarly robe worn by the Students of Hightower."),
    WARDEN_PLATE("Warden's Plate", 19,
            "A plate mail worn by the Warden, the plate is burned," +
                    " rusted and so seeped in blood and grime that what lays beneath it is a mystery");

    private final String name;
    private final int ac;
    private final String description;

    ArmourType(String name, int ac, String description) {
        this.name = name;
        this.ac = ac;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public int getAc() {
        return ac;
    }

    public String getDescription() {
        return description;
    }
}
