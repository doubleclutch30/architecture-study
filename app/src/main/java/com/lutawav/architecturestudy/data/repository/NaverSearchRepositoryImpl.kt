package com.lutawav.architecturestudy.data.repository

import com.lutawav.architecturestudy.data.mapper.BlogDataMapper
import com.lutawav.architecturestudy.data.mapper.ImageDataMapper
import com.lutawav.architecturestudy.data.mapper.MovieDataMapper
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
    ): Single<MovieRepo> =
        naverSearchRemoteDataSource.getMovie(
            keyword = keyword
        )
            .map {
                MovieRepo(
                    keyword = keyword,
                    movies = it.movies
                )
            }
            .flatMap {
                refreshMovieSearchHistory(
                    keyword = keyword,
                    movies = it.movies
                )
            }

    override fun getImage(
        keyword: String
    ): Single<ImageRepo> =
        naverSearchRemoteDataSource.getImage(
            keyword = keyword
        )
            .map {
                ImageRepo(
                    keyword = keyword,
                    images = it.images
                )
            }
            .flatMap {
                refreshImageSearchHistory(
                    keyword = keyword,
                    images = it.images
                )
            }

    override fun getBlog(
        keyword: String
    ): Single<BlogRepo> =
        naverSearchRemoteDataSource.getBlog(
            keyword = keyword
        )
            .map {
                BlogRepo(
                    keyword = keyword,
                    blogs = it.blogs
                )
            }
            .flatMap {
                refreshBlogSearchHistory(
                    keyword = keyword,
                    blogs = it.blogs
                )
            }

    override fun getLatestMovieResult(): Single<MovieRepo> =
        naverSearchLocalDataSource.getMovie()
            .map {
                MovieRepo(
                    keyword = naverSearchLocalDataSource.getLatestMovieKeyword(),
                    movies = it.movies.map { entity ->
                        MovieDataMapper.reverseMap(entity)
                    }
                )
            }

    override fun getLatestImageResult(): Single<ImageRepo> =
        naverSearchLocalDataSource.getImage()
            .map {
                ImageRepo(
                    keyword = naverSearchLocalDataSource.getLatestImageKeyword(),
                    images = it.images.map { entity ->
                        ImageDataMapper.reverseMap(entity)
                    }
                )
            }

    override fun getLatestBlogResult(): Single<BlogRepo> =
        naverSearchLocalDataSource.getBlog()
            .map {
                BlogRepo(
                    keyword = naverSearchLocalDataSource.getLatestBlogKeyword(),
                    blogs = it.blogs.map { entity ->
                        BlogDataMapper.reverseMap(entity)
                    }
                )
            }

    override fun refreshMovieSearchHistory(
        keyword: String,
        movies: List<Movie>
    ): Single<MovieRepo> {
        val movieRepo = MovieRepo(
            keyword = keyword,
            movies = movies
        )

        return if (movies.isEmpty()) {
            updateSearchHistory(
                fun1 = { naverSearchLocalDataSource.clearMovieResult() },
                fun2 = { naverSearchLocalDataSource.saveMovieKeyword(keyword) }
            )
                .toSingle { movieRepo }
        } else {
            updateSearchHistory(
                fun1 = { naverSearchLocalDataSource.clearMovieResult() },
                fun2 = { naverSearchLocalDataSource.saveMovieKeyword(keyword) },
                fun3 = {
                    naverSearchLocalDataSource.saveMovieResult(
                        movies.map { MovieDataMapper.map(it) }
                    )
                }
            )
                .toSingle { movieRepo }
        }
    }

    override fun refreshImageSearchHistory(
        keyword: String,
        images: List<Image>
    ): Single<ImageRepo> {
        val imageRepo = ImageRepo(
            keyword = keyword,
            images = images
        )

        return if (images.isEmpty()) {
            updateSearchHistory(
                fun1 = { naverSearchLocalDataSource.clearImageResult() },
                fun2 = { naverSearchLocalDataSource.saveImageKeyword(keyword) }
            )
                .toSingle { imageRepo }
        } else {
            updateSearchHistory(
                fun1 = { naverSearchLocalDataSource.clearImageResult() },
                fun2 = { naverSearchLocalDataSource.saveImageKeyword(keyword) },
                fun3 = {
                    naverSearchLocalDataSource.saveImageResult(
                        images.map { ImageDataMapper.map(it) }
                    )
                }
            )
                .toSingle { imageRepo }
        }
    }

    override fun refreshBlogSearchHistory(
        keyword: String,
        blogs: List<Blog>
    ): Single<BlogRepo> {
        val blogRepo = BlogRepo(
            keyword = keyword,
            blogs = blogs
        )

        return if (blogs.isEmpty()) {
            updateSearchHistory(
                fun1 = { naverSearchLocalDataSource.clearBlogResult() },
                fun2 = { naverSearchLocalDataSource.saveBlogKeyword(keyword) }
            )
                .toSingle { blogRepo }
        } else {
            updateSearchHistory(
                fun1 = { naverSearchLocalDataSource.clearBlogResult() },
                fun2 = { naverSearchLocalDataSource.saveBlogKeyword(keyword) },
                fun3 = {
                    naverSearchLocalDataSource.saveBlogResult(
                        blogs.map { BlogDataMapper.map(it) }
                    )
                }
            )
                .toSingle { blogRepo }
        }
    }

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