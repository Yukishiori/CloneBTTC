package com.waifusystem.duplicate;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment {

    private int id;

    private TextView topText;
    private ImageView randomImage;
    private TextView infoDescription;

    private String[] description = new String[]{
            //ban to chuc
            "Dự án Bảo tàng Thấu Cảm - Empathy Museum (EM) được thành lập và " +
                    "điều hành do một nhóm các bạn trẻ năng động, có nền tảng vững chắc trong" +
                    "lĩnh vực tổ chức các sự kiện vì cộng đồng. Bên cạnh đó, Dự án này còn nhận" +
                    "được sự cố vấn về chuyên môn trực tiếp của TS. Đặng Hoàng Giang (Phó " +
                    "Giám Đốc Trung tâm Nghiên cứu phát triển và hỗ trợ cộng đồng (CECODES).",
            //mo hinh
            "Bảo tàng Thấu Cảm là dự án được truyền " +
                    "cảm hứng bởi mô hình Empathy Museum, " +
                    "với mục tiêu chính là đem đến cho người " +
                    "tham gia một trải nghiệm giúp họ nhìn đời " +
                    "qua lăng kính của người khác, đặt mình vào " +
                    "hoàn cảnh của các nhân vật, nhằm thấu hiểu " +
                    "và tôn trọng cảm xúc của những người đến " +
                    "từ mọi tầng lớp, mọi hoàn cảnh khác nhau trong xã hội",

            //noi dung
            "\"Bảo tàng thấu cảm\" vốn được lấy ý tưởng từ \"thấu cảm\" - một từ khóa đã tạo nên nhiều ý kiến " +
                    "trái chiều sau khi xuất hiện trong đề thi Văn Quốc Gia. Một số ý kiến cho rằng \"thấu cảm\" không hề " +
                    "tồn tại trong từ điển tiếng Việt; vì thế, Tiến Sĩ Đặng Hoàng Giang (tác giả cuốn sách “Thiện, Ác và " +
                    "Smartphone”) đã đưa ra một định nghĩa mới cho từ này: \"Thấu cảm là khả năng nhìn thế giới bằng " +
                    "con mắt của người khác, đặt mình vào thế giới của họ. Giống như cái lạnh thấu tủy hay cái đau thấu " +
                    "xương, thấu cảm là sự hiểu biết thấu đáo, trọn vẹn một ai đó, khiến ta hiểu được những suy nghĩ của " +
                    "họ, cảm được những suy nghĩ của họ, và tất cả xảy ra mà không có sự phán xét\". " +
                    "Dựa trên tinh thần ấy, \"Bảo tàng thấu cảm\" được lập ra với những con người cùng chung chí " +
                    "hướng - xây dựng và gắn kết mối quan hệ giữa người với người một cách chân thực và bền chặt hơn.",
            //thong tin lien he
            "Ban Tổ chức xin chân thành cảm ơn Quý Doanh nghiệp/Quý Tổ chức đã " +
                    "quan tâm đến Dự án Bảo tàng Thấu Cảm. Mọi thông tin chi tiết, xin vui lòng liên hệ:\n " +
                    "N g u y ễ n L ê H à L i n h ( M s . )\n" +
                    "Trưởng Ban Đối ngoại Dự án Bảo tàng Thấu Cảm Email: nguyenlehalinh1120@gmail.com\n" +
                    "M a r i l y n P h a m D a c u s i n ( M s . )\n" +
                    "Trưởng Ban Tổ chức Dự án Bảo tàng Thấu cảm Email: marilynphamdacusin@gmail.com",

            //sponsor
            "1. Giới thiệu về đơn vị Qùa tặng điện tử Urbox\n" +
                    "Hãy đón nhận mọi điều trong tâm thế của một trái tim rộng lớn!\n" +
                    "Bước đầu tiên để “hiểu” người đối diện là lắng nghe, là quan tâm, là yêu thương từ những điều nhỏ bé nhất. Những điều nhỏ nhắn, đẹp đẽ trong thế giới này đều mang lại ý nghĩa quan trọng. Trong tình yêu, những món quà đắt tiền từ người thương cũng không bằng câu nói: \"Anh đây!\" mỗi khi cô nhõng nhẽo, những lúc mệt mỏi có anh là nhà. Nhiều tiền gửi cho bố mẹ không bằng lời hỏi thăm, bữa cơm cùng ăn với gia đình... Mỗi chúng ta đều là những hạt nhân lan truyền hạnh phúc, lan truyền sự tử tế, lan truyền sự quan tâm.\n" +
                    " \n" +
                    "Urbox thật hạnh phúc khi mang trong mình sứ mệnh kết nối mọi người với nhau. Bằng những món quà nhỏ nhắn hằng ngày, thiệp điện tử thông minh, chỉ cần một vài nút ấn là có thể gửi quà, gửi cả sự yêu thương mà từ bấy lâu chúng ta bỏ quên mất giữa bộn bề cuộc sống. Một món quà kèm lời cảm ơn, một món quà cùng lời xin lỗi, một món quà có lời yêu là những điều bé nhỏ mà chúng ta có thể làm mỗi ngày cùng Urbox.vn.\n" +
                    "\n" +
                    "2. Giới thiệu Grab Promotion\n" +
                    "Tháng 4 đong đầy yêu thương, Urbox cùng Grab mang đến món quà đặc biệt cho các Urbox-ers, từ ngày 10/04 - 10/05/2018 chỉ cần gửi một món quà thành công trên Urbox, bạn nhận được mã Code Grab trị giá 30.000đ.\n" +
                    "Hãy để Urbox đồng hành cùng bạn trong việc trao yêu thương từ những món quà nhỏ bé đến những người xung quanh.\n" +
                    "\n" +
                    "3. Giới thiệu về Urbox trên app\n" +
                    "Urbox.vn là giải pháp quà tặng điện tử thông minh và tiện lợi dành cho khách hàng cá nhân và doanh nghiệp. Với mong muốn xây dựng một phong cách tặng quà độc đáo Urbox.vn hy vọng có thể biến việc tặng quà trở thành một thói \n" +
                    "quen, giúp việc giao tiếp trở nên dễ dàng hơn.\n\n" +
                    "4. Giới thiệu về Urbox trên website\n" +
                    "Urbox – là hệ thống tặng quà điện tử hiện đại và thông minh, cho phép bạn tặng quà và gửi lời chúc hàng ngày dễ dàng thông qua chỉ vài cú nhấp chuột.\n" +
                    "Bạn chỉ cần đến DUY NHẤT cửa hàng là Urbox.vn để có thể chọn lựa rất nhiều món quà, phù hợp cho mọi đối tượng và mọi độ tuổi, với mức giá vô cùng HỢP LÝ, \n" +
                    "Quà tặng bạn chọn sẽ được gửi đi TỰ ĐỘNG và NGAY LẬP TỨC. \n" +
                    "Món quà đến tay người nhận sẽ được gói gọn trong MỘT MÃ CODE gửi qua email và tin nhắn SMS, vì vậy món quà sẽ luôn nằm trong điện thoại 24/24  \n" +
                    "Với Urbox, bạn sẽ không còn lo lắng về khoảng cách địa lý\n" +
                    " SỬ DỤNG URBOX THẾ NÀO?\t\n\n" +
                    "B1: Truy cập http://urbox.vn/ và đăng nhập bằng tài khoản Facebook/Gmail\n" +
                    "B2: Chọn món quà bạn muốn gửi tặng\n" +
                    "B3: Nhập thông tin người nhận, chọn thiệp và để lại lời nhắn\n" +
                    "B4: Ấn nút Tặng ngay và thanh toán online với cổng thanh toán điện tử\n" +
                    "\n"
    };

    private String[] topTexts = new String[] {
            //filler
            "Ban tổ chức",
            "Mô hình",
            "Nội dung",
            "Thông tin liên hệ",
            "Nhà tài trợ"
    };

    private int[] randomImagesPath = new int[] {
            R.drawable.hung_item,
            R.drawable.question_mark,
            R.drawable.best_moment_item,
            R.drawable.dai_item,
            R.drawable.gau_meo_item,
            R.drawable.vong_item,
            R.drawable.eric_item,
            R.drawable.giang_hid_item,
            R.drawable.hue_item
    };

    public InfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        Random random = new Random();

        randomImage = view.findViewById(R.id.random_image);
        infoDescription = view.findViewById(R.id.info_description);
        topText = view.findViewById(R.id.top_text);
        if (id < 4) {
            randomImage.setImageResource(randomImagesPath[random.nextInt(randomImagesPath.length)]);
        } else {
            randomImage.setImageResource(R.drawable.sponsor_logo);
        }
        infoDescription.setText(description[id]);
        topText.setText(topTexts[id]);
        return view;
    }

    public void setId(int id) {
        this.id = id;
    }

}
