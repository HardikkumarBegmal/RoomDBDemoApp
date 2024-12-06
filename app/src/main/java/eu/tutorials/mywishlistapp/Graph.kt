package eu.tutorials.mywishlistapp

import android.app.Service
import android.content.Context
import androidx.room.Room
import eu.tutorials.mywishlistapp.data.WishDatabase
import eu.tutorials.mywishlistapp.data.WishRepository

object Graph {

    lateinit var database: WishDatabase

    val wishRepository by lazy {
        WishRepository(database.wishDao())
    }

    fun provide(context: Context) {
        database = Room.databaseBuilder(context, WishDatabase::class.java, "wishlist.db").build()
    }

    val listOfNavigationElements = arrayListOf(
        ServiceItems(
            R.drawable.baseline_profile_24,
            "Profile item clicked !!",
            "Profile"),
        ServiceItems(
            R.drawable.baseline_inventory_transfer_24,
            "Inventory Transfer item clicked !!",
            "Inventory Transfer"),
        ServiceItems(
            R.drawable.baseline_order_summary_24,
            "Order Summary item clicked !!",
            "Order Summary")
    )

    val listOfBottomNavigationElements = arrayListOf<ServiceItems>(
        ServiceItems(R.drawable.baseline_profile_24,"Home", "Home"),
        ServiceItems(R.drawable.baseline_inventory_transfer_24,"Home", "Home"),
        ServiceItems(R.drawable.baseline_order_summary_24,"Home", "Home"),
        ServiceItems(R.drawable.baseline_profile_24,"Home", "Home"),
    )

} // Singleton - one instance is created in app