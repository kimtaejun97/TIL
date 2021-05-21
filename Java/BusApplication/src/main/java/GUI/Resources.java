package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class Resources {

    // 테스트를 위한 배열
    public static String[][] testArray(int size){
        String[][] array=new String[2][size];
        for(int i=0; i<size; i++){
            array[0][i]=String.valueOf(i);
            array[1][i]="Value"+String.format("%05d", i);
        }
        return array;
    }


    // 리스트에서 해당 문자열을 갖는 항목을 반환
    public static String[][] search(String[][] list, String what){

       ArrayList<String[]> found=new ArrayList<>();

        for (int i=0; i<list[0].length; i++){
            if(list[1][i].contains(what)) found.add(new String[]{list[0][i], list[1][i]});
        }
        String[][] find=new String[2][found.size()];
        for (int i=0; i<found.size(); i++){
            find[0][i]=found.get(i)[0];
            find[1][i]=found.get(i)[1];
        }
        return find;
    }

    // 리스트에서 해당 문자열의 번호를 반환
    public static int[] searchIndex(String[][] list, String what){
        ArrayList<Integer> found=new ArrayList<>();

        for (int i=0; i<list[0].length; i++){
            if(list[1][i].contains(what)) found.add(Integer.parseInt(list[0][i]));
        }
        int[] find=new int[found.size()];
        for (int i=0; i<found.size(); i++){
            find[i] = found.get(i);
        }
        return find;
    }


    // Image
    static final String IMG_BUS_ORANGE = "bus1.png";
    static final String IMG_BUS_ORANGE2 = "bus2.png";
    static final String IMG_BUS_RED = "bus3.png";
    static final String IMG_BUS_GREEN = "bus4.png";
    static final String IMG_BUS_TOWN = "bus5.png";
    static final String IMG_BUS_WIDE = "bus6.png";
    static final String IMG_STOP1 = "stop1.png";
    static final String IMG_STOP2 = "stop2.png";
    static final String IMG_EMPTY = "empty.png";

    // Icon
    // 정상 비율 아이콘: 버튼, 라벨
    public static ImageIcon getBtImage(String imgName, int width) {
        ImageIcon ico = new ImageIcon("src/main/resources/" + imgName); //src/main/resources 하위 폴더
        Image image = ico.getImage().getScaledInstance(width, -1, Image.SCALE_SMOOTH);  // 두 값 중 하나가 음수면 원본 비율 유지
        ico.setImage(image);
        return ico;
    }

    // 자유 비율 아이콘: 버튼, 라벨
    public static ImageIcon getBtImage(String imgName, int width, int height) {
        ImageIcon ico = new ImageIcon("src/main/resources/" + imgName); //src/main/resources 하위 폴더
        Image image = ico.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);  // 두 값 중 하나가 음수면 원본 비율 유지
        ico.setImage(image);
        return ico;
    }

    // Image: 창 아이콘
    public static Image getWindowIco(String imgName) {
        return new ImageIcon("src/main/resources/" + imgName).getImage(); //src/main/resources 하위 폴더
    }

    // Color
    static final Color COLOR_SKY = new Color(58, 184, 255);
    static final Color COLOR_GRAY = new Color(176, 176, 176);
    static final Color COLOR_PURPLE = new Color(112, 48, 160);
    static final Color COLOR_BLUE_DARK = new Color(0, 112, 192);
    static final Color COLOR_PINK = new Color(238,150,200);

    static final Color COLOR_RED_BUS = new Color(255, 84, 84);
    static final Color COLOR_YELLOW_BUS = new Color(255, 153, 0);
    static final Color COLOR_TOWN_BUS = new Color(102, 192, 0);
    static final Color COLOR_GREEN_BUS = new Color(60, 113, 0);
    static final Color COLOR_WIDE_BUS = new Color(158, 82, 255);


    // Font
    static final String FONT_NanumSq = "나눔스퀘어";
    static final String FONT_NanumSqBold = "나눔스퀘어 Bold";

    static final String FONT_BOLD = "Bold";
    static final String FONT_NORMAL = "Normal";

//    static Font nsq(String type, int size) {
//        if (type.equals(FONT_BOLD))
//            return new Font(FONT_NanumSqBold, Font.PLAIN, size);
//        else return new Font(FONT_NanumSq, Font.PLAIN, size);
//    }

    static Font nsq(String type, int size) {
        Font nsq;

        if (type.equals(FONT_BOLD))
        try {
            nsq=Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/NanumSquareB.ttf")).deriveFont((float) size);
        } catch (Exception e){
            nsq=new Font(FONT_NanumSqBold, Font.PLAIN, size);
        }
        else try {
            nsq=Font.createFont(Font.TRUETYPE_FONT, new File("src/main/resources/NanumSquareR.ttf")).deriveFont((float) size);
        } catch (Exception e){
            nsq=new Font(FONT_NanumSq, Font.PLAIN, size);
        }

        return nsq;
    }

}
