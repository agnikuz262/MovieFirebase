package com.example.moviefirebase.ui.main.library.tabs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviefirebase.model.model.firebase.MovieDB
import com.google.firebase.database.*

class AllMoviesViewModel : ViewModel() {

    var dbReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("movies")
    var allMovies: MutableLiveData<List<MovieDB>> = MutableLiveData()

    fun getAllMovies() : LiveData<List<MovieDB>> {
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

    private fun toMovies(snapshot: DataSnapshot) : List<MovieDB> {
        val list = ArrayList<MovieDB>()
        for(i in snapshot.children) {
            val newMovie = i.getValue(MovieDB::class.java)
            list.add(newMovie!!)
        }
        for(i in list) {
            print(i.title)
        }
        return list

    }
}