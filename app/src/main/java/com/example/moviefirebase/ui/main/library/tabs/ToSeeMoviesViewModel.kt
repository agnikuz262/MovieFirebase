package com.example.moviefirebase.ui.main.library.tabs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviefirebase.model.model.firebase.MovieDbEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ToSeeMoviesViewModel : ViewModel() {
    private val uid = FirebaseAuth.getInstance().uid
    var dbReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("movies").child("$uid")
    var toSeeMovies: MutableLiveData<List<MovieDbEntity>> = MutableLiveData()

    fun getToSeeMovies(): LiveData<List<MovieDbEntity>> {
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