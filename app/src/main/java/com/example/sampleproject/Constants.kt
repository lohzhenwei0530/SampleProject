package com.example.sampleproject

object Constants {

    object Api {
        const val CONNECT_TIMEOUT_SEC = 30L
        const val READ_TIMEOUT_SEC = 60L

        object Url {
            const val users = "users"
        }

        object BaseUrl {
            const val jsonBase = "https://jsonplaceholder.typicode.com/"
        }
    }

    object Database {
        const val DB_NAME = "sample_db"
    }

}