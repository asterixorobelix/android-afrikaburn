# android-afrikaburn-firestore-util

The Afrikaburn API serves JSON of all the projects, without the ability to filter. The data also should be manipulated in order to have efficient Firestore queries. 

This project allows one to read in the JSON file containing all the projects and write to the Firestore database.

You will need a JSON file with your [service account credentials](https://cloud.google.com/iam/docs/creating-managing-service-account-keys) in it in order to write to your Firestore db.