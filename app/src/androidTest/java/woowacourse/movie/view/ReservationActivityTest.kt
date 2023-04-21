package woowacourse.movie.view

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import woowacourse.movie.R
import woowacourse.movie.view.model.MovieListModel
import java.time.LocalDate

@RunWith(AndroidJUnit4::class)
class ReservationActivityTest {

    private val movie = MovieListModel.MovieUiModel(
        "해리 포터와 마법사의 돌",
        LocalDate.of(2024, 3, 1),
        LocalDate.of(2024, 3, 31),
        152,
        R.drawable.harry_potter1_poster,
        "《해리 포터와 마법사의 돌》은 2001년 J. K. 롤링의 동명 소설을 원작으로 하여 만든, 영국과 미국 합작, 판타지 영화이다. 해리포터 시리즈 영화 8부작 중 첫 번째에 해당하는 작품이다. 크리스 콜럼버스가 감독을 맡았다."
    )

    private val intent = Intent(
        ApplicationProvider.getApplicationContext(),
        ReservationActivity::class.java
    ).apply {
        putExtra("MOVIE_ITEM", movie)
    }

    @get:Rule
    val activityRule = ActivityScenarioRule<ReservationActivity>(intent)

    @Test
    fun movieTitle() {
        onView(withId(R.id.movie_title)).check(matches(withText("해리 포터와 마법사의 돌")))
    }

    @Test
    fun initialPeopleCount() {
        onView(withId(R.id.people_count)).check(matches(withText("1")))
    }

    @Test
    fun plusPeopleCount() {
        onView(withId(R.id.plus_button)).perform(click())
        onView(withId(R.id.people_count)).check(matches(withText("2")))
    }

    @Test
    fun minusButtonWhenPeopleCountIsOne() {
        onView(withId(R.id.minus_button)).perform(click())
        onView(withId(R.id.people_count)).check(matches(withText("1")))
    }
}
