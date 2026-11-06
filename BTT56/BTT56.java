import java.time.Year;
import java.util.Scanner;

public class BTT56 {

    static final int MAX = 100;           // so sach toi da
    static Scanner sc = new Scanner(System.in);

    // Danh sach sach (cac mang song song, vi tri i la cung 1 cuon sach)
    static String[] maSach = new String[MAX];
    static String[] tenSach = new String[MAX];
    static String[] tacGia = new String[MAX];
    static int[] namXuatBan = new int[MAX];
    static double[] donGia = new double[MAX];
    static int[] soLuong = new int[MAX];
    static int soSach = 0;                // so sach hien co

    public static void main(String[] args) {
        int luaChon;

        // Bai 3: Menu lap den khi chon Thoat
        do {
            System.out.println();
            System.out.println("========== QUAN LY SACH THU VIEN ==========");
            System.out.println("1. Nhap n sach");
            System.out.println("2. Hien thi danh sach sach");
            System.out.println("3. Tinh tong so luong va tong gia tri");
            System.out.println("4. Tim sach theo ma");
            System.out.println("0. Thoat");
            System.out.println("===========================================");
            luaChon = docSoNguyen("Nhap lua chon: ");

            switch (luaChon) {
                case 1:
                    nhapNSach();
                    break;
                case 2:
                    hienThiDanhSach();
                    break;
                case 3:
                    tinhTong();
                    break;
                case 4:
                    timSachTheoMa();
                    break;
                case 0:
                    System.out.println("Tam biet!");
                    break;
                default:
                    System.out.println("Lua chon khong hop le, vui long chon lai");
                    break;
            }
        } while (luaChon != 0);

        sc.close();
    }

    // Bai 1: Nhap n sach bang vong lap
    static void nhapNSach() {
        int conTrong = MAX - soSach;
        if (conTrong == 0) {
            System.out.println("Danh sach da day (" + MAX + " sach)");
            return;
        }

        int n = docSoNguyen("Nhap so sach can nhap (n): ");
        while (n <= 0 || n > conTrong) {
            System.out.println("n phai tu 1 den " + conTrong);
            n = docSoNguyen("Nhap lai n: ");
        }

        int namHienTai = Year.now().getValue();

        for (int i = 1; i <= n; i++) {
            System.out.println();
            System.out.println("--- Nhap sach thu " + i + "/" + n + " ---");

            // Ma sach: khong duoc rong, khong duoc trung
            String ma;
            while (true) {
                System.out.print("Nhap ma sach: ");
                ma = sc.nextLine().trim();
                if (ma.isEmpty()) {
                    System.out.println("Ma sach khong duoc de trong");
                } else if (timViTri(ma) != -1) {
                    System.out.println("Ma sach da ton tai, nhap ma khac");
                } else {
                    break;
                }
            }

            System.out.print("Nhap ten sach: ");
            String ten = sc.nextLine().trim();

            System.out.print("Nhap tac gia: ");
            String tg = sc.nextLine().trim();

            // Nam xuat ban hop le (tuan 4): 1900 den nam hien tai
            int nam = docSoNguyen("Nhap nam xuat ban: ");
            while (nam < 1900 || nam > namHienTai) {
                System.out.println("Nam xuat ban phai tu 1900 den " + namHienTai);
                nam = docSoNguyen("Nhap lai nam xuat ban: ");
            }

            // Don gia > 0
            double gia = docSoThuc("Nhap don gia (VND): ");
            while (gia <= 0) {
                System.out.println("Don gia phai lon hon 0");
                gia = docSoThuc("Nhap lai don gia (VND): ");
            }

            // So luong >= 0
            int sl = docSoNguyen("Nhap so luong: ");
            while (sl < 0) {
                System.out.println("So luong phai lon hon hoac bang 0");
                sl = docSoNguyen("Nhap lai so luong: ");
            }

            maSach[soSach] = ma;
            tenSach[soSach] = ten;
            tacGia[soSach] = tg;
            namXuatBan[soSach] = nam;
            donGia[soSach] = gia;
            soLuong[soSach] = sl;
            soSach++;
        }

        System.out.println();
        System.out.println("Da nhap them " + n + " sach. Tong so sach hien co: " + soSach);
    }

