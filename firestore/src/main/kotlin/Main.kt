
import com.google.auth.oauth2.GoogleCredentials
import com.google.cloud.firestore.Firestore
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.cloud.FirestoreClient
import com.google.gson.Gson
import databasemodels.AfrikaburnProjects
import java.io.File
import java.io.FileInputStream

fun main() {

    getProjectsFromJson()
}

fun getProjectsFromJson(){
    val json = File("/Users/nathanstasin/Documents/Personal/firebase-database-util/projects.json").readText()
    val deserializedJson = Gson().fromJson(json, AfrikaburnProjects::class.java)
    println("${deserializedJson.projects.size} projects received")

    val firestore = setupFirebaseFirestore()

    for (project in deserializedJson.projects){
        println("set ${project.nid}")
        val result = firestore.collection("projects").document(project.nid).set(project)
        println("Update time : " + result.get().getUpdateTime());

        val separatedCollectionResult = firestore.collection(project.type).document(project.nid).set(project)

        println("Update time : " + separatedCollectionResult.get().getUpdateTime())
    }
}

private fun setupFirebaseFirestore(): Firestore {
 //Use a service account with datastore.write privileges in order to insert into the afrikaburn Firestore
    val serviceAccount =
        FileInputStream("/Users/nathanstasin/Documents/Personal/firebase-database-util/afrikaburnkotlin-59456bd598bb.json")
    val credentials = GoogleCredentials.fromStream(serviceAccount);
    val options = FirebaseOptions.Builder()
        .setCredentials(credentials)
        .build()
    FirebaseApp.initializeApp(options)

    return FirestoreClient.getFirestore()
}