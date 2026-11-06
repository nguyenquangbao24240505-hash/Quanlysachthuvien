import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

public class BTT56 {

    // ================= TUAN 5 =================
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

    // ================= TUAN 6 =================
    // Bai 2: ArrayList luu ma sach
    static ArrayList<String> dsMaSach = new ArrayList<>();

    // Bai 3, 4: moi phan tu la 1 cuon sach, dang "ma|ten|tac gia|nam|don gia|so luong"
    static ArrayList<String> danhSachSach = new ArrayList<>();

    public static void main(String[] args) {
        int luaChon;

        // Menu lap den khi chon Thoat
        do {
            System.out.println();
            System.out.println("========== QUAN LY SACH THU VIEN ==========");
            System.out.println("--- Tuan 5 (mang) ---");
            System.out.println("1. Nhap n sach");
            System.out.println("2. Hien thi danh sach sach");
            System.out.println("3. Tinh tong so luong va tong gia tri");
            System.out.println("4. Tim sach theo ma");
            System.out.println("--- Tuan 6 (mang va ArrayList) ---");
            System.out.println("5. Bai 1: Mang so luong 5 cuon sach");
            System.out.println("6. Bai 2: ArrayList ma sach");
            System.out.println("7. Bai 3, 4: Quan ly sach bang ArrayList (menu hoan chinh)");
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
                case 5:
                    tuan6Bai1();
                    break;
                case 6:
                    tuan6Bai2();
                    break;
                case 7:
                    tuan6Bai4();
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

    // =====================================================================
    //                              TUAN 5
    // =====================================================================

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

    // =====================================================================
    //                              TUAN 6
    // =====================================================================

    // Bai 1: Mang int[] so luong cua 5 cuon sach
    static void tuan6Bai1() {
        int[] soLuongMang = new int[5];   // de bai goi la int[] soLuong

        System.out.println();
        System.out.println("===== BAI 1: MANG SO LUONG 5 CUON SACH =====");
        for (int i = 0; i < soLuongMang.length; i++) {
            int sl = docSoNguyen("Nhap so luong cuon sach thu " + (i + 1) + ": ");
            while (sl < 0) {
                System.out.println("So luong phai lon hon hoac bang 0");
                sl = docSoNguyen("Nhap lai so luong cuon sach thu " + (i + 1) + ": ");
            }
            soLuongMang[i] = sl;
        }

        // Hien thi so luong
        System.out.println();
        System.out.println("So luong cac cuon sach:");
        for (int i = 0; i < soLuongMang.length; i++) {
            System.out.println("  Cuon " + (i + 1) + ": " + soLuongMang[i]);
        }

        // Tinh tong
        int tong = 0;
        for (int i = 0; i < soLuongMang.length; i++) {
            tong += soLuongMang[i];
        }

        // Tim so luong lon nhat
        int max = soLuongMang[0];
        int viTriMax = 0;
        for (int i = 1; i < soLuongMang.length; i++) {
            if (soLuongMang[i] > max) {
                max = soLuongMang[i];
                viTriMax = i;
            }
        }

        System.out.println("Tong so luong      : " + tong);
        System.out.println("So luong lon nhat  : " + max + " (cuon thu " + (viTriMax + 1) + ")");
    }

    // Bai 2: ArrayList<String> luu ma sach: them, hien thi, tim, xoa
    static void tuan6Bai2() {
        int chon;
        do {
            System.out.println();
            System.out.println("===== BAI 2: ARRAYLIST MA SACH =====");
            System.out.println("1. Them ma sach");
            System.out.println("2. Hien thi ma sach");
            System.out.println("3. Tim ma sach");
            System.out.println("4. Xoa ma sach");
            System.out.println("0. Quay lai menu chinh");
            chon = docSoNguyen("Nhap lua chon: ");

            switch (chon) {
                case 1:
                    System.out.print("Nhap ma sach can them: ");
                    String ma = sc.nextLine().trim();
                    if (ma.isEmpty()) {
                        System.out.println("Ma sach khong duoc de trong");
                    } else if (dsMaSach.contains(ma)) {
                        System.out.println("Ma sach da ton tai");
                    } else {
                        dsMaSach.add(ma);
                        System.out.println("Da them. So ma sach hien co: " + dsMaSach.size());
                    }
                    break;
                case 2:
                    if (dsMaSach.isEmpty()) {
                        System.out.println("Danh sach ma sach trong");
                    } else {
                        System.out.println("Danh sach ma sach (" + dsMaSach.size() + "):");
                        for (int i = 0; i < dsMaSach.size(); i++) {
                            System.out.println("  " + (i + 1) + ". " + dsMaSach.get(i));
                        }
                    }
                    break;
                case 3:
                    System.out.print("Nhap ma sach can tim: ");
                    String tim = sc.nextLine().trim();
                    int vt = dsMaSach.indexOf(tim);
                    if (vt == -1) {
                        System.out.println("Khong tim thay ma: " + tim);
                    } else {
                        System.out.println("Tim thay ma " + tim + " o vi tri thu " + (vt + 1));
                    }
                    break;
                case 4:
                    System.out.print("Nhap ma sach can xoa: ");
                    String xoa = sc.nextLine().trim();
                    if (dsMaSach.remove(xoa)) {
                        System.out.println("Da xoa ma: " + xoa);
                    } else {
                        System.out.println("Khong tim thay ma: " + xoa);
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Lua chon khong hop le");
                    break;
            }
        } while (chon != 0);
    }

    // Bai 3, 4: ArrayList<String> danhSachSach, menu hoan chinh
    static void tuan6Bai4() {
        int chon;
        do {
            System.out.println();
            System.out.println("===== QUAN LY SACH BANG ARRAYLIST =====");
            System.out.println("1. Them sach");
            System.out.println("2. Hien thi sach");
            System.out.println("3. Tim sach");
            System.out.println("4. Xoa sach");
            System.out.println("5. Sua sach");
            System.out.println("6. Tinh tong so luong");
            System.out.println("7. Tinh tong gia tri");
            System.out.println("8. Thoat (quay lai menu chinh)");
            chon = docSoNguyen("Nhap lua chon: ");

            switch (chon) {
                case 1:
                    themSach();
                    break;
                case 2:
                    hienThiSach();
                    break;
                case 3:
                    timSach();
                    break;
                case 4:
                    xoaSach();
                    break;
                case 5:
                    suaSach();
                    break;
                case 6:
                    tongSoLuong();
                    break;
                case 7:
                    tongGiaTri();
                    break;
                case 8:
                    System.out.println("Quay lai menu chinh");
                    break;
                default:
                    System.out.println("Lua chon khong hop le, vui long chon lai");
                    break;
            }
        } while (chon != 8);
    }

    // Them sach vao ArrayList
    static void themSach() {
        int namHienTai = Year.now().getValue();

        String ma;
        while (true) {
            ma = docChuoi("Nhap ma sach: ");
            if (ma.isEmpty()) {
                System.out.println("Ma sach khong duoc de trong");
            } else if (timViTriDS(ma) != -1) {
                System.out.println("Ma sach da ton tai, nhap ma khac");
            } else {
                break;
            }
        }

        String ten = docChuoi("Nhap ten sach: ");
        String tg = docChuoi("Nhap tac gia: ");

        int nam = docSoNguyen("Nhap nam xuat ban: ");
        while (nam < 1900 || nam > namHienTai) {
            System.out.println("Nam xuat ban phai tu 1900 den " + namHienTai);
            nam = docSoNguyen("Nhap lai nam xuat ban: ");
        }

        double gia = docSoThuc("Nhap don gia (VND): ");
        while (gia <= 0) {
            System.out.println("Don gia phai lon hon 0");
            gia = docSoThuc("Nhap lai don gia (VND): ");
        }

        int sl = docSoNguyen("Nhap so luong: ");
        while (sl < 0) {
            System.out.println("So luong phai lon hon hoac bang 0");
            sl = docSoNguyen("Nhap lai so luong: ");
        }

        danhSachSach.add(ghepSach(ma, ten, tg, nam, gia, sl));
        System.out.println("Da them sach. Tong so sach hien co: " + danhSachSach.size());
    }

    // Hien thi toan bo sach
    static void hienThiSach() {
        if (danhSachSach.isEmpty()) {
            System.out.println("Danh sach trong, hay chon 1 de them sach");
            return;
        }

        System.out.println();
        System.out.println("===== DANH SACH SACH (" + danhSachSach.size() + " cuon) =====");
        inTieuDe();
        for (int i = 0; i < danhSachSach.size(); i++) {
            inDong(i);
        }
    }

    // Tim sach theo ma hoac theo ten (co chua tu khoa)
    static void timSach() {
        if (danhSachSach.isEmpty()) {
            System.out.println("Danh sach trong, hay chon 1 de them sach");
            return;
        }

        String tuKhoa = docChuoi("Nhap ma hoac ten sach can tim: ");
        if (tuKhoa.isEmpty()) {
            System.out.println("Chua nhap tu khoa");
            return;
        }

        int dem = 0;
        for (int i = 0; i < danhSachSach.size(); i++) {
            String[] t = tachSach(danhSachSach.get(i));
            boolean khop = t[0].equalsIgnoreCase(tuKhoa)
                    || t[1].toLowerCase().contains(tuKhoa.toLowerCase());
            if (khop) {
                if (dem == 0) {
                    System.out.println();
                    System.out.println("===== KET QUA TIM KIEM =====");
                    inTieuDe();
                }
                inDong(i);
                dem++;
            }
        }

        if (dem == 0) {
            System.out.println("Khong tim thay sach nao voi tu khoa: " + tuKhoa);
        } else {
            System.out.println("Tim thay " + dem + " sach");
        }
    }

    // Xoa sach theo ma
    static void xoaSach() {
        if (danhSachSach.isEmpty()) {
            System.out.println("Danh sach trong, hay chon 1 de them sach");
            return;
        }

        String ma = docChuoi("Nhap ma sach can xoa: ");
        int vt = timViTriDS(ma);
        if (vt == -1) {
            System.out.println("Khong tim thay sach co ma: " + ma);
            return;
        }

        String[] t = tachSach(danhSachSach.get(vt));
        danhSachSach.remove(vt);
        System.out.println("Da xoa sach: " + t[0] + " - " + t[1]);
        System.out.println("Con lai " + danhSachSach.size() + " sach");
    }

    // Sua sach theo ma (de trong de giu nguyen gia tri cu)
    static void suaSach() {
        if (danhSachSach.isEmpty()) {
            System.out.println("Danh sach trong, hay chon 1 de them sach");
            return;
        }

        String ma = docChuoi("Nhap ma sach can sua: ");
        int vt = timViTriDS(ma);
        if (vt == -1) {
            System.out.println("Khong tim thay sach co ma: " + ma);
            return;
        }

        String[] t = tachSach(danhSachSach.get(vt));
        int namHienTai = Year.now().getValue();

        System.out.println("Sua sach " + t[0] + " (nhan Enter de giu nguyen gia tri cu)");

        String ten = docChuoi("Ten sach moi [" + t[1] + "]: ");
        if (ten.isEmpty()) {
            ten = t[1];
        }

        String tg = docChuoi("Tac gia moi [" + t[2] + "]: ");
        if (tg.isEmpty()) {
            tg = t[2];
        }

        int nam = Integer.parseInt(t[3]);
        while (true) {
            String s = docChuoi("Nam xuat ban moi [" + t[3] + "]: ");
            if (s.isEmpty()) {
                break;
            }
            try {
                int v = Integer.parseInt(s);
                if (v < 1900 || v > namHienTai) {
                    System.out.println("Nam xuat ban phai tu 1900 den " + namHienTai);
                } else {
                    nam = v;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so nguyen hop le");
            }
        }

        double gia = Double.parseDouble(t[4]);
        while (true) {
            String s = docChuoi("Don gia moi [" + String.format("%,.0f", gia) + "]: ");
            if (s.isEmpty()) {
                break;
            }
            try {
                double v = Double.parseDouble(s);
                if (v <= 0) {
                    System.out.println("Don gia phai lon hon 0");
                } else {
                    gia = v;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so hop le");
            }
        }

        int sl = Integer.parseInt(t[5]);
        while (true) {
            String s = docChuoi("So luong moi [" + t[5] + "]: ");
            if (s.isEmpty()) {
                break;
            }
            try {
                int v = Integer.parseInt(s);
                if (v < 0) {
                    System.out.println("So luong phai lon hon hoac bang 0");
                } else {
                    sl = v;
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui long nhap so nguyen hop le");
            }
        }

        danhSachSach.set(vt, ghepSach(t[0], ten, tg, nam, gia, sl));
        System.out.println("Da cap nhat sach " + t[0]);
    }

    // Tinh tong so luong
    static void tongSoLuong() {
        if (danhSachSach.isEmpty()) {
            System.out.println("Danh sach trong, hay chon 1 de them sach");
            return;
        }

        int tong = 0;
        for (int i = 0; i < danhSachSach.size(); i++) {
            tong += Integer.parseInt(tachSach(danhSachSach.get(i))[5]);
        }
        System.out.println("So dau sach   : " + danhSachSach.size());
        System.out.println("Tong so luong : " + tong);
    }

    // Tinh tong gia tri = tong (don gia x so luong)
    static void tongGiaTri() {
        if (danhSachSach.isEmpty()) {
            System.out.println("Danh sach trong, hay chon 1 de them sach");
            return;
        }

        double tong = 0;
        for (int i = 0; i < danhSachSach.size(); i++) {
            String[] t = tachSach(danhSachSach.get(i));
            tong += Double.parseDouble(t[4]) * Integer.parseInt(t[5]);
        }
        System.out.println("So dau sach   : " + danhSachSach.size());
        System.out.printf("Tong gia tri  : %,.0f VND%n", tong);
    }

    // ---------- Ham ho tro cho ArrayList sach ----------

    // Ghep thong tin 1 cuon sach thanh 1 chuoi de luu vao ArrayList<String>
    static String ghepSach(String ma, String ten, String tg, int nam, double gia, int sl) {
        return ma + "|" + ten + "|" + tg + "|" + nam + "|" + gia + "|" + sl;
    }

    // Tach chuoi thanh 6 phan: ma, ten, tac gia, nam, don gia, so luong
    static String[] tachSach(String s) {
        return s.split("\\|", -1);
    }

    // Tim vi tri sach trong ArrayList theo ma, khong thay tra ve -1
    static int timViTriDS(String ma) {
        for (int i = 0; i < danhSachSach.size(); i++) {
            if (tachSach(danhSachSach.get(i))[0].equalsIgnoreCase(ma)) {
                return i;
            }
        }
        return -1;
    }

    // Doc 1 dong chu, bo ky tu | de khong lam hong cach luu chuoi
    static String docChuoi(String thongBao) {
        System.out.print(thongBao);
        return sc.nextLine().trim().replace("|", " ");
    }

    static void inTieuDe() {
        System.out.printf("%-4s %-10s %-22s %-18s %-6s %-14s %-8s%n",
                "STT", "Ma", "Ten sach", "Tac gia", "Nam", "Don gia", "SL");
    }

    static void inDong(int i) {
        String[] t = tachSach(danhSachSach.get(i));
        System.out.printf("%-4d %-10s %-22s %-18s %-6s %,-14.0f %-8s%n",
                i + 1, t[0], t[1], t[2], t[3], Double.parseDouble(t[4]), t[5]);
    }
}