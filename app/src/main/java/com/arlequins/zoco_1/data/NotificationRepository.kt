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


class NotificationRepository {

    private var auth: FirebaseAuth = Firebase.auth
    private var db = Firebase.firestore

    private val userCollection = "Users"
    private val notificationCollection = "Notification"
    private val chatCollection = "Chat"

    suspend fun crateNotification(
        notification: NotificationModel,
        chatModel: Chat
    ): ResourceRemote<String?> {
        return try{
            val uidSeller = notification.article?.uid
            if (auth.uid != uidSeller) {
                val pathClient = auth.uid?.let { db.collection(userCollection).document(it)
                    .collection(notificationCollection)}

                val documentNotificationClient = pathClient?.document()

                val pathSeller = uidSeller?.let { db.collection(userCollection).document(it)
                    .collection(notificationCollection) }

                notification.id = documentNotificationClient?.id
                notification.uid = auth.uid
                notification.state = "sent"
                notification.type ="client"

                documentNotificationClient?.id?.let {
                    pathClient.document(it).set(notification).await()
                    notification.state = "received"
                    notification.type = "seller"
                    pathSeller?.document(it)?.set(notification)?.await()

                    val pathChatClient = pathClient.document(it).collection(chatCollection)
                    val pathChatSeller = pathSeller?.document(it)?.collection(chatCollection)
                    val documentChat = pathChatClient.document()
                    chatModel.id = documentChat.id
                    chatModel.type = "out"
                    pathChatClient.document(documentChat.id).set(chatModel).await()
                    chatModel.type = "in"
                    pathChatSeller?.document(documentChat.id)?.set(chatModel)?.await()
                }
                ResourceRemote.Success(data = notification.state)
            }
            else{
                ResourceRemote.Error(message = "No puedes comprar tus propios productos")
            }

        }
        catch(e: FirebaseFirestoreException){
            ResourceRemote.Error(message = e.localizedMessage)
        }
        catch(e: FirebaseNetworkException){
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }

    suspend fun search(): ResourceRemote<QuerySnapshot?> {
        return try{
            val result = auth.uid?.let {
                db.collection(userCollection).document(it)
                    .collection(notificationCollection).get().await()
            }
            ResourceRemote.Success(data = result)
        }catch(e: FirebaseFirestoreException){
            e.localizedMessage?.let { Log.e("SearchNotification", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
        catch (e: FirebaseNetworkException){
            e.localizedMessage?.let { Log.e("NetwordSearchNt", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }
}