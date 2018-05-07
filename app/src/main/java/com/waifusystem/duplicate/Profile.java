package com.waifusystem.duplicate;

/**
 * Created by Shiori on 3/24/2018.
 */

public class Profile {
    private String name;
    private String description;
    private int profilePicPath;
    private int audioPath;
    private int itemImagePath;
    private String itemDescription;

    static Profile[] profiles = {
            // the question part

            new Profile("Bảo Tàng Thấu Cảm",
                    "",
                    "\"Quét mã QR được tìm thấy cạnh đồ vật được hiển thị của nhân vật để bắt đầu câu chuyện.\"",
                    R.drawable.lancao,
                    R.drawable.question_mark,
                    R.raw.lan_cao),
            //1
            new Profile("Lan Cao & Gregor Siedl",
                    "After years of living overseas, when i came back to Hanoi, gradually i see Hanoi with different eyes. I would say in the past, i wouldn't treasure all those dodgy colors of Hanoi, but now i find beauty in many corners which i would ignore before.",
                    "After years of living overseas, when i came back to Hanoi, gradually i see Hanoi with different eyes.",
                    R.drawable.lancao,
                    R.drawable.lancao_item,
                    R.raw.lan_cao),
            //2
            new Profile("Khang A Tủa",
                    "\"Cứ mỗi lần về, Mù Cang Chải với mình như không còn là quê hương mình, ko còn là những gì mình biết về nó nữa, mỗi lần về là thấy khác như hàng thế kỉ đã trôi qua...\"",
                    "\"Cứ mỗi lần về, Mù Cang Chải với mình như không còn là quê hương mình, ko còn là những gì mình biết về nó nữa,...\"",
                    R.drawable.tua,
                    R.drawable.tua_item,
                    R.raw.a_tua),
            //3
            new Profile("Sóng",
                    "Anh thèm có 1 người yêu, thèm có 1 người.. Chỉ đơn giản là ngồi nói chuyện với nhau thôi, hoặc là cầm tay gì đấy thôi, chứ cũng không đòi hỏi quá cao. ",
                    "Anh thèm có 1 người yêu, thèm có 1 người..",
                    R.drawable.song,
                    R.drawable.song_item,
                    R.raw.a_song),
            //4
            new Profile("Đại",
                    "\"Khi mình cùng người khuyết tật vào cửa hàng, họ bảo \"Lại vào đây xin tiền hả?\". Mình thấy sốc và nhục, nhưng người khuyết tật chỉ bảo \"Bình thường thôi mà em\".\"",
                    "\"Khi mình cùng người khuyết tật vào cửa hàng, họ bảo \"Lại vào đây xin tiền hả?\"\"",
                    R.drawable.dai,
                    R.drawable.dai_item,
                    R.raw.a_dai),

            //5
            new Profile("Quyên",
                    "\"Thà là mình, đánh mình một cái thật đau còn hơn là nghe những lời người khác sỉ vả.\"",
                    "\"Thà là mình, đánh mình một cái thật đau...\"",
                    R.drawable.quyen,
                    R.drawable.quyen_item,
                    R.raw.chi_quyen),
            //6
            new Profile("Cá",
                    "\"i see myself in the ceiling\"",
                    "\"i see myself in the ceiling\"",
                    R.drawable.ca,
                    R.drawable.ca_item,
                    R.raw.ca),
            //7
            new Profile("Best moment của Cá và người yêu Cá",
                    "\"She's my present and my future. That's something that I can not describe what it was. That's something I never had before - I never expected to have.\"",
                    "\"She's my present and my future. That's something that I can not describe what it was... \"",
                    R.drawable.best_moment,
                    R.drawable.best_moment_item,
                    R.raw.best_moment_of_ca),

            //8
            new Profile("Người yêu của Cá",
                    "\"She's my present and my future. That's something that I can not describe what it was. That's something I never had before - I never expected to have.\"",
                    "\"She's my present and my future. That's something that I can not describe what it was... \"",
                    R.drawable.ny_ca,
                    R.drawable.ny_ca_item,
                    R.raw.ny_ca),
            //9
            new Profile("Cá #2",
                    "\"Xin chào, năm nay tớ 19 tuổi, tớ đang học một ĐH bên Nhật, tớ là một con cá và tớ gọi bản thân mình là Cá Bơi Ngửa, tại vì... khi mà Cá Bơi Ngửa là cá đã chết ** nó rồi.\"",
                    "Xin chào, năm nay tớ 19 tuổi, tớ đang học một ĐH bên Nhật, tớ là một con cá và tớ gọi bản thân mình là Cá Bơi Ngửa, tại vì...",
                    R.drawable.ca2,
                    R.drawable.ca_item,
                    R.raw.ca2),
            //10

            new Profile("Chị Vân",
                    "\"trạng thái tâm lí người ta bất cần thì người ta sẵn sàng làm mọi thứ\"",
                    "“trạng thái tâm lí người ta bất cần thì người ta sẵn sàng làm mọi thứ.”",
                    R.drawable.van,
                    R.drawable.van_item,
                    R.raw.chi_van),
            //11
            new Profile("Eric Phạm",
                    "\"Thì lúc đấy trong đầu mình nghĩ là à hoá ra là mình cũng có thể yêu 2 người 1 lúc. Có thể nếu là có người phù hợp thì có thể thêm 3,4,5 gì đấy thì chưa khám phá ra chỉ là hiện tại mình có khả năng yêu 2 người 1 lúc\"",
                    "\"Thì lúc đấy trong đầu mình nghĩ là à hoá ra là mình cũng có thể yêu 2 người 1 lúc.\"",
                    R.drawable.eric,
                    R.drawable.eric_item,
                    R.raw.eric),
//            //12
            new Profile("Đỗ Đức Sơn",
                    "\"Cuộc đời hít drama để sống mà.\"",
                    "\"Cuộc đời hít drama để sống mà.\"",
                    R.drawable.doducson,
                    R.drawable.sonblog_item,
                    R.raw.son_not_blog
            ),
            //13
            new Profile("Hùng",
                    "\"Nhiều khi đau quá khiến con người phi thường lắm, tự khâu sống được, em tự khâu sống mấy lần rồi.\"",
                    "\"Nhiều khi đau quá khiến con người phi thường lắm, tự khâu sống được...\"",
                    R.drawable.hung,
                    R.drawable.hung_item_test,
                    R.raw.hung),
            //14
            new Profile("Chú Giang (Bố Hùng)",
                    "\"Chả thấy lúc nào nó cười cả, lúc nào cũng thấy mặt nhăn khó chịu. Không bao giờ thấy nó cười tươi cả. Kiểu tâm tư nó nghĩ là không khỏi bệnh, chán rồi. Chả bao giờ thấy nó cười được câu nào. 3 năm nay rồi.\"",
                    "\"Chả thấy lúc nào nó cười cả, lúc nào cũng thấy mặt nhăn khó chịu. Không bao giờ thấy nó cười tươi cả.\"",
                    R.drawable.chu_giang,
                    R.drawable.chu_giang_item,
                    R.raw.hung_dad),
            //15
            new Profile("Tú #1",
                    "Sau đấy thì Tú buồn, thế là Tú hỏi là:\n" +
                            "\"Thế em còn trinh không ạ?\"\n" +
                            "\"Em mất trinh rồi còn đâu.\"\n" +
                            "Thế là Tú buồn, khóc mẹ luôn, buồn vãi.",
                    "Sau đấy thì Tú buồn, thế là Tú hỏi là:\n" +
                            "\"Thế em còn trinh không ạ?\"",
                    R.drawable.tu,
                    R.drawable.tu_item,
                    R.raw.tu1),
            //16
            new Profile("Tú #2",
                    "Sau đấy thì Tú buồn, thế là Tú hỏi là:\n" +
                            "\"Thế em còn trinh không ạ?\"\n" +
                            "\"Em mất trinh rồi còn đâu.\"\n" +
                            "Thế là Tú buồn, khóc mẹ luôn, buồn vãi.",
                    "Sau đấy thì Tú buồn, thế là Tú hỏi là:\n" +
                            "\"Thế em còn trinh không ạ?\"",
                    R.drawable.tu,
                    R.drawable.tu_item,
                    R.raw.tu2),
            //17
            new Profile("Em Tú (Sơn)",
                    "\"Em nghĩ dùng cái cụm từ này nó ổn hơn, \" nạn nhân của thế giới \" ý. Em nghĩ bản thân em là nạn nhân của cái cuộc đời này. Chị em là nạn nhân của cuộc đời này.\"",
                    "\"Em nghĩ dùng cái cụm từ này nó ổn hơn, \" nạn nhân của thế giới \" ý.\"",
                    R.drawable.toson,
                    R.drawable.toson_item,
                    R.raw.to_son),
            //18
            new Profile("Bà nội Long (1945 - 1975)",
                    "\"Bà nghĩ thời đại nào thì cũng có cái hay của nó, và cũng có cái dở của nó. Không có thời nào là hoàn hảo cả.\"",
                    "\"Bà nghĩ thời đại nào thì cũng có cái hay của nó, và cũng có cái dở của nó...\"",
                    R.drawable.long_gramp,
                    R.drawable.long_gramp_item,
                    R.raw.long_gramp_45_75),
            //19
            new Profile("Bà nội Long (sau 1975)",
                    "\"Bà nghĩ thời đại nào thì cũng có cái hay của nó, và cũng có cái dở của nó. Không có thời nào là hoàn hảo cả.\"",
                    "\"Bà nghĩ thời đại nào thì cũng có cái hay của nó, và cũng có cái dở của nó...\"",
                    R.drawable.long_gramp,
                    R.drawable.long_gramp_item,
                    R.raw.long_gramp_b75),
            //20
            new Profile("G1",
                    "\"Có những thứ đáng để cho mình hằn học đến mức độ như thế hay không khi mà mình chưa biết được nhiều thứ khác, khi mà mình chưa nhìn mục đích bằng con mắt của những người bên kia?\"",
                    "\"Có những thứ đáng để cho mình hằn học đến mức độ như thế hay không khi mà mình chưa biết được nhiều thứ khác,\"",
                    R.drawable.giang_hid,
                    R.drawable.giang_hid_item,
                    R.raw.giang1
                    ),
            //21
            new Profile("G2",
                    "\"Có những thứ đáng để cho mình hằn học đến mức độ như thế hay không khi mà mình chưa biết được nhiều thứ khác, khi mà mình chưa nhìn mục đích bằng con mắt của những người bên kia?\"",
                    "\"Có những thứ đáng để cho mình hằn học đến mức độ như thế hay không khi mà mình chưa biết được nhiều thứ khác,\"",
                    R.drawable.giang_hid,
                    R.drawable.giang_hid_item,
                    R.raw.giang2
                    ),
            //22
            new Profile("Nyjah",
                    "Thế kiểu đã bao giờ cậu cảm thấy là mình rất là cô độc chưa? không có ai hiểu mình ý?\n" +
                            "Lúc nào tớ cũng thế.\n",
                    "Thế kiểu đã bao giờ cậu cảm thấy là mình rất là cô độc chưa? không có ai hiểu mình ý?\n",
                    R.drawable.nyjah,
                    R.drawable.nyjah_item,
                    R.raw.nyjah),
            //23
            new Profile("Thầy Thắng",
                    "\"Hôm nay không làm được, ngày mai ta làm đc, miễn ta ko mất niềm tin, ko mất động lực là đc\"",
                    "\"Hôm nay không làm được, ngày mai ta làm đc,\"",
                    R.drawable.thang,
                    R.drawable.thang_item,
                    R.raw.thay_thang
                    ),
            //24
            new Profile("Phương Vũ",
                    "\"Đối với tớ từ lúc đấy là kiểu tất cả mọi thứ nó có thể diễn ra được ấy\"",
                    "\"Đối với tớ từ lúc đấy là kiểu tất cả mọi thứ nó có thể diễn ra được ấy\"",
                    R.drawable.phuong_vu,
                    R.drawable.phuong_vu_item,
                    R.raw.phuong_vu),
//            //25
            new Profile("Bác Tươi",
                    "\"Mình đi thì mình cứ đi, cứ làm thôi, chứ chẳng có cái việc gì là việc thích\"",
                    "\"Mình đi thì mình cứ đi, cứ làm thôi,\"",
                    R.drawable.tuoi,
                    R.drawable.tuoi_item,
                    R.raw.tuoi),
//            26
            new Profile("Gấu Mèo",
                    "\"Lúc đấy mình rất mong muốn tìm kiếm một lời khuyên hoặc ko phải lời khuyên thì là cái gì đấy cho mình thấy có ánh sáng... và thấy dễ thở hơn và có thể ra quyết định đúng hơn, nhưng mà ko có một ai cả. \"",
                    "\"Lúc đấy mình rất mong muốn tìm kiếm một lời khuyên hoặc ko phải lời khuyên thì là cái gì đấy cho mình thấy có ánh sáng...\"",
                    R.drawable.gau_meo,
                    R.drawable.gau_meo_item,
                    R.raw.gau_meo
                    ),

            //27
            new Profile("Chị Huế",
                    "\"Về đến nhà lúc đấy, vừa lấy cái lược chải đầu, cứ chải một phát thấy rụng một túm nhỏ, lấy tay vuốt tóc thì rụng hết, mình cứ khóc thôi...\"",
                    "\"Về đến nhà lúc đấy, vừa lấy cái lược chải đầu, cứ chải một phát thấy rụng một túm nhỏ,...\"",
                    R.drawable.hue,
                    R.drawable.hue_item,
                    R.raw.hue),
            //28
            new Profile("Plaaastic",
                    "\"Tất cả công việc mình làm đều xuất phát từ tình yêu, mình yêu công việc của mình, mình yêu những người xung quanh mình, mình yêu cái Trái Đất này..\"",
                    "\"Tất cả công việc mình làm đều xuất phát từ tình yêu,...\"",
                    R.drawable.plaaastic,
                    R.drawable.plaaastic_item,
                    R.raw.plaaastic),
            //29
            new Profile("Vọng",
                    "\"Người ta hàng xóm các thứ là người ta không có ưa, người ta bảo là, đó thằng đó chơi ma túy đó, giờ mới bị bắt. Nói chung chơi ma túy là không có ai ưa.\"",
                    "\"Người ta hàng xóm các thứ là người ta không có ưa, người ta bảo là,...\"",
                    R.drawable.vong,
                    R.drawable.vong_item,
                    R.raw.vong),
            //30
            new Profile("Kỉ niệm tuyệt vời nhất của bạn",
                    "\"Kỉ niệm tuyệt vời nhất của bạn là gì\"",
                    "\"Kỉ niệm tuyệt vời nhất của bạn là gì\"",
                    R.drawable.thing_cam_profile,
                    R.drawable.thing_cam_item,
                    R.raw.best_memory)

    };

    private Profile(String name, String description, String itemDescription, int profileImagePath, int itemImagePath, int audioPath) {
        this.name = name;
        this.description = description;
        this.profilePicPath = profileImagePath;
        this.audioPath = audioPath;

        this.itemImagePath = itemImagePath;
        this.itemDescription = itemDescription;
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

    public String getItemDescription() {
        return this.itemDescription;
    }
}