    // Hien thi toan bo danh sach
    static void hienThiDanhSach() {
        if (soSach == 0) {
            System.out.println("Danh sach trong, hay chon 1 de nhap sach");
            return;
        }

        System.out.println();
        System.out.println("===== DANH SACH SACH =====");
        System.out.printf("%-4s %-10s %-22s %-18s %-6s %-14s %-8s %-10s%n",
                "STT", "Ma", "Ten sach", "Tac gia", "Nam", "Don gia", "SL", "Ton kho");
        for (int i = 0; i < soSach; i++) {
            System.out.printf("%-4d %-10s %-22s %-18s %-6d %,-14.0f %-8d %-10s%n",
                    i + 1, maSach[i], tenSach[i], tacGia[i], namXuatBan[i],
                    donGia[i], soLuong[i], phanLoaiTonKho(soLuong[i]));
        }
    }

    // Bai 2: Tinh tong so luong va tong gia tri sach
    static void tinhTong() {
        if (soSach == 0) {
            System.out.println("Danh sach trong, hay chon 1 de nhap sach");
            return;
        }

        int tongSoLuong = 0;
        double tongGiaTri = 0;

        for (int i = 0; i < soSach; i++) {
            tongSoLuong += soLuong[i];
            tongGiaTri += donGia[i] * soLuong[i];
        }

        double phiVanChuyen = tongGiaTri * 0.05;
        double tongTienNhap = tongGiaTri + phiVanChuyen;

        System.out.println();
        System.out.println("===== THONG KE =====");
        System.out.println("So dau sach          : " + soSach);
        System.out.println("Tong so luong        : " + tongSoLuong);
        System.out.printf("Tong gia tri sach    : %,.0f VND%n", tongGiaTri);
        System.out.printf("Phi van chuyen (5%%)  : %,.0f VND%n", phiVanChuyen);
        System.out.printf("Tong tien nhap       : %,.0f VND%n", tongTienNhap);
    }

    // Bai 4: Tim sach theo ma
    static void timSachTheoMa() {
        if (soSach == 0) {
            System.out.println("Danh sach trong, hay chon 1 de nhap sach");
            return;
        }

        System.out.print("Nhap ma sach can tim: ");
        String ma = sc.nextLine().trim();

        int vt = timViTri(ma);
        if (vt == -1) {
            System.out.println("Khong tim thay sach co ma: " + ma);
            return;
        }

        System.out.println();
        System.out.println("===== THONG TIN SACH TIM THAY =====");
        System.out.println("Ma sach      : " + maSach[vt]);
        System.out.println("Ten sach     : " + tenSach[vt]);
        System.out.println("Tac gia      : " + tacGia[vt]);
        System.out.println("Nam xuat ban : " + namXuatBan[vt]);
        System.out.printf("Don gia      : %,.0f VND%n", donGia[vt]);
        System.out.println("So luong     : " + soLuong[vt]);
        System.out.println("Phan loai gia: " + phanLoaiGia(donGia[vt]));
        System.out.println("Ton kho      : " + phanLoaiTonKho(soLuong[vt]));
        System.out.printf("Thanh tien   : %,.0f VND%n", donGia[vt] * soLuong[vt]);
    }

    // Tim vi tri sach theo ma bang vong lap, khong thay tra ve -1
    static int timViTri(String ma) {
        for (int i = 0; i < soSach; i++) {
            if (maSach[i].equalsIgnoreCase(ma)) {
                return i;
            }
        }
        return -1;
    }

    // Phan loai gia (tuan 4)
    static String phanLoaiGia(double gia) {
        if (gia < 50000) {
            return "Sach gia thap";
        } else if (gia <= 100000) {
            return "Sach gia trung binh";
        } else if (gia <= 200000) {
            return "Sach gia cao";
        } else {
            return "Sach gia dac biet";
        }
    }

    // Phan loai ton kho (tuan 4)
    static String phanLoaiTonKho(int sl) {
        if (sl == 0) {
            return "Het sach";
        } else if (sl <= 5) {
            return "Sap het";
        } else if (sl <= 20) {
            return "Con it";
        } else {
            return "Con nhieu";
        }
    }

    // Doc so nguyen, nhap sai thi hoi lai (khong bi loi chuong trinh)
    static int docSoNguyen(String thongBao) {
        while (true) {
            System.out.print(thongBao);
            String s = sc.nextLine().trim();
            try {
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so nguyen hop le");
            }
        }
    }

    // Doc so thuc, nhap sai thi hoi lai
    static double docSoThuc(String thongBao) {
        while (true) {
            System.out.print(thongBao);
            String s = sc.nextLine().trim();
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so hop le");
            }
        }
    }
}
