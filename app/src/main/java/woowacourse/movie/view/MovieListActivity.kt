package woowacourse.movie.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.Movie
import woowacourse.movie.R
import woowacourse.movie.repository.MovieMockRepository
import woowacourse.movie.view.mapper.toUiModel
import woowacourse.movie.view.model.MovieListModel

class MovieListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_list)

        val movies = MovieMockRepository.findAll()
        val dataList = generateMovieListData(movies)

        val movieAdapter = MovieListAdapter(
            dataList,
            object : MovieListAdapter.OnReserveListener {
                override fun onClick(movie: MovieListModel.MovieUiModel) {
                    val intent = Intent(this@MovieListActivity, ReservationActivity::class.java)
                    intent.putExtra(MOVIE_ITEM, movie)
                    startActivity(intent)
                }
            }
        )
        val movieListView = findViewById<RecyclerView>(R.id.movie_recyclerview)
        movieListView.adapter = movieAdapter
    }

    private fun generateMovieListData(movies: List<Movie>): List<MovieListModel> {
        val dataList = mutableListOf<MovieListModel>()
        val ad = MovieListModel.MovieAdModel(
            R.drawable.woowacourse_banner
        )
        movies.forEachIndexed { index, movie ->
            if (index % 3 == 2) {
                dataList.add(movie.toUiModel())
                dataList.add(ad)
                return@forEachIndexed
            }
            dataList.add(movie.toUiModel())
        }
        return dataList
    }

    companion object {
        const val MOVIE_ITEM = "MOVIE_ITEM"
    }
}
