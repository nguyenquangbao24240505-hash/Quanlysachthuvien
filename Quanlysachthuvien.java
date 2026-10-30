import java.time.Year;
import java.util.Scanner;

public class Quanlysachthuvien {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Bài 1: Nhập thông tin sách và hiển thị lại
        System.out.println("===== NHAP THONG TIN SACH =====");
        System.out.print("Nhap ma sach: ");
        String maSach = sc.nextLine();
        System.out.print("Nhap ten sach: ");
        String tenSach = sc.nextLine();
        System.out.print("Nhap tac gia: ");
        String tacGia = sc.nextLine();
        System.out.print("Nhap nam xuat ban: ");
        int namXuatBan = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Nhap don gia (VNĐ): ");
        double donGia = Double.parseDouble(sc.nextLine().trim());
        System.out.print("Nhap so luong: ");
        int soLuong = Integer.parseInt(sc.nextLine().trim());
        System.out.println();
        System.out.println("===== THONG TIN SACH VUA NHAP =====");
        System.out.println("Ma sach      : " + maSach);
        System.out.println("Ten sach     : " + tenSach);
        System.out.println("Tac gia      : " + tacGia);
        System.out.println("Nam xuat ban : " + namXuatBan);
        System.out.printf("Don gia    : %,.0f VND%n", donGia);
        System.out.println("So luong    : " + soLuong);

        // Bài 2: Thành tiền = đơn giá x số lượng
        double thanhTien = donGia * soLuong;

        // Bài 3: Phí vận chuyển = 5% giá trị sách
        double phiVanChuyen = thanhTien * 0.05;

        // Bài 4: Tổng tiền nhập = Thành tiền + Phí vận chuyển
        double tongTienNhap = thanhTien + phiVanChuyen;

        System.out.println();
        System.out.println("===== GIA TRI NHAP KHO =====");
        System.out.printf("Thanh tien         : %,.0f VND%n", thanhTien);
        System.out.printf("Phi van chuyen (5%%): %,.0f VND%n", phiVanChuyen);
        System.out.printf("Tong tien nhap    : %,.0f VND%n", tongTienNhap);
                // ================= TUAN 4 =================

        // Bai 1: Kiem tra gia > 0, so luong >= 0, nam xuat ban hop le
        System.out.println();
        System.out.println("===== KIEM TRA SACH HOP LE =====");
        int namHienTai = Year.now().getValue();
        boolean hopLe = true;

        if (donGia <= 0) {
            System.out.println("Loi: don gia phai lon hon 0");
            hopLe = false;
        }
        if (soLuong < 0) {
            System.out.println("Loi: so luong phai lon hon hoac bang 0");
            hopLe = false;
        }
        if (namXuatBan < 1900 || namXuatBan > namHienTai) {
            System.out.println("Loi: nam xuat ban phai tu 1900 den " + namHienTai);
            hopLe = false;
        }

        if (!hopLe) {
            System.out.println("=> Sach KHONG hop le");
        } else {
            System.out.println("=> Sach hop le");

            // Bai 2: Phan loai gia
            String loaiGia;
            if (donGia < 50000) {
                loaiGia = "Sach gia thap";
            } else if (donGia <= 100000) {
                loaiGia = "Sach gia trung binh";
            } else if (donGia <= 200000) {
                loaiGia = "Sach gia cao";
            } else {
                loaiGia = "Sach gia dac biet";
            }

            // Bai 3: Phan loai ton kho
            String tinhTrangKho;
            if (soLuong == 0) {
                tinhTrangKho = "Het sach";
            } else if (soLuong <= 5) {
                tinhTrangKho = "Sap het";
            } else if (soLuong <= 20) {
                tinhTrangKho = "Con it";
            } else {
                tinhTrangKho = "Con nhieu";
            }

            // Bai 4: Dung switch chon loai sach
            System.out.println();
            System.out.println("===== CHON LOAI SACH =====");
            System.out.println("1. Sach giao khoa");
            System.out.println("2. Sach tham khao");
            System.out.println("3. Sach chuyen nganh");
            System.out.println("4. Sach khac");
            System.out.print("Nhap lua chon (1-4): ");
            int luaChon = Integer.parseInt(sc.nextLine().trim());

            String loaiSach;
            switch (luaChon) {
                case 1:
                    loaiSach = "Sach giao khoa";
                    break;
                case 2:
                    loaiSach = "Sach tham khao";
                    break;
                case 3:
                    loaiSach = "Sach chuyen nganh";
                    break;
                case 4:
                    loaiSach = "Sach khac";
                    break;
                default:
                    loaiSach = "Khong hop le";
                    break;
            }

            System.out.println();
            System.out.println("===== KET QUA PHAN LOAI =====");
            System.out.println("Phan loai gia: " + loaiGia);
            System.out.println("Ton kho      : " + tinhTrangKho);
            System.out.println("Loai sach    : " + loaiSach);
        }
        sc.close();
    }
}