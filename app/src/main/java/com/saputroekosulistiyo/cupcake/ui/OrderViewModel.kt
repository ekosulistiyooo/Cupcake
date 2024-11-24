package com.saputroekosulistiyo.cupcake.ui

import androidx.lifecycle.ViewModel
import com.saputroekosulistiyo.cupcake.data.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/** Harga untuk satu cupcake */
private const val PRICE_PER_CUPCAKE = 2.00

/** Biaya tambahan untuk pengambilan pesanan pada hari yang sama */
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00

/**
 * [OrderViewModel] menyimpan informasi tentang pesanan cupcake dalam hal jumlah, rasa,
 * dan tanggal pengambilan. ViewModel ini juga mengetahui cara menghitung total harga
 * berdasarkan detail pesanan tersebut.
 */
class OrderViewModel : ViewModel() {

    /**
     * Status pesanan cupcake untuk pesanan ini
     */
    private val _uiState = MutableStateFlow(OrderUiState(pickupOptions = pickupOptions()))
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    /**
     * Mengatur jumlah [numberCupcakes] cupcake untuk status pesanan ini dan memperbarui harga
     */
    fun setQuantity(numberCupcakes: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                quantity = numberCupcakes,
                price = calculatePrice(quantity = numberCupcakes)
            )
        }
    }

    /**
     * Mengatur rasa [desiredFlavor] cupcake untuk status pesanan ini.
     * Hanya satu rasa yang dapat dipilih untuk seluruh pesanan.
     */
    fun setFlavor(desiredFlavor: String) {
        _uiState.update { currentState ->
            currentState.copy(flavor = desiredFlavor)
        }
    }

    /**
     * Mengatur [pickupDate] untuk status pesanan ini dan memperbarui harga
     */
    fun setDate(pickupDate: String) {
        _uiState.update { currentState ->
            currentState.copy(
                date = pickupDate,
                price = calculatePrice(pickupDate = pickupDate)
            )
        }
    }

    /**
     * Mengatur ulang status pesanan
     */
    fun resetOrder() {
        _uiState.value = OrderUiState(pickupOptions = pickupOptions())
    }

    /**
     * Mengembalikan harga yang dihitung berdasarkan detail pesanan.
     */
    private fun calculatePrice(
        quantity: Int = _uiState.value.quantity,
        pickupDate: String = _uiState.value.date
    ): String {
        var calculatedPrice = quantity * PRICE_PER_CUPCAKE
        // Jika pengguna memilih opsi pertama (hari ini) untuk pengambilan, tambahkan biaya tambahan
        if (pickupOptions()[0] == pickupDate) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        val formattedPrice = NumberFormat.getCurrencyInstance().format(calculatedPrice)
        return formattedPrice
    }

    /**
     * Mengembalikan daftar opsi tanggal mulai dari tanggal saat ini dan 3 tanggal berikutnya.
     */
    private fun pickupOptions(): List<String> {
        val dateOptions = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        // Tambahkan tanggal saat ini dan 3 tanggal berikutnya.
        repeat(4) {
            dateOptions.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return dateOptions
    }
}

