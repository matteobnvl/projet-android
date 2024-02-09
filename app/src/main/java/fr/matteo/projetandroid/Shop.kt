package fr.matteo.projetandroid

object Shop {
    private val shop: MutableList<Movies> = mutableListOf()

    fun getShop(): List<Movies> {
        return shop
    }

    fun addMovieInShop(movie: Movies) {
        shop.add(movie)
    }

    fun removeMovieFromShop(movie: Movies) {
        shop.remove(movie)
    }

    fun isMovieInShop(movie: Movies): Boolean {
        return shop.contains(movie)
    }
}