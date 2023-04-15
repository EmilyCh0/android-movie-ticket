package woowacourse.movie.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import woowacourse.movie.R
import woowacourse.movie.databinding.ActivityReservationCompletedBinding
import woowacourse.movie.util.getParcelable
import java.text.DecimalFormat

class ReservationCompletedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReservationCompletedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReservationCompletedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val reservation = intent.getParcelable<ReservationUiModel>(ReservationActivity.RESERVATION)
        requireNotNull(reservation) { "인텐트로 받아온 데이터가 널일 수 없습니다." }
        initViewData(reservation)
    }

    private fun initViewData(reservation: ReservationUiModel) {
        binding.apply {
            movieTitle.text = reservation.title
            movieScreeningDate.text = reservation.screeningDateTime.format(DATE_TIME_FORMATTER)
            peopleCount.text = getString(R.string.reservation_people_count_format)
                .format(getString(R.string.general_person), reservation.peopleCount)
            totalPrice.text =
                getString(R.string.total_price_format).format(DECIMAL_FORMAT.format(reservation.totalReservationFee))
        }
    }

    companion object {
        private val DECIMAL_FORMAT = DecimalFormat("#,###")
    }
}
