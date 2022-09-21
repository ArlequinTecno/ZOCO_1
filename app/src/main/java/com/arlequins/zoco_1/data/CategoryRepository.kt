package com.arlequins.zoco_1.data

import android.util.Log
import com.arlequins.zoco_1.model.Category
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class CategoryRepository {

    private var db = Firebase.firestore
    private val categoryCollection: String = "Category"


    suspend fun createCategory(category: Category): ResourceRemote<String>{
        return try {
            val categoryRef = db.collection(categoryCollection).document()
            category.id = categoryRef.id
            categoryRef.set(category).await()
            ResourceRemote.Success(data = categoryRef.id)
        }catch(e: FirebaseFirestoreException){
            e.localizedMessage?.let { Log.e("CreateCategory", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
        catch (e: FirebaseNetworkException){
            e.localizedMessage?.let { Log.e("NetwordCreateCategory", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }

    }
    suspend fun   searchCategory(): ResourceRemote<QuerySnapshot?>{
        return try {
            val result = db.collection(categoryCollection).get().await()
            ResourceRemote.Success(data = result)
        }catch(e: FirebaseFirestoreException){
            e.localizedMessage?.let { Log.e("SearchCategory", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
        catch (e: FirebaseNetworkException){
            e.localizedMessage?.let { Log.e("NetwordSearchCategory", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }

}