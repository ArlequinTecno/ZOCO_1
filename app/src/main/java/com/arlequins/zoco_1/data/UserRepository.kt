package com.arlequins.zoco_1.data

import android.util.Log
import com.arlequins.zoco_1.model.User
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await


class UserRepository {

    private var auth: FirebaseAuth = Firebase.auth
    private var db = Firebase.firestore

    private val userCollection = "Users"
    suspend fun registerUser(email: String, passwd: String): ResourceRemote<String?>{
        return try{
            val result = auth.createUserWithEmailAndPassword(email, passwd).await()
            ResourceRemote.Success(data = result.user?.uid)
        }
        catch(e: FirebaseAuthException){
            e.localizedMessage?.let { Log.e("Register", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
        catch (e:FirebaseNetworkException){
            e.localizedMessage?.let { Log.e("NetwordRegister", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }

    suspend fun logInUser(email: String, passwd: String): ResourceRemote<String?> {
        return try{
            val result = auth.signInWithEmailAndPassword(email, passwd).await()
            ResourceRemote.Success(data = result.user?.uid)
        }
        catch(e: FirebaseAuthException){
            e.localizedMessage?.let { Log.e("Login", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
        catch (e:FirebaseNetworkException){
            e.localizedMessage?.let { Log.e("NetwordLogin", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }

    suspend fun createUser(user: User): ResourceRemote<String?> {
        return try{
            user.uid?.let {db.collection(userCollection).document(it).set(user).await()}
            ResourceRemote.Success(data = user.uid)
        }catch(e: FirebaseFirestoreException){
            e.localizedMessage?.let { Log.e("CreateUser", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
        catch (e:FirebaseNetworkException){
            e.localizedMessage?.let { Log.e("NetwordCreateUser", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }

    }
    suspend fun searchUser(): ResourceRemote<QuerySnapshot?> {
        return try {
            val result = db.collection(userCollection).get().await()
            ResourceRemote.Success(data = result)
        }catch(e: FirebaseFirestoreException){
            e.localizedMessage?.let { Log.e("SearchUser", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
        catch (e: FirebaseNetworkException){
            e.localizedMessage?.let { Log.e("NetwordSearchUser", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }
    suspend fun myUser(user: User): ResourceRemote<String?>{
        return try {
            val result = db.collection(userCollection).get().await()
            var flag = false
            for (doc in result?.documents!!){
                val myUser = doc?.toObject<User>()
                if (auth.uid == myUser?.uid){
                    user.name = myUser?.name
                    user.phone = myUser?.phone
                    flag = true
                    break
                }
            }
            if (flag){
                ResourceRemote.Success(data = user.name)
            }
            else{
                ResourceRemote.Error(message = "No se encontró el usuario")
            }

        }catch(e: FirebaseFirestoreException){
            e.localizedMessage?.let { Log.e("SearchUser", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
        catch (e: FirebaseNetworkException){
            e.localizedMessage?.let { Log.e("NetwordSearchUser", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }
}

