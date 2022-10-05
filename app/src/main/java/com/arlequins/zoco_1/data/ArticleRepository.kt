package com.arlequins.zoco_1.data

import android.util.Log
import com.arlequins.zoco_1.model.Article
import com.arlequins.zoco_1.model.User
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

class ArticleRepository {
    private var auth: FirebaseAuth = Firebase.auth
    private var db = Firebase.firestore

    private val userCollection: String = "Users"
    private val articleCollection: String = "Articles"

    suspend fun createArticle(article: Article): ResourceRemote<String> {
        return try{
            val path = auth.uid?.let { db.collection(userCollection).document(it)
                .collection(articleCollection) }
            val documentArticle = path?.document()
            article.id = documentArticle?.id
            article.uid = auth.uid
            documentArticle?.id?.let {
                path.document(it).set(article).await()
                db.collection(articleCollection).document(it).set(article).await()
            }
            ResourceRemote.Success(data = article.id)
        }catch(e: FirebaseFirestoreException){
            e.localizedMessage?.let { Log.e("CreateArticles", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
        catch (e: FirebaseNetworkException){
            e.localizedMessage?.let { Log.e("NetwordCreateArticles", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }
    suspend fun searchMyArticle(): ResourceRemote<QuerySnapshot?>{
        return try {
            val result = auth.uid?.let { db.collection(userCollection).document(it)
                .collection(articleCollection).get().await() }
            ResourceRemote.Success(data = result)
        }catch(e: FirebaseFirestoreException){
            e.localizedMessage?.let { Log.e("SearchArticles", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
        catch (e: FirebaseNetworkException){
            e.localizedMessage?.let { Log.e("NetwordSearchArticles", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }
    suspend fun searchArticle(): ResourceRemote<QuerySnapshot?>{
        return try {
            val result = db.collection(articleCollection).get().await()
            ResourceRemote.Success(data = result)
        }catch(e: FirebaseFirestoreException){
            e.localizedMessage?.let { Log.e("SearchArticles", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
        catch (e: FirebaseNetworkException){
            e.localizedMessage?.let { Log.e("NetwordSearchArticles", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }

    suspend fun editMyArticle(article: Article): ResourceRemote<String> {
        return try {
            auth.uid?.let {
                article.id?.let { aid ->
                    db.collection(userCollection)
                        .document(it).collection(articleCollection)
                        .document(aid).set(article).await()
                }
            }
            article.id?.let {aid -> db.collection(articleCollection)
                .document(aid).set(article).await()
            }
            ResourceRemote.Success(data = article.id)
        }catch(e: FirebaseFirestoreException){
            e.localizedMessage?.let { Log.e("EditArticles", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
        catch (e: FirebaseNetworkException){
            e.localizedMessage?.let { Log.e("NetwordEditArticles", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }

    suspend fun deleteArticle(articleID: String?): ResourceRemote<Boolean> {
        return try {
            articleID?.let {
                auth.uid?.let { it1 ->
                    db.collection(userCollection)
                        .document(it1).collection(articleCollection)
                        .document(it).delete().await()
                }
            }
            articleID?.let { db.collection(articleCollection).document(it).delete().await() }
            ResourceRemote.Success(true)
        }catch(e: FirebaseFirestoreException){
            e.localizedMessage?.let { Log.e("DeleteArticles", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
        catch (e: FirebaseNetworkException){
            e.localizedMessage?.let { Log.e("NetwordDeleteArticles", it) }
            ResourceRemote.Error(message = e.localizedMessage)
        }
    }
}

