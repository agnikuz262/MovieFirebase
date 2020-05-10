package com.example.moviefirebase.ui.main.library.tabs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviefirebase.model.model.firebase.MovieDbEntity
import com.google.firebase.database.*

class SeenMoviesViewModel : ViewModel() {
    var dbReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("movies")
    var seenMovies: MutableLiveData<List<MovieDbEntity>> = MutableLiveData()

    fun getSeenMovies(): LiveData<List<MovieDbEntity>> {
        if (seenMovies.value == null) {
            dbReference.orderByChild("seen").equalTo(true)
                .addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        if (dataSnapshot.exists()) {
                            seenMovies.postValue(AllMoviesViewModel().toMovies(dataSnapshot))
                        }
                    }

                    override fun onCancelled(dataSnapshot: DatabaseError) {
                        print(dataSnapshot.toException())
                    }
                })
        }
        return seenMovies
    }
}