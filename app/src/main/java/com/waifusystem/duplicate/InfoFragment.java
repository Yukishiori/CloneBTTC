package com.waifusystem.duplicate;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


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
                    "Trưởng Ban Tổ chức Dự án Bảo tàng Thấu cảm Email: marilynphamdacusin@gmail.com"
    };

    private String[] topTexts = new String[] {
            //filler
            "Ban tổ chức",
            "Mô hình",
            "Nội dung",
            "Thông tin liên hệ"
    };

    private int[] randomImagesPath = new int[] {

    };

    public InfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        randomImage = view.findViewById(R.id.random_image);
        infoDescription = view.findViewById(R.id.info_description);
        topText = view.findViewById(R.id.top_text);

        infoDescription.setText(description[id]);
        topText.setText(topTexts[id]);

        return view;
    }

    public void setId(int id) {
        this.id = id;
    }

}
