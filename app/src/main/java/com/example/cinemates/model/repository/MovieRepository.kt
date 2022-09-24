package com.example.cinemates.model.repository

import com.example.cinemates.model.api.MovieService
import com.example.cinemates.model.data.*
import com.example.cinemates.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject

private const val TAG = "MovieRepository"

/**
 * @author Antonio Di Nuzzo
 * Created 21/04/2022 at 15:46
 */
class MovieRepository
@Inject
constructor(private val apiService: MovieService) {

    companion object {
        private lateinit var sMap: HashMap<String, String>
    }

    init {
        sMap = HashMap()
        sMap["language"] =
            Constants.DEFAULT_SYSTEM_LANGUAGE
        sMap["append_to_response"] = "images"
        sMap["include_image_language"] =
            Constants.DEFAULT_SYSTEM_LANGUAGE
        sMap["page"] = "1"
    }

    fun getPopularMovies(): Flow<List<Movie>> = flow {
        val popularMovies = apiService.getPopular(sMap).body()?.results ?: listOf()
        emit(popularMovies)
    }


    fun getTopRatedMovies(): Flow<List<Movie>> = flow {
        val topRatedMovies = apiService.getTopRated(sMap).body()?.results ?: listOf()
        emit(topRatedMovies)
    }

    fun getUpcomingMovies(): Flow<List<Movie>> = flow {
        val upcomingMovies = apiService.getUpcoming(sMap).body()?.results ?: listOf()
        emit(upcomingMovies)
    }

    suspend fun getCurrentlyShowingMovies() = apiService.getCurrentlyShowing(sMap)

    suspend fun getGenreList() = apiService.getGenreList(sMap)

    fun getTrendingMovies(mediaType: String, timeWindow: String): Flow<List<Movie>> = flow {
        val movies =
            apiService.getTrendingMovies(mediaType, timeWindow, sMap).body()?.results ?: listOf()
        emit(movies)
    }


    fun getTrendingPerson(mediaType: String, timeWindow: String): Flow<List<Person>> = flow {
        val persons =
            apiService.getTrendingPerson(mediaType, timeWindow, sMap).body()?.results ?: listOf()
        emit(persons)
    }


    fun getVideos(movieId: Int): Flow<List<Video>> = flow {
        val videos = apiService.getVideos(movieId, sMap).body()?.results ?: listOf()
        emit(videos)
    }

    fun getMovieDetails(movieId: Int): Flow<Movie> = flow {
        val response = apiService.getMovieDetails(movieId, sMap)
        if (response.isSuccessful)
            response.body()?.let { movie ->
                emit(movie)
            }
    }


    fun getSimilarMovies(movieId: Int): Flow<List<Movie>> = flow {
        val similarMovies = apiService.getSimilar(movieId, sMap).body()?.results ?: listOf()
        emit(similarMovies)
    }

    fun getDiscoverableMovies(filter: Filter): Flow<List<Movie>> = flow {
        sMap["sort_by"] = filter.sortBy.toString()
        sMap["with_genres"] = filter.withGenres ?: ""
        val movies = apiService.getMoviesByDiscover(sMap).body()?.results ?: listOf()
        emit(movies)
    }


    suspend fun getReviews(movieId: Int) = apiService.getReviews(movieId, sMap)


    fun getImages(movieId: Int) = flow {
        val images = apiService.getImages(movieId, sMap).body()
        emit(images)
    }


    fun getMovieCredits(movieId: Int): Flow<CreditsResponse> = flow {
        val credits = apiService.getMovieCredits(movieId, sMap).body()
        if (credits != null)
            emit(credits)
    }


    fun getActorDetails(personId: Int): Flow<Person> = flow {
        val response = apiService.getActorDetails(personId, sMap)
        if (response.isSuccessful) {
            response.body()?.let { actor ->
                emit(actor)
            }
        }
    }


    fun getCollection(collectionId: Int): Flow<List<Movie>> = flow {
        val collection = apiService.getCollection(collectionId, sMap).body()?.parts
        if (collection != null)
            emit(collection)
    }


    suspend fun getMoviesBySearch(query: String): Response<GenericResponse<Movie>> {
        sMap["query"] = query
        return apiService.getMoviesBySearch(sMap)
    }

    suspend fun getMoviesByActor(with_cast: String): Response<GenericResponse<Movie>> {
        sMap["with_cast"] = with_cast
        return apiService.getMoviesByDiscover(sMap)
    }

    suspend fun getPeoplesBySearch(query: String): Response<GenericResponse<Cast>> {
        sMap["query"] = query
        return apiService.getPeoplesBySearch(sMap)
    }


}

