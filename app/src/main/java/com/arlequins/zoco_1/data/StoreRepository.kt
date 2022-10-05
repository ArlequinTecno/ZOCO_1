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

    suspend fun editMyStore(store: Store): ResourceRemote<String> {
        return try {
            auth.uid?.let {
                store.id?.let { sid ->
                    db.collection(userCollection)
                        .document(it).collection(storeCollection)
                        .document(sid).set(store).await() }
            }
            store.id?.let {sid ->db.collection(storeCollection)
                .document(sid).set(store).await() }
            ResourceRemote.Success(data = store.id)
        }catch(e: FirebaseFirestoreException){
            e.localizedMessage?.let { Log.e("EditStore", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
        catch (e: FirebaseNetworkException){
            e.localizedMessage?.let { Log.e("NetwordEditStore", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }

    }

    suspend fun deleteStore(storeID: String?): ResourceRemote<Boolean> {
        return try {
            auth.uid?.let {
                storeID?.let { it1 ->
                    db.collection(userCollection).document(it)
                        .collection(storeCollection).document(it1).delete().await()
                }
            }
            storeID?.let { db.collection(storeCollection).document(it).delete().await() }
            ResourceRemote.Success(data = true)
        }catch(e: FirebaseFirestoreException){
            e.localizedMessage?.let { Log.e("DeleteStore", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
        catch (e: FirebaseNetworkException){
            e.localizedMessage?.let { Log.e("NetwordDeleteStore", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }
}