package com.blogspot.quanlytomatoads.common;

/**
 * Created by DaiPhongPC on 7/1/2017.
 */

public class ListNotification {
    //0: cong 5000 điểm thưởng
    //1: cong cài đặt 1000 điểm
    //2: trừ 1000 điểm
    //3: cong 200 điểm mo app
    //4: trừ 200 điểm mo app
    //5: cong 300 điểm sau 1 ngày
    //6: trừ 300 điểm sau 1 ngày

    public static final String ConvertNotifaction(int group, String nameapp, String namedev,int diem) {
        String s = "";
        switch (group) {
            case 0: {
                s = "Bạn được cộng 5000 điểm.";
                break;
            }
            case 1: {
                s = "Bạn được +" + 1000 + " điểm khi cài đặt thành công " + nameapp +
                        " của " + namedev+". Điểm hiện tại "+diem;
                break;
            }
            case 2: {
                s = "Ứng dụng "+nameapp+" được cài đặt thành công bởi "+namedev;
                break;
            }
            case 3: {
                s = "Bạn được +" + 200 + " điểm khi mở thành công " + nameapp + " của " +
                        namedev+". Điểm hiện tại "+diem;
                break;
            }
            case 4: {
                s = "Ứng dụng "+nameapp+" được mở bởi "+namedev;
                break;
            }
            case 5: {
                s = "Bạn được +" + 300 + " điểm khi ứng dụng " + nameapp + " của " + namedev +
                        " còn trong điện thoại bạn sau 1 ngày"+". Điểm hiện tại "+diem;
                break;
            }
            case 6: {
                s = "Ứng dụng "+nameapp+" còn trong điện thoại "+namedev+" sau 1 ngày";
                break;
            }
            case 7:{
                s="Bạn vừa tạo thành công chiến dịch cho ứng dụng "+". Điểm hiện tại "+diem;
            }


        }
        return s;
    }

}
