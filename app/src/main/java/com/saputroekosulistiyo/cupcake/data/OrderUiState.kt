package com.saputroekosulistiyo.cupcake.data

/**
 * Kelas data yang merepresentasikan status UI saat ini dalam hal [quantity], [flavor],
 * [dateOptions], tanggal pengambilan yang dipilih [date], dan [price]
 */
data class OrderUiState(
    /** Jumlah cupcake yang dipilih (1, 6, 12) */
    val quantity: Int = 0,
    /** Rasa cupcake dalam pesanan (seperti "Cokelat", "Vanilla", dll.) */
    val flavor: String = "",
    /** Tanggal pengambilan yang dipilih (seperti "1 Jan") */
    val date: String = "",
    /** Total harga untuk pesanan */
    val price: String = "",
    /** Tanggal pengambilan yang tersedia untuk pesanan */
    val pickupOptions: List<String> = listOf()
)

