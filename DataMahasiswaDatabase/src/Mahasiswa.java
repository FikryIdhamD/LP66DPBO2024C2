public class Mahasiswa {
    private String nim;
    private String nama;
    private String prodi;
    private String jenisKelamin;


    public Mahasiswa(String nim, String nama, String prodi, String jenisKelamin) {
        this.nim = nim;
        this.nama = nama;
        this.prodi = prodi;
        this.jenisKelamin = jenisKelamin;

    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    public String getNim() {
        return this.nim;
    }

    public String getNama() {
        return this.nama;
    }

    public String getJenisKelamin() {
        return this.jenisKelamin;
    }

    public String getProdi() {
        return this.prodi;
    }
}
