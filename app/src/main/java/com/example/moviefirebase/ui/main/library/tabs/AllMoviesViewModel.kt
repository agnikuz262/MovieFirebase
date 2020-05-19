package com.example.moviefirebase.ui.main.library.tabs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviefirebase.model.model.firebase.MovieDbEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class AllMoviesViewModel : ViewModel() {
    private var uid = FirebaseAuth.getInstance().uid
    var dbReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("movies").child("$uid")
    var allMovies: MutableLiveData<List<MovieDbEntity>> = MutableLiveData()

    fun getAllMovies() : LiveData<List<MovieDbEntity>> {
        if(allMovies.value == null) {
            dbReference.addValueEventListener(object: ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if(dataSnapshot.exists()) {
                        allMovies.postValue(toMovies(dataSnapshot))
                    }
                }

                override fun onCancelled(dataSnapshot: DatabaseError) {
                    print(dataSnapshot.toException())
                }
            })
        }
        return allMovies
    }

    fun toMovies(snapshot: DataSnapshot) : List<MovieDbEntity> {
        val list = ArrayList<MovieDbEntity>()
        for(i in snapshot.children) {
            val newMovie = i.getValue(MovieDbEntity::class.java)
            list.add(newMovie!!)
        }
        return list

    }
}