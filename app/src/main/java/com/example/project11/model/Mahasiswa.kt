package com.example.project11.model

data class Mahasiswa(
    val nim: String,
    val nama: String,
    val alamat: String,
    val jenisKelamin: String,
    val kelas: String,
    val angkatan: String,
    val JudulSkripsi: String,
    val DosenPembimbing1: String,
    val DosenPembimbing2: String
){
    constructor() : this("", "", "", "", "", "", "","","")
}