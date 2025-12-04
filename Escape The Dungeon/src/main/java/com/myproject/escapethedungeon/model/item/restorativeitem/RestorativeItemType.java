package com.myproject.escapethedungeon.model.item.restorativeitem;

public enum RestorativeItemType {
    HEALING_POTION("Healing Potion", "A small vial of arcane medicine. Restores 20 HP",
            20,
            0),
    MAGIC_POTION("Magic Potion", "A small vial of arcane medicine. Restores 15 MP",
            0,
            15);
    //ELIXIR_OF_VITALITY("Elixir of Vitality", "Restores 30 HP and 20 MP", 30, 20);

    private final String name;
    private final String description;
    private final int hpRestore;
    private final int mpRestore;

    RestorativeItemType(String name, String description, int hpRestore, int mpRestore) {
        this.name = name;
        this.description = description;
        this.hpRestore = hpRestore;
        this.mpRestore = mpRestore;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getHpRestore() {
        return hpRestore;
    }

    public int getMpRestore() {
        return mpRestore;
    }
}
