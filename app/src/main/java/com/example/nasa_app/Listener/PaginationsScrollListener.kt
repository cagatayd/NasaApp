package com.example.nasa_app.Listener

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationsScrollListener(var gridLayoutManager: GridLayoutManager) : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount = gridLayoutManager.childCount // toplam görüntülenen item sayısı
        val totalItemCount = gridLayoutManager.itemCount // tüm itemların(listenin) toplamı
        val firstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition() // gösterilen item ın pozisyonu
        if (!isLoading && !isLastPage) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount &&
                    firstVisibleItemPosition >= 0) {
                loadMoreItems()
            }
        }
    }

    protected abstract val isLoading: Boolean
    protected abstract val isLastPage: Boolean
    protected abstract fun loadMoreItems()
}