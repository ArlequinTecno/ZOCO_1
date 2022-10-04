package com.arlequins.zoco_1.data

import android.util.Log
import com.arlequins.zoco_1.model.Chat
import com.arlequins.zoco_1.model.NotificationModel
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class ChatRepository {
    private var auth: FirebaseAuth = Firebase.auth
    private var db = Firebase.firestore

    private val userCollection = "Users"
    private val notificationCollection = "Notification"
    private val chatCollection = "Chat"

    suspend fun loadMsg(notificationID: String?): ResourceRemote<QuerySnapshot?> {
        return try{
            val result = auth.uid?.let {
                notificationID?.let { it1 ->
                    db.collection(userCollection).document(it)
                        .collection(notificationCollection)
                        .document(it1).collection(chatCollection).get().await()
                }
            }
            ResourceRemote.Success(data = result)
        }catch(e: FirebaseFirestoreException){
            e.localizedMessage?.let { Log.e("loadChat", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
        catch (e: FirebaseNetworkException){
            e.localizedMessage?.let { Log.e("loadChatNet", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }

    suspend fun sendMsg(
        notification: NotificationModel,
        chatMsg: Chat): ResourceRemote<String?> {
        return try{
            val pathOut = auth.uid?.let { db.collection(userCollection).document(it)
                .collection(notificationCollection).document(notification.id.toString())
                .collection(chatCollection) }
            val documentChat = pathOut?.document()

            val pathIn = db.collection(userCollection)
                .document(notification.article?.uid.toString()).collection(notificationCollection)
                .document(notification.id.toString()).collection(chatCollection)
            chatMsg.id = documentChat?.id

            if (notification.uid == auth.uid){
                chatMsg.type ="out"
                pathOut?.document(documentChat?.id.toString())?.set(chatMsg)?.await()
                chatMsg.type ="in"
                pathIn.document(documentChat?.id.toString()).set(chatMsg).await()
            }
            else{
                chatMsg.type ="in"
                pathOut?.document(documentChat?.id.toString())?.set(chatMsg)?.await()
                chatMsg.type ="out"
                pathIn.document(documentChat?.id.toString()).set(chatMsg).await()
            }
            ResourceRemote.Success(data = chatMsg.id)
        }catch(e: FirebaseFirestoreException){
            e.localizedMessage?.let { Log.e("sendMsg", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
        catch (e: FirebaseNetworkException){
            e.localizedMessage?.let { Log.e("sendMsgNet", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }


}