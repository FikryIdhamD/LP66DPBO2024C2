# TUGASPRAKTIKUM2DPBO2023
Saya Fikry Idham Dwiyana NIM 2101294 mengerjakan Tugas Praktikum 2 dalam mata kuliah Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

## Tugas:
* Lanjutkan program LP5 yang sudah kamu buat, lalu tambahkan koneksi dengan database MySQL. Ubah program dengan spesifikasi sebagai berikut:
* Hubungkan semua proses CRUD dengan database.
* Tampilkan dialog/prompt error jika masih ada input yang kosong saat insert/update.
* Tampilkan dialog/prompt error jika sudah ada NIM yang sama saat insert.


## Desain Program
Program didesain menjadi 2 class dengan 1 class utama yaitu class `Mahasiswa`:
* **Mahasiswa** -> Class yang datanya akan diisi pada GUI
* **Menu** -> Class yang berisi program untuk GUI

Pada class `Mahasiswa` terdapat lima atribut:
* **nim** -> NIM mahasiswa, `String`
* **nama** -> Nama mahasiswa, `String`
* **prodi** -> Prodi mahasiswa, `String`
* **jKelamin** -> Jenis kelamin mahasiswa, `String`

Pada class `Menu` berisi method-method untuk GUI:
* **Set Tabel** -> Men-set Tabel untuk ditampilkan pada GUI, `DefaultTableModel`
* **Insert Data** -> Menambah data, `void`
* **Update Data** -> Mengubah data yang dipilih, `void`
* **Delete Data** -> Menghapus data yang dipilih, `void`
* **Reset Form** -> Mereset form menjadi default, `void`
* Selain dari method di atas, terdapat juga method-method untuk setting GUInya

## Desain GUI
![Design](https://github.com/FikryIdhamD/LP5DPBO2024C2/assets/147605722/8f4355da-e2f0-4daa-bc71-d433d5b394b0)


## Alur Program GUI
Terdapat textfield untuk mengisi data nim, nama dan prodi. JComboBox untuk mengisi data jenis kelamin. Jika semua data sudah diisi dan klik button `add` maka akan menambahkan data serta data akan ditampilkan pada tabel dan data pada field juga akan direset ke default (kosong). Jika user sedang mengisi data dan klik tombol `cancel` maka akan mengosongkan semua data yang sebelumnya sedang diisi (bukan data pada tabel). Jika user klik salah satu data pada tabel, data akan otomatis terisi pada field untuk mengisi data, sesuai dengan data yang dipilih, serta user dapat mengubah atau menghapus data tesebut. Jika user mengubah salah satu data dan klik tombol `update` maka data pada tabel akan berubah. Jika user klik tombol `delete` maka akan muncul kotak dialog untuk konfirmasi dan jika diklik `Yes` maka data akan dihapus.

## Dokumentasi
**Jar File**

![Jar File](https://github.com/FikryIdhamD/LP5DPBO2024C2/assets/147605722/4cec7f23-cdc4-4156-87d8-7d9f9023170a)

**Tambah Data**

![Add_Data1](https://github.com/FikryIdhamD/LP66DPBO2024C2/assets/147605722/9aea4edb-a6a5-4d75-8810-89231da3fa41)
![Add_Data2](https://github.com/FikryIdhamD/LP5DPBO2024C2/assets/147605722/638ac153-2de9-4f8a-b7d0-23aeaf704ba6)

**Tambah Data(Jika Kosong)**

![Add_Data_Kosong](https://github.com/FikryIdhamD/LP66DPBO2024C2/assets/147605722/ad49be41-1907-4b44-968b-08a1d65b50f0)

**Tambah Data(Jika NIM Sudah ada)**

![Add_Data_Sudah_Ada](https://github.com/FikryIdhamD/LP66DPBO2024C2/assets/147605722/f59e4a35-22d3-4c0b-b4db-5372ebc77f69)

**Ubah Data**

![Update_Data1](https://github.com/FikryIdhamD/LP66DPBO2024C2/assets/147605722/0b6a92ed-3d45-4625-b99c-ef4c02d11bfa)
![Update_Data2](https://github.com/FikryIdhamD/LP5DPBO2024C2/assets/147605722/ba3a14d3-d562-4cf5-a3c5-f5006c2b19d1)

**Ubah Data(Jika Kosong)**

![Update_Data_Kosong](https://github.com/FikryIdhamD/LP66DPBO2024C2/assets/147605722/cc6f2305-d9b5-44ae-baf8-44ef7549fa72)

**Ubah Data(Jika NIM Sudah ada)**

![Update_Data_Sudah_Ada](https://github.com/FikryIdhamD/LP66DPBO2024C2/assets/147605722/1ef1e4f3-47ed-4f3b-96e7-a0dd3c9972cd)

**Hapus Data**

![Hapus_Data1](https://github.com/FikryIdhamD/LP66DPBO2024C2/assets/147605722/2859770b-c990-4c9c-95ea-514cca2cf145)
![Hapus_Data2](https://github.com/FikryIdhamD/LP66DPBO2024C2/assets/147605722/6c4dd151-67f2-4f24-bc82-8296169a7f15)


