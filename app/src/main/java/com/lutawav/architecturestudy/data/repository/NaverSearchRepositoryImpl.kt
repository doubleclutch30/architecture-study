package com.lutawav.architecturestudy.data.repository

import com.lutawav.architecturestudy.data.database.entity.BlogEntity
import com.lutawav.architecturestudy.data.database.entity.ImageEntity
import com.lutawav.architecturestudy.data.database.entity.MovieEntity
import com.lutawav.architecturestudy.data.model.*
import com.lutawav.architecturestudy.data.source.local.NaverSearchLocalDataSource
import com.lutawav.architecturestudy.data.source.remote.NaverSearchRemoteDataSource
import io.reactivex.Completable
import io.reactivex.Single

class NaverSearchRepositoryImpl(
    private val naverSearchRemoteDataSource: NaverSearchRemoteDataSource,
    private val naverSearchLocalDataSource: NaverSearchLocalDataSource
) : NaverSearchRepository {

    override fun getMovie(
        keyword: String
    ): Single<ResponseMovie> =
        naverSearchRemoteDataSource.getMovie(
            keyword = keyword
        )

    override fun getBlog(
        keyword: String
    ): Single<ResponseBlog> =
        naverSearchRemoteDataSource.getBlog(
            keyword = keyword
        )

    override fun getImage(
        keyword: String
    ): Single<ResponseImage> =
        naverSearchRemoteDataSource.getImage(
            keyword = keyword
        )

    override fun getLatestMovieResult(): Single<List<Movie>> =
        naverSearchLocalDataSource.getMovie()

    override fun getLatestImageResult(): Single<List<Image>> =
        naverSearchLocalDataSource.getImage()

    override fun getLatestBlogResult(): Single<List<Blog>> =
        naverSearchLocalDataSource.getBlog()

    override fun refreshMovieSearchHistory(
        keyword: String,
        movies: List<Movie>
    ): Single<List<Movie>> =
        if (movies.isEmpty()) {
            updateSearchHistory(
                fun1 = { naverSearchLocalDataSource.clearMovieResult() },
                fun2 = { naverSearchLocalDataSource.saveMovieKeyword(keyword) }
            )
                .toSingle { movies }
        } else {
            //
            val movieList = ensureMovieEntityList(movies)
            updateSearchHistory(
                fun1 = { naverSearchLocalDataSource.clearMovieResult() },
                fun2 = { naverSearchLocalDataSource.saveMovieKeyword(keyword) },
                fun3 = { naverSearchLocalDataSource.saveMovieResult(movieList) }
            )
                .toSingle { movies }
        }

    // model -> entity
    private fun ensureMovieEntityList(movies: List<Movie>): List<MovieEntity> =
        arrayListOf<MovieEntity>().apply {
            movies.mapTo(this) { movie ->
                MovieEntity(
                    title = movie.title,
                    link = movie.link,
                    image = movie.image,
                    subtitle = movie.subtitle,
                    director = movie.director,
                    actor = movie.actor,
                    pubDate = movie.pubDate,
                    userRating = movie.userRating
                )
            }
        }

    override fun refreshImageSearchHistory(
        keyword: String,
        images: List<Image>
    ): Single<List<Image>> =
        if (images.isEmpty()) {
            updateSearchHistory(
                fun1 = { naverSearchLocalDataSource.clearImageResult() },
                fun2 = { naverSearchLocalDataSource.saveImageKeyword(keyword) }
            )
                .toSingle { images }
        } else {
            val imageList = ensureImageEntityList(images)
            updateSearchHistory(
                fun1 = { naverSearchLocalDataSource.clearImageResult() },
                fun2 = { naverSearchLocalDataSource.saveImageKeyword(keyword) },
                fun3 = { naverSearchLocalDataSource.saveImageResult(imageList) }
            )
                .toSingle { images }
        }

    private fun ensureImageEntityList(images: List<Image>): List<ImageEntity> =
        arrayListOf<ImageEntity>().apply {
            images.mapTo(this) { image ->
                ImageEntity(
                    link = image.link,
                    sizeWidth = image.sizeWidth,
                    sizeHeight = image.sizeHeight,
                    thumbnail = image.thumbnail,
                    title = image.title
                )
            }
        }

    override fun refreshBlogSearchHistory(keyword: String, blogs: List<Blog>): Single<List<Blog>> =
        if (blogs.isEmpty()) {
            updateSearchHistory(
                fun1 = { naverSearchLocalDataSource.clearBlogResult() },
                fun2 = { naverSearchLocalDataSource.saveBlogKeyword(keyword) }
            )
                .toSingle { blogs }
        } else {
            val blogList = ensureBlogEntityList(blogs)
            updateSearchHistory(
                fun1 = { naverSearchLocalDataSource.clearBlogResult() },
                fun2 = { naverSearchLocalDataSource.saveBlogKeyword(keyword) },
                fun3 = { naverSearchLocalDataSource.saveBlogResult(blogList) }
            )
                .toSingle { blogs }
        }

    private fun ensureBlogEntityList(blogs: List<Blog>): List<BlogEntity> =
        arrayListOf<BlogEntity>().apply {
            blogs.mapTo(this) { blog ->
                BlogEntity(
                    bloggerLink = blog.bloggerLink,
                    bloggerName = blog.bloggerName,
                    description = blog.description,
                    link = blog.link,
                    postdate = blog.postdate,
                    title = blog.title
                )
            }
        }

    override fun getLatestMovieKeyword(): String =
        naverSearchLocalDataSource.getLatestMovieKeyword()

    override fun getLatestImageKeyword(): String =
        naverSearchLocalDataSource.getLatestImageKeyword()

    override fun getLatestBlogKeyword(): String =
        naverSearchLocalDataSource.getLatestBlogKeyword()

    private fun updateSearchHistory(
        fun1: () -> Unit,
        fun2: () -> Unit,
        fun3: (() -> Unit)? = null
    ): Completable {
        val firstCall = Completable.fromCallable(fun1)
        val secondCall = Completable.fromCallable(fun2)
        val completable = firstCall
            .andThen(secondCall)

        return fun3?.let { call ->
            val thirdCall = Completable.fromCallable(call)
            completable
                .andThen(thirdCall)
        } ?: run {
            completable
        }
    }
}