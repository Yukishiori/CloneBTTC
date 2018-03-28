package com.waifusystem.duplicate;

/**
 * Created by Shiori on 3/24/2018.
 */

public class Profile {
    private String name;
    private String description;
    private String itemDescription;
    private int profilePicPath;
    private int itemPicPath;
    private int audioPath;

    static Profile[] profiles = {
            new Profile("Ciel", "the best repliod around ~", "quá lười để viết des",
                    R.drawable.china,
                    R.drawable.circle,
                    R.raw.test_audio)
    };

    public Profile(String name, String description, String itemDescription, int profilePicId, int itemPicPath, int audioPath) {
        this.name = name;
        this.description = description;
        this.itemDescription = itemDescription;
        this.profilePicPath = profilePicId;
        this.itemPicPath = itemPicPath;
        this.audioPath = audioPath;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription()   {
        return description;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public int getItemPicPath() {
        return itemPicPath;
    }

    public int getProfilePicPath() {
        return profilePicPath;
    }

    public int getAudioPath() {
        return audioPath;
    }
}
