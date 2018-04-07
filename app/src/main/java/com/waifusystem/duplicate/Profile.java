package com.waifusystem.duplicate;

/**
 * Created by Shiori on 3/24/2018.
 */

public class Profile {
    private String name;
    private String description;
    private int profilePicPath;
    private int audioPath;
    private String tag;
    private int itemImagePath;

    static Profile[] profiles = {
            new Profile("Ching Chong Nguyen",
                    "china man",
                    "Hai người đến từ hai nền văn hoá khác nhau gặp nhau tại một điểm chung của âm nhạc. Họ kể mình đã gặp người kia thế nào, cùng yêu nhau và yêu thể loại nhạc họ theo đuổi, về những chuyện thú vị họ gặp trên hành trình du lịch và lưu diễn ở các nước",
                    R.mipmap.test_char,
                    R.mipmap.illust,
                    R.raw.something),
            new Profile("Bosca ceoil",
                    "korean Man",
                    "- Nếu mà hối hận nhiều nhất thì chắc là, chắc là… việc anh chấp nhận là nghỉ học. Bởi vì anh cảm giác tới thời điểm này ấy, anh thấy việc đi học nó là một việc có giá trị với anh rất là nhiều… nhưng anh đã từ bỏ, mà bây giờ anh mới đòi đi học lại, nên nó đã mất đi một khoảng thời gian 10 năm lãng phí của anh. Và bây giờ anh không thể học lại những cái anh mà đáng ra anh đã có thể học trước đây, mà anh chỉ có thể đi học tiếp những gì cần thiết cho tương lai thôi [...] Còn cái việc anh ngã và anh bị như thế này ấy thì… anh lại thấy không hối tiếc lắm ở chỗ là nó không phải là lựa chọn của anh mà chỉ là một cái xui thôi và gần như nó… gọi là run rủi như thế nào đó nó bị… một cái điều không hay thôi.\n" +
                            "- Anh thèm có 1 người yêu, thèm có 1 người.. Chỉ đơn giản là ngồi nói chuyện với nhau thôi, hoặc là cầm tay gì đấy thôi, chứ cũng không đòi hỏi quá cao. ",
                    R.mipmap.test_char,
                    R.mipmap.illust2,
                    R.raw.something2)
    };

    private Profile(String name, String tag, String description, int profileImagePath, int itemImagePath, int audioPath) {
        this.name = name;
        this.description = description;
        this.profilePicPath = profileImagePath;
        this.audioPath = audioPath;
        this.tag = tag;
        this.itemImagePath = itemImagePath;
    }

    public String getTag() {
        return tag;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return description;
    }

    public int getProfileImagePath() {
        return profilePicPath;
    }

    public int getAudioPath() {
        return audioPath;
    }

    public int getItemImagePath() {
        return itemImagePath;
    }
}
