package baitapquanlysach;
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        //tạo đối tượng sách
        Sach s1 = new Sach();
        s1.maSach = "S01";
        s1.tieuDe = "Lap trinh Java";
        s1.tacGia = "Nguyen Van A";
        s1.namXuatBan = 2025;
        s1.soLuong = 10;

        Sach s2 = new Sach();
        s2.maSach = "S02";
        s2.tieuDe = "Lap trinh C++";
        s2.tacGia = "Nguyen Van B";
        s2.namXuatBan = 2024;
        s2.soLuong = 15;

        //tạo arraylist để lưu trữ sách 
        ArrayList<Sach> danhSachSach = new ArrayList<>();
        //thêm sách vào arraylist
        danhSachSach.add(s1);
        danhSachSach.add(s2);
        //hiển thị thông tin sách
        for (Sach s : danhSachSach) {
            s.hienThiThongTin();
            System.out.println("------------------");
        }

        //đếm số lượng sách 
        System.out.println("Tong so sach: " + danhSachSach.size());
    }
} //end class

