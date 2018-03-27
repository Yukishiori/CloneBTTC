package com.waifusystem.duplicate;

/**
 * Created by Shiori on 3/24/2018.
 */

public class Profile {
    private String name;
    private String description;
    private String itemDes;
    private int profilePicId;
    private int itemPicId;

    static Profile[] profiles = {
            new Profile("Ciel", "the best repliod around ~", "quá lười để viết des",
                    R.drawable.china,
                    R.drawable.circle)
    };

    public Profile(String name, String description, String itemDes, int profilePicId, int itemPicId) {
        this.name = name;
        this.description = description;
        this.itemDes = itemDes;
        this.profilePicId = profilePicId;
        this.itemPicId = itemPicId;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return description;
    }

    public String getItemDes() {
        return itemDes;
    }

    public int getItemPicId() {
        return itemPicId;
    }

    public int getProfilePicId() {
        return profilePicId;
    }
}
