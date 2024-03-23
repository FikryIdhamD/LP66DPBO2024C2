import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;

public class Menu extends JFrame {
    public static void main(String[] args) {
        // buat object window
        Menu window = new Menu();

        // atur ukuran window
        window.setSize(480, 560);
        // letakkan window di tengah layar
        window.setLocationRelativeTo(null);
        // isi window
        window.setContentPane(window.mainPanel);
        // ubah warna background
        window.getContentPane().setBackground(Color.white);
        // tampilkan window
        window.setVisible(true);
        // agar program ikut berhenti saat window diclose
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // index baris yang diklik
    private int selectedIndex = -1;
    // list untuk menampung semua mahasiswa
    private ArrayList<Mahasiswa> listMahasiswa;

    private Database database;
    private JPanel mainPanel;
    private JTextField nimField;
    private JTextField namaField;
    private JTextField prodiField;
    private JTable mahasiswaTable;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JComboBox jenisKelaminComboBox;
    private JButton deleteButton;
    private JLabel titleLabel;
    private JLabel nimLabel;
    private JLabel namaLabel;
    private JLabel prodiLabel;
    private JLabel jenisKelaminLabel;

    // constructor
    public Menu() {
        // inisialisasi listMahasiswa
        listMahasiswa = new ArrayList<>();

        database = new Database();

        // isi tabel mahasiswa
        mahasiswaTable.setModel(setTable());

        // ubah styling title
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        // atur isi combo box
        String[] jenisKelaminData = {"", "Laki-laki", "Perempuan"};
        jenisKelaminComboBox.setModel(new DefaultComboBoxModel(jenisKelaminData));

        // sembunyikan button delete
        deleteButton.setVisible(false);

        // saat tombol add/update ditekan
        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex == -1) {
                    insertData();
                } else {
                    updateData();
                }
            }
        });
        // saat tombol delete ditekan
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex >= 0) {
                    deleteData();
                }
            }
        });
        // saat tombol cancel ditekan
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // saat tombol
                clearForm();
            }
        });
        // saat salah satu baris tabel ditekan
        mahasiswaTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // ubah selectedIndex menjadi baris tabel yang diklik
                selectedIndex = mahasiswaTable.getSelectedRow();

                // simpan value textfield dan combo box
                String selectedNim = mahasiswaTable.getModel().getValueAt(selectedIndex, 1).toString();
                String selectedNama = mahasiswaTable.getModel().getValueAt(selectedIndex, 2).toString();
                String selectedProdi = mahasiswaTable.getModel().getValueAt(selectedIndex, 3). toString();
                String selectedJenisKelamin = mahasiswaTable.getModel().getValueAt(selectedIndex, 4).toString();

                // ubah isi textfield dan combo box
                nimField.setText(selectedNim);
                namaField.setText(selectedNama);
                prodiField. setText (selectedProdi) ;
                jenisKelaminComboBox.setSelectedItem(selectedJenisKelamin);

                // ubah button "Add" menjadi "Update"
                addUpdateButton.setText("Update");
                // tampilkan button delete
                deleteButton.setVisible(true);
            }
        });
    }

    public final DefaultTableModel setTable() {
        // tentukan kolom tabel
        Object[] column = {"No", "NIM", "Nama","Prodi", "Jenis Kelamin"};

        // buat objek tabel dengan kolom yang sudah dibuat
        DefaultTableModel temp = new DefaultTableModel(null, column);

        try {
            ResultSet resultSet = database.selectQuery("SELECT * FROM mahasiswa");

            int i = 0;
            while (resultSet.next()){
                Object[] row = new Object[5];

                row[0] = i + 1;
                row[1] = resultSet.getString("nim");
                row[2] = resultSet.getString("nama");
                row[3] =  resultSet.getString("Prodi");
                row[4] = resultSet.getString("jenis_kelamin");

                temp.addRow(row);
                i++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return temp;
    }

    public boolean isNimExists(String nim) {
        try {
            ResultSet resultSet = database.selectQuery("SELECT COUNT(*) FROM mahasiswa WHERE nim = '" + nim + "'");
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean isNimExistsExcept(String nim, String id) {
        try {
            ResultSet resultSet = database.selectQuery("SELECT COUNT(*) FROM mahasiswa WHERE nim = '" + nim + "' AND id != " + id);
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void insertData() {
        // ambil value dari textfield dan combobox
        String nim = nimField.getText().trim();
        String nama = namaField.getText().trim();
        String prodi = prodiField.getText().trim();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString().trim();

        // Periksa apakah ada input yang kosong
        if (nim.isEmpty() || nama.isEmpty() || prodi.isEmpty() || jenisKelamin.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Mohon lengkapi semua field sebelum melakukan operasi.");
            return; // Hentikan proses insert jika ada input yang kosong
        }

        // Periksa apakah NIM sudah ada
        if (isNimExists(nim)) {
            JOptionPane.showMessageDialog(null, "NIM sudah ada dalam database. Silakan masukkan NIM lain.");
            return; // Jika NIM sudah ada, hentikan proses insert
        }

        // tambahkan data ke dalam database
        String sql = "INSERT INTO mahasiswa VALUES (null, '" + nim + "', '" + nama + "', '" + prodi + "', '" + jenisKelamin + "');";
        database.insertUpdateDeleteQuery(sql);

        // bersihkan form
        clearForm();
        // update tabel
        mahasiswaTable.setModel(setTable());
        // feedback
        System.out.println("Insert berhasil!");
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan");
    }

    public void updateData() {
        // ambil data dari form
        String nim = nimField.getText().trim();
        String nama = namaField.getText().trim();
        String prodi = prodiField.getText().trim();
        String jenisKelamin = jenisKelaminComboBox.getSelectedItem().toString().trim();

        // Ambil NIM dari baris yang dipilih
        String nimSelected = mahasiswaTable.getValueAt(selectedIndex, 1).toString();

        // Periksa apakah ada input yang kosong
        if (nim.isEmpty() || nama.isEmpty() || prodi.isEmpty() || jenisKelamin.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Mohon lengkapi semua field sebelum melakukan operasi.");
            return; // Hentikan proses update jika ada input yang kosong
        }

        // Periksa apakah NIM yang dimasukkan sama dengan NIM yang ada di baris yang dipilih
        if (!nim.equals(nimSelected)) {
            // Periksa apakah NIM yang dimasukkan sudah ada kecuali untuk NIM yang sedang diubah
            if (isNimExists(nim)) {
                JOptionPane.showMessageDialog(null, "NIM sudah ada dalam database. Silakan masukkan NIM lain.");
                return; // Jika NIM sudah ada, hentikan proses update
            }
        }

        // Buat query untuk update data di database berdasarkan NIM
        String sql = "UPDATE mahasiswa SET nim = '" + nim + "', nama = '" + nama + "', Prodi = '" + prodi + "', jenis_kelamin = '" + jenisKelamin + "' WHERE nim = '" + nimSelected + "'";

        // eksekusi query update di database
        database.insertUpdateDeleteQuery(sql);

        // bersihkan form
        clearForm();

        // update tabel
        mahasiswaTable.setModel(setTable());

        // feedback
        System.out.println("Update Berhasil!");
        JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
    }


    public void deleteData() {
        int confirmation = JOptionPane.showConfirmDialog(null, "Hapus Data?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (confirmation == JOptionPane.YES_OPTION) {
            // Periksa apakah baris yang dipilih valid
            if (selectedIndex >= 0) {
                // Ambil NIM dari entri yang dipilih
                String nimSelected = mahasiswaTable.getValueAt(selectedIndex, 1).toString();

                // Buat query untuk menghapus data dari database berdasarkan NIM
                String sql = "DELETE FROM mahasiswa WHERE nim = '" + nimSelected + "'";

                // Eksekusi query delete di database
                database.insertUpdateDeleteQuery(sql);

                // Feedback
                System.out.println("Delete berhasil!");
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
            } else {
                JOptionPane.showMessageDialog(null, "Pilih baris yang ingin dihapus.");
            }
        } else {
            // Jika konfirmasi tidak diterima, bersihkan form
            clearForm();
        }

        // Update tabel, baik setelah penghapusan data atau setelah membersihkan form
        mahasiswaTable.setModel(setTable());
    }


    public void clearForm() {
        // kosongkan semua texfield dan combo box
        nimField.setText("");
        namaField.setText("");
        prodiField.setText("");
        jenisKelaminComboBox.setSelectedItem("");

        // ubah button "Update" menjadi "Add"
        addUpdateButton.setText("Add");
        // sembunyikan button delete
        deleteButton.setVisible(false);
        // ubah selectedIndex menjadi -1 (tidak ada baris yang dipilih)
        selectedIndex = -1;
    }
}
