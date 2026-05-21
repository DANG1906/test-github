package Admin;

import DAO.NhanvienDAO;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ThongkeNhanvien extends JPanel {

    private JLabel lbTong, lbNam, lbNu;
    private JTable tablePB;
    private JTable tableCV;
    private NhanvienDAO dao = new NhanvienDAO();

    public ThongkeNhanvien() {
        setLayout(new BorderLayout(10, 10));
        setBackground(Color.WHITE);

        JLabel title = new JLabel("THỐNG KÊ NHÂN VIÊN", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        // ===== THỐNG KÊ TỔNG =====
        JPanel pnTop = new JPanel(new GridLayout(1, 3, 15, 15));
        pnTop.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        lbTong = createBox("Tổng nhân viên", dao.countAll());
        lbNam = createBox("Nam", dao.countByGender("Nam"));
        lbNu = createBox("Nữ", dao.countByGender("Nữ"));

        pnTop.add(lbTong);
        pnTop.add(lbNam);
        pnTop.add(lbNu);

        add(pnTop, BorderLayout.CENTER);

        // ===== BẢNG THỐNG KÊ =====
        JPanel pnBottom = new JPanel(new GridLayout(1, 2, 10, 10));

        tablePB = createTable("PHÒNG BAN", dao.countByPhongBan());
        tableCV = createTable("CHỨC VỤ", dao.countByChucVu());

        pnBottom.add(new JScrollPane(tablePB));
        pnBottom.add(new JScrollPane(tableCV));

        add(pnBottom, BorderLayout.SOUTH);
    }

    private JLabel createBox(String title, int value) {
        JLabel lb = new JLabel(
                "<html><center>" + title + "<br><b style='font-size:24px'>" + value + "</b></center></html>",
                JLabel.CENTER
        );
        lb.setOpaque(true);
        lb.setBackground(new Color(230, 240, 255));
        lb.setFont(new Font("Arial", Font.PLAIN, 16));
        lb.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        return lb;
    }

    private JTable createTable(String title, Map<String, Integer> data) {
        String[] col = {title, "Số lượng"};
        Object[][] rows = new Object[data.size()][2];

        int i = 0;
        for (String key : data.keySet()) {
            rows[i][0] = key;
            rows[i][1] = data.get(key);
            i++;
        }

        JTable tb = new JTable(rows, col);
        tb.setRowHeight(28);
        tb.setEnabled(false);
        return tb;
    }
}
