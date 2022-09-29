package com.arlequins.zoco_1.data

import android.util.Log
import com.arlequins.zoco_1.model.Store
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class StoreRepository {

    private var auth: FirebaseAuth = Firebase.auth
    private var db = Firebase.firestore
    private val userCollection = "Users"
    private val storeCollection = "Store"

    suspend fun createStore(store: Store): ResourceRemote<String> {
        return try{
            val path = auth.uid?.let { db.collection(userCollection).document(it)
                .collection(storeCollection) }
            val documentStore = path?.document()
            store.id = documentStore?.id
            store.uid = auth.uid
            documentStore?.id?.let { path.document(it).set(store).await() }

            db.collection(storeCollection).document(store.id.toString()).set(store).await()
            ResourceRemote.Success(data = store.id)
        }catch(e: FirebaseFirestoreException){
            e.localizedMessage?.let { Log.e("CreateStore", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
        catch (e: FirebaseNetworkException){
            e.localizedMessage?.let { Log.e("NetwordCreateStore", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }
    suspend fun searchMyStore(): ResourceRemote<QuerySnapshot?>{
        return try {
            val result = auth.uid?.let { db.collection(userCollection).document(it)
                .collection(storeCollection).get().await() }
            ResourceRemote.Success(data = result)
        }catch(e: FirebaseFirestoreException){
            e.localizedMessage?.let { Log.e("SearchStore", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
        catch (e: FirebaseNetworkException){
            e.localizedMessage?.let { Log.e("NetwordSearchStore", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }
    suspend fun searchStore(): ResourceRemote<QuerySnapshot?>{
        return try {
            val result = db.collection(storeCollection).get().await()
            ResourceRemote.Success(data = result)
        }catch(e: FirebaseFirestoreException){
            e.localizedMessage?.let { Log.e("SearchStore", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
        catch (e: FirebaseNetworkException){
            e.localizedMessage?.let { Log.e("NetwordSearchStore", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }
}