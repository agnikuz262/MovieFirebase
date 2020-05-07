package com.example.moviefirebase.ui.main.library.tabs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviefirebase.model.model.firebase.MovieDB
import com.google.firebase.database.*

class ToSeeMoviesViewModel : ViewModel() {
    var dbReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("movies")
    var toSeeMovies: MutableLiveData<List<MovieDB>> = MutableLiveData()

    fun getToSeeMovies(): LiveData<List<MovieDB>> {
        if (toSeeMovies.value == null) {
            dbReference.orderByChild("seen").equalTo(false).addValueEventListener(object :
                ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        toSeeMovies.postValue(AllMoviesViewModel().toMovies(dataSnapshot))
                    }
                }

                override fun onCancelled(dataSnapshot: DatabaseError) {
                    print(dataSnapshot.toException())
                }
            })
        }
        return toSeeMovies
    }
}